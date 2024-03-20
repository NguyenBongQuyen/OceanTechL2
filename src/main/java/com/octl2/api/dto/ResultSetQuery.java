package com.octl2.api.dto;

public interface ResultSetQuery {
    Integer getProvinceId();
    String getProvinceName();
    String getProvinceCode();
    Integer getDistrictId();
    String getDistrictName();
    String getDistrictCode();
    Integer getSubDistrictId();
    String getSubDistrictName();
    String getSubDistrictCode();
    Integer getFulfilmentId();
    String getFulfilmentName();
    String getFulfilmentShortname();
    Integer getLastMileId();
    String getLastMileName();
    String getLastMileShortname();
    Integer getWarehouseId();
    String getWareHouseName();
    String getWareHouseShortname();
}
