package com.octl2.api.repository;

import com.octl2.api.dto.DeliveryDto;
import com.octl2.api.dto.ResultSetQuery;
import com.octl2.api.entity.SubDistrict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface SubDistrictRepository extends JpaRepository<SubDistrict, Integer> {
    @Query(value = "SELECT " +
            "sdt.subdistrict_id AS subDistrictId, " +
            "sdt.district_id AS districtId, " +
            "sdt.name AS subDistrictName, " +
            "sdt.code AS subDistrictCode " +
            "FROM lc_subdistrict AS sdt " +
            "WHERE sdt.subdistrict_id = :id", nativeQuery = true)
    ResultSetQuery getDtoById(@Param("id") int id);

    @Query(value = "SELECT " +
            "sdt.subdistrict_id AS subdistrictId, " +
            "sdt.name AS subdistrictName, " +
            "sdt.code AS subdistrictCode, " +
            "dt.district_id AS districtId, " +
            "dt.name AS districtName, " +
            "dt.code AS districtCode, " +
            "p.province_id AS provinceId, " +
            "p.name AS provinceName, " +
            "p.code AS provinceCode, " +
            "pn_ffm.partner_id AS fulfilmentId, " +
            "pn_ffm.name AS fulfilmentName, " +
            "pn_ffm.shortname AS fulfilmentShortname, " +
            "pn_lm.partner_id AS lastMileId, " +
            "pn_lm.name AS lastMileName, " +
            "pn_lm.shortname AS lastMileShortname, " +
            "wh.warehouse_id AS warehouseId, " +
            "wh.warehouse_name AS warehouseName, " +
            "wh.warehouse_shortname AS warehouseShortname " +
            "FROM lc_subdistrict AS sdt " +
            "INNER JOIN lc_district dt ON sdt.district_id = dt.district_id " +
            "INNER JOIN lc_province p ON dt.province_id = p.province_id " +
            "LEFT JOIN cf_default_delivery cdd ON p.province_id = cdd.location_id " +
            "LEFT JOIN bp_partner pn_ffm ON cdd.fulfilment_id = pn_ffm.partner_id " +
            "LEFT JOIN bp_partner pn_lm ON cdd.lastmile_id = pn_lm.partner_id " +
            "LEFT JOIN bp_warehouse wh ON cdd.warehouse_id = wh.warehouse_id " +
            "WHERE sdt.district_id = :districtId " +
            "ORDER BY provinceId", nativeQuery = true)
    List<ResultSetQuery> getSubDistrictAndLogisticLevelOne(@Param("districtId") int districtId);

    @Query(value = "SELECT " +
            "sdt.subdistrict_id AS subdistrictId, " +
            "sdt.name AS subdistrictName, " +
            "sdt.code AS subdistrictCode, " +
            "dt.district_id AS districtId, " +
            "dt.name AS districtName, " +
            "dt.code AS districtCode, " +
            "pn_ffm.partner_id AS fulfilmentId, " +
            "pn_ffm.name AS fulfilmentName, " +
            "pn_ffm.shortname AS fulfilmentShortname, " +
            "pn_lm.partner_id AS lastMileId, " +
            "pn_lm.name AS lastMileName, " +
            "pn_lm.shortname AS lastMileShortname, " +
            "wh.warehouse_id AS warehouseId, " +
            "wh.warehouse_name AS warehouseName, " +
            "wh.warehouse_shortname AS warehouseShortname " +
            "FROM lc_subdistrict AS sdt " +
            "INNER JOIN lc_district dt ON sdt.district_id = dt.district_id " +
            "LEFT JOIN cf_default_delivery cdd ON dt.district_id = cdd.location_id " +
            "LEFT JOIN bp_partner pn_ffm ON cdd.fulfilment_id = pn_ffm.partner_id " +
            "LEFT JOIN bp_partner pn_lm ON cdd.lastmile_id = pn_lm.partner_id " +
            "LEFT JOIN bp_warehouse wh ON cdd.warehouse_id = wh.warehouse_id " +
            "WHERE sdt.district_id = :districtId " +
            "ORDER BY districtId", nativeQuery = true)
    List<ResultSetQuery> getSubDistrictAndLogisticLevelTwo(@Param("districtId") int districtId);

    @Query(value = "SELECT " +
            "sdt.subdistrict_id AS subdistrictId, " +
            "sdt.name AS subdistrictName, " +
            "sdt.code AS subdistrictCode, " +
            "pn_ffm.partner_id AS fulfilmentId, " +
            "pn_ffm.name AS fulfilmentName, " +
            "pn_ffm.shortname AS fulfilmentShortname, " +
            "pn_lm.partner_id AS lastMileId, " +
            "pn_lm.name AS lastMileName, " +
            "pn_lm.shortname AS lastMileShortname, " +
            "wh.warehouse_id AS warehouseId, " +
            "wh.warehouse_name AS warehouseName, " +
            "wh.warehouse_shortname AS warehouseShortname " +
            "FROM lc_subdistrict AS sdt " +
            "INNER JOIN lc_district dt ON sdt.district_id = dt.district_id " +
            "LEFT JOIN cf_default_delivery cdd ON sdt.subdistrict_id = cdd.location_id " +
            "LEFT JOIN bp_partner pn_ffm ON cdd.fulfilment_id = pn_ffm.partner_id " +
            "LEFT JOIN bp_partner pn_lm ON cdd.lastmile_id = pn_lm.partner_id " +
            "LEFT JOIN bp_warehouse wh ON cdd.warehouse_id = wh.warehouse_id " +
            "WHERE sdt.district_id = :districtId " +
            "ORDER BY subdistrictId", nativeQuery = true)
    List<ResultSetQuery> getSubDistrictAndLogisticLevelThree(@Param("districtId") int districtId);

    @Query(value = "SELECT EXISTS " +
            "(SELECT 1 " +
            "FROM lc_subdistrict AS sdt " +
            "WHERE sdt.subdistrict_id = :subDistrictId)", nativeQuery = true)
    boolean existsById(@Param("subDistrictId") int subDistrictId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE cf_default_delivery " +
            "SET fulfilment_id = :#{#deliveryDto.fulfilmentId}, " +
            "lastmile_id = :#{#deliveryDto.lastMileId}, " +
            "warehouse_id = :#{#deliveryDto.warehouseId} " +
            "WHERE location_id = :#{#locationId}", nativeQuery = true)
    void updateLogisticLevelThree(@Param("locationId") int locationId, @Param("deliveryDto") DeliveryDto deliveryDto);

}
