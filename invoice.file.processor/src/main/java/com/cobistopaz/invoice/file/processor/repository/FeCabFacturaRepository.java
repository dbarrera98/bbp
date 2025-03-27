package com.cobistopaz.invoice.file.processor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cobistopaz.invoice.file.processor.model.FeCabFactura;

public interface FeCabFacturaRepository extends JpaRepository<FeCabFactura, Integer> {
    FeCabFactura findByCfPtoFacAndCfSeqnos(String cfPtoFac, Integer  cfSeqnos);
}

