package com.octl2.api.repository;

import com.octl2.api.consts.Const;
import com.octl2.api.entity.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PartnerRepository extends JpaRepository<Partner, Integer> {
    @Query(value = "SELECT EXISTS " +
            "(SELECT 1 " +
            "FROM bp_partner ffm " +
            "WHERE ffm.partner_id = :partnerId " +
            "AND ffm.partner_type = " + Const.TYPE_OF_FULFILMENT + ")", nativeQuery = true)
    boolean existsByFulfilmentId(@Param("partnerId") int partnerId);

    @Query(value = "SELECT EXISTS " +
            "(SELECT 1 " +
            "FROM bp_partner lm " +
            "WHERE lm.partner_id = :partnerId " +
            "AND lm.partner_type = " + Const.TYPE_OF_LAST_MILE + ")", nativeQuery = true)
    boolean existsByLastMileId(@Param("partnerId") int partnerId);


}
