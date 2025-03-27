package com.cobistopaz.custom.invoice.updater.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.cobistopaz.custom.invoice.updater.repositories.IFeRespuestaRepository;
import com.cobistopaz.custom.invoice.updater.dto.FeRespuestaDto;
import com.cobistopaz.custom.invoice.updater.entities.FeRespuestaEntity;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Service
public class FeRespuestaService {

    private static final Logger logger = LoggerFactory.getLogger(FeRespuestaService.class);

    @Autowired
    private IFeRespuestaRepository feRespuestaRepository;

    public boolean isFacturaDuplicada(String ptoFac, Integer seqNos, String modo) {
        logger.info("Inicio de método isFacturaDuplicada..");
        try {
            return feRespuestaRepository.existsByPtoFacAndSeqNosAndModo(ptoFac, seqNos, modo);
        } catch (Exception e) {
            logger.error("Error al verificar factura duplicada", e);
            return false;
        }
    }

    public boolean guardarRespuesta(FeRespuestaDto feRespuestaDto) {
        if (feRespuestaDto.getSeqNos() == null) {
            logger.error("El valor de seqNos es NULL. No se puede guardar la respuesta.");
            return false;
        }

        if (isFacturaDuplicada(feRespuestaDto.getPtoFac(), feRespuestaDto.getSeqNos(), feRespuestaDto.getModo())) {
            logger.info("Factura duplicada detectada: ptoFac={}, seqNos={}, modo={}. No se guarda el registro.",
                    feRespuestaDto.getPtoFac(), feRespuestaDto.getSeqNos(), feRespuestaDto.getModo());
            return false;
        }

        if (feRespuestaRepository.existsByPtoFacAndSeqNosAndModo(
                feRespuestaDto.getPtoFac(), feRespuestaDto.getSeqNos(), feRespuestaDto.getModo())) {
            logger.info("Factura duplicada detectada en la base de datos: ptoFac={}, seqNos={}, modo={}. No se guarda el registro.",
                    feRespuestaDto.getPtoFac(), feRespuestaDto.getSeqNos(), feRespuestaDto.getModo());
            return false;
        }

        FeRespuestaEntity entity = new FeRespuestaEntity();
        entity.setPtoFac(feRespuestaDto.getPtoFac());
        entity.setSeqNos(feRespuestaDto.getSeqNos());
        entity.setModo(feRespuestaDto.getModo());
        entity.setCufe(feRespuestaDto.getCufe());
        entity.setTipo(feRespuestaDto.getTipo());

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
            LocalDateTime localDateTime = LocalDateTime.parse(feRespuestaDto.getFecha(), formatter);
            entity.setFecha(Timestamp.valueOf(localDateTime));
            feRespuestaRepository.save(entity);
            return true;
        } catch (DateTimeParseException e) {
            logger.error("Error al convertir la fecha: {}", feRespuestaDto.getFecha(), e);
            return false;
        } catch (Exception e) {
            logger.error("Error al guardar la respuesta", e);
            return false;
        }
    }
}