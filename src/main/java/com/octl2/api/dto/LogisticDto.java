package com.octl2.api.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LogisticDto {
    private Set<FulfilmentDto> fulfilmentDtoSet;
    private Set<LastMileDto> lastMileDtoSet;
    private Set<WarehouseDto> warehouseDtoSet;

}
