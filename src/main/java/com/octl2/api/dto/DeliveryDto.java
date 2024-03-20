package com.octl2.api.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryDto {
    private Integer id;
    private Integer locationId;
    private Integer fulfilmentId;
    private Integer lastMileId;
    private Integer warehouseId;
}
