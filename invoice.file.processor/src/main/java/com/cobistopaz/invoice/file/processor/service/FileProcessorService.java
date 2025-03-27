package com.cobistopaz.invoice.file.processor.service;

import com.cobistopaz.invoice.file.processor.model.FeCabFactura;
import com.cobistopaz.invoice.file.processor.model.FeRespuesta;
import com.cobistopaz.invoice.file.processor.model.JsonResponse;
import com.cobistopaz.invoice.file.processor.repository.FeCabFacturaRepository;
import com.cobistopaz.invoice.file.processor.repository.FeRespuestaRepository;
import com.cobistopaz.invoice.file.processor.util.Constants;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class FileProcessorService {

    private static final Logger logger = LoggerFactory.getLogger(FileProcessorService.class);

    private final FeRespuestaRepository feRespuestaRepository;
    private final FeCabFacturaRepository feCabFacturaRepository;
    private final ObjectMapper mapper;

    @Autowired
    public FileProcessorService(FeRespuestaRepository feRespuestaRepository,
                                FeCabFacturaRepository feCabFacturaRepository,
                                ObjectMapper mapper) {
        this.feRespuestaRepository = feRespuestaRepository;
        this.feCabFacturaRepository = feCabFacturaRepository;
        this.mapper = mapper;
    }

    @Transactional
    public void processContent(String fileContent, String fileName) throws Exception {
        JsonResponse response;
        try {
            response = mapper.readValue(fileContent, JsonResponse.class);
        } catch (Exception e) {
            logger.error("Error al parsear el contenido del archivo JSON, el json  : {}", fileContent, e);
            throw new Exception("Error al parsear el contenido del archivo JSON", e);
        }

        logger.info("Nombre del archivo: {}", fileName);
        String[] fileNameParts = fileName.split("_");
        if (fileNameParts.length < 4) {
            logger.error("Nombre del archivo no tiene el formato esperado: {}", fileName);
            throw new Exception("Nombre del archivo no tiene el formato recibido: " + fileName);
        }

        String ptoFac = fileNameParts[2];
        String seqnosStr = fileNameParts[3].replace(".json", "").replace(".txt", "");
        int seqnos;
        try {
            seqnos = Integer.parseInt(seqnosStr);
        } catch (NumberFormatException e) {
            logger.error("Secuencia no es un número válido: {}", seqnosStr, e);
            throw new Exception("Secuencia no es un número válido: " + seqnosStr, e);
        }

        FeRespuesta feRespuesta = feRespuestaRepository.findByIdRePtoFacAndIdReSeqnosAndIdReModo(ptoFac, seqnos, Constants.MODO_FACTURA);

        if (feRespuesta != null) {
            logger.info("Registro encontrado para re_modo '{}', ptoFac '{}' y seqnos '{}'. Actualizando...", Constants.MODO_FACTURA, ptoFac, seqnos);
            updateFeRespuesta(feRespuesta, response);
        } else {
            logger.error("Registro con re_modo '{}' no encontrado para ptoFac '{}' y seqnos '{}'", Constants.MODO_FACTURA, ptoFac, seqnos);
        }

        FeCabFactura feCabFactura = feCabFacturaRepository.findByCfPtoFacAndCfSeqnos(ptoFac, seqnos);
        if (feCabFactura != null) {
            logger.info("Registro encontrado para ptoFac '{}' y seqnos '{}'. Actualizando...", ptoFac, seqnos);
            updateFeCabFactura(feCabFactura, response);
        } else {
            logger.warn("Registro FeCabFactura no encontrado para ptoFac '{}' y seqnos '{}'", ptoFac, seqnos);
        }
    }

    private void updateFeRespuesta(FeRespuesta feRespuesta, JsonResponse response) {
        try {
            feRespuesta.setReCufe(response.getCufe());
            feRespuesta.setReError(response.getCodigoError());
            feRespuesta.setReFechaAut(validateFechaAutorizacion(response.getFechaAutorizacion()));
            feRespuesta.setReEstado(response.getEstado());
            feRespuesta.setReMensajeRspta(response.getMensajeRespuesta());
            feRespuesta.setReNumAutorizacion(response.getNumAutorizacion());
            feRespuesta.setReSecuencialErp(response.getSecuencialERP());
            feRespuesta.setReUrlCodeQr(response.getUrlCodeQR());
            feRespuestaRepository.save(feRespuesta);
            logger.info("FeRespuesta actualizada exitosamente: {}", feRespuesta);
        } catch (Exception e) {
            logger.error("Error al actualizar FeRespuesta: {}", feRespuesta, e);
            throw new RuntimeException("Error al actualizar FeRespuesta", e);
        }
    }

    private void updateFeCabFactura(FeCabFactura feCabFactura, JsonResponse response) {
        try {
            feCabFactura.setCfCufe(response.getCufe());
            if (Constants.CODIGO_ERROR_AUTORIZADO.equals(response.getCodigoError()) && Constants.ESTADO_AUTORIZADO.equals(response.getEstado())) {
                feCabFactura.setCfEstado(Constants.ESTADO_APROBADO);
            } else {
                feCabFactura.setCfEstado(Constants.ESTADO_ERROR);
            }
            feCabFacturaRepository.save(feCabFactura);
            logger.info("Cufe actualizado exitosamente en cabecera: {}", feCabFactura);
        } catch (Exception e) {
            logger.error("Error al actualizar cufe en cabecera: {}", feCabFactura, e);
            throw new RuntimeException("Error al actualizar cufe en cabecera", e);
        }
    }

    private LocalDateTime validateFechaAutorizacion(LocalDateTime fechaAutorizacion) {
        if (fechaAutorizacion != null && fechaAutorizacion.equals(LocalDateTime.of(1, 1, 1, 0, 0))) {
            return null;
        }
        return fechaAutorizacion;
    }
}