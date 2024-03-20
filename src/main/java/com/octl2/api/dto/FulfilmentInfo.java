package com.octl2.api.dto;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FulfilmentInfo {
    private Integer fulfilmentId;
    private String fulfilmentName;
    private Set<LastMileDto> lastMileDtoSet;
    private Set<WarehouseDto> warehouseDtoSet;

    public FulfilmentInfo(ResultSetQuery resultSetQuery) {
        if (resultSetQuery == null) {
            return;
        }
        this.fulfilmentId = resultSetQuery.getFulfilmentId();
        this.fulfilmentName = resultSetQuery.getFulfilmentName();
        this.lastMileDtoSet = new HashSet<>();
        this.lastMileDtoSet.add(new LastMileDto(resultSetQuery));
        this.warehouseDtoSet = new HashSet<>();
        this.warehouseDtoSet.add(new WarehouseDto(resultSetQuery));
    }

}
