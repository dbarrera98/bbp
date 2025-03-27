package com.cobistopaz.custom.invoice.updater.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.cobistopaz.custom.invoice.updater.entities.FeFacturaEntity;

@Repository
public interface IFeFacturaRepository extends JpaRepository<FeFacturaEntity, Integer> {

    @Modifying
    @Transactional
    @Query("UPDATE FeFacturaEntity f SET f.estado = 'EN' WHERE f.ptoFac = :ptoFac AND f.seqNos = :seqNos AND f.estado = 'P'")
    int actualizarFactura(@Param("ptoFac") String ptoFac, @Param("seqNos") Integer seqNos);

    @Query("SELECT COUNT(f) FROM FeFacturaEntity f WHERE f.ptoFac = :ptoFac AND f.seqNos = :seqNos AND f.estado = 'EN'")
    int countByPtoFacAndSeqNosAndEstado(@Param("ptoFac") String ptoFac, @Param("seqNos") Integer seqNos);
}