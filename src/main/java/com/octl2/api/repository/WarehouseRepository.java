package com.octl2.api.repository;

import com.octl2.api.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Integer> {
    @Query(value = "SELECT EXISTS " +
            "(SELECT 1 " +
            "FROM bp_warehouse wh " +
            "WHERE wh.warehouse_id = :warehouseId)", nativeQuery = true)
    boolean existsById(@Param("warehouseId") int warehouseId);
}
