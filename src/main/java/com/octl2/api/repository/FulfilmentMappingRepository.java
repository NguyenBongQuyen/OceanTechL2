package com.octl2.api.repository;

import com.octl2.api.consts.Const;
import com.octl2.api.dto.ResultSetQuery;
import com.octl2.api.entity.FulfilmentMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FulfilmentMappingRepository extends JpaRepository<FulfilmentMapping, Integer> {
    @Query(value = "SELECT " +
            "ffm.partner_id AS fulfilmentId, " +
            "ffm.name AS fulfilmentName, " +
            "ffm.shortname AS fulfilmentShortname, " +
            "lm.partner_id AS lastmileId, " +
            "lm.name AS lastmileName, " +
            "lm.shortname AS lastmileShortname, " +
            "wh.warehouse_id AS warehouseId, " +
            "wh.warehouse_name AS warehouseName, " +
            "wh.warehouse_shortname AS warehouseShortname " +
            "FROM bp_partner AS ffm " +
            "LEFT JOIN cf_mapping_ffm_lm cmfl ON ffm.partner_id = cmfl.fulfilment_id " +
            "LEFT JOIN bp_partner lm ON lm.partner_id = cmfl.lastmile_id " +
            "LEFT JOIN bp_warehouse wh ON ffm.partner_id = wh.fulfilment_id " +
            "WHERE ffm.partner_type = " + Const.TYPE_OF_FULFILMENT +
            " AND lm.partner_type = " + Const.TYPE_OF_LAST_MILE +
            " ORDER BY fulfilmentId", nativeQuery = true)
    List<ResultSetQuery> getLogistics();

}
