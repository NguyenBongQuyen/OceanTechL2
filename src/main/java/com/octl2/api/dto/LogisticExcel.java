package com.octl2.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LogisticExcel {
    private Integer provinceId;
    private String provinceName;
    private String provinceCode;
    private Integer districtId;
    private String districtName;
    private String districtCode;
    private Integer subDistrictId;
    private String subDistrictName;
    private String subDistrictCode;
    private Integer fulfilmentId;
    private String fulfilmentName;
    private Integer lastMileId;
    private String lastMileName;
    private Integer wareHouseId;
    private String wareHouseName;

    public LogisticExcel(ResultSetQuery resultSetQuery) {
        if (resultSetQuery == null) {
            return;
        }
        this.provinceId = resultSetQuery.getProvinceId();
        this.provinceName = resultSetQuery.getProvinceName();
        this.provinceCode = resultSetQuery.getProvinceCode();
        this.districtId = resultSetQuery.getDistrictId();
        this.districtName = resultSetQuery.getDistrictName();
        this.districtCode = resultSetQuery.getDistrictCode();
        this.subDistrictId = resultSetQuery.getSubDistrictId();
        this.subDistrictName = resultSetQuery.getSubDistrictName();
        this.subDistrictCode = resultSetQuery.getSubDistrictCode();
        this.fulfilmentId = resultSetQuery.getFulfilmentId();
        this.fulfilmentName = resultSetQuery.getFulfilmentName();
        this.lastMileId = resultSetQuery.getLastMileId();
        this.lastMileName = resultSetQuery.getLastMileName();
        this.wareHouseId = resultSetQuery.getWarehouseId();
        this.wareHouseName = resultSetQuery.getWareHouseName();
    }

}
