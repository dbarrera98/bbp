package com.cobistopaz.custom.invoice.updater.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.cobistopaz.custom.invoice.updater.repositories.IFeFacturaRepository;

@Service
public class FeFacturaService {

    private static final Logger logger = LoggerFactory.getLogger(FeFacturaService.class);

    @Autowired
    private IFeFacturaRepository feFacturaRepository;

    public void actualizarFactura(String ptoFac, Integer seqNos) {
        logger.info("Inicio de método actualizarFactura..");
        try {
            int rowsUpdated = feFacturaRepository.actualizarFactura(ptoFac, seqNos);
            if (rowsUpdated > 0) {
                logger.info("Factura actualizada correctamente.");
            } else {
                logger.warn("No se pudo actualizar la factura con ptoFac={} y seqNos={}", ptoFac, seqNos);
            }
        } catch (Exception e) {
            logger.error("Error al actualizar la factura con ptoFac={} y seqNos={}", ptoFac, seqNos, e);
        }
    }

    public boolean isArchivoProcesado(String ptoFac, Integer seqNos) {
        logger.info("Inicio de método isArchivoProcesado..");
        try {
            int count = feFacturaRepository.countByPtoFacAndSeqNosAndEstado(ptoFac, seqNos);
            return count > 0;
        } catch (Exception e) {
            logger.error("Error al verificar si el archivo ha sido procesado con ptoFac={} y seqNos={}", ptoFac, seqNos, e);
            return false;
        }
    }
}