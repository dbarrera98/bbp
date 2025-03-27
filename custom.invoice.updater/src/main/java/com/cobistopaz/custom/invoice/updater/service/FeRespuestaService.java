package com.cobistopaz.custom.invoice.updater.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.cobistopaz.custom.invoice.updater.repositories.IFeRespuestaRepository;

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
}