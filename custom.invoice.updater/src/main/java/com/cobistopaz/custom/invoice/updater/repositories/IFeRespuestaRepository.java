package com.cobistopaz.custom.invoice.updater.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cobistopaz.custom.invoice.updater.entities.FeRespuestaEntity;

@Repository
public interface IFeRespuestaRepository extends JpaRepository<FeRespuestaEntity, Integer> {

    boolean existsByPtoFacAndSeqNosAndModo(String ptoFac, Integer seqNos, String modo);
}