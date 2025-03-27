package com.cobistopaz.invoice.file.processor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cobistopaz.invoice.file.processor.model.FeRespuesta;
import com.cobistopaz.invoice.file.processor.model.FeRespuestaId;

public interface FeRespuestaRepository extends JpaRepository<FeRespuesta, FeRespuestaId> {
    FeRespuesta findByIdRePtoFacAndIdReSeqnosAndIdReModo(String rePtoFac, Integer reSeqnos, String reModo);
}