package com.octl2.api.transfer;

import com.octl2.api.dto.*;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class LogisticTransfer {
    public <T extends LogisticDto> void transferLogistics(ResultSetQuery resultSetQuery, T logisticDto) {
        transferResultSetToFulfilmentDto(resultSetQuery, logisticDto.getFulfilmentDtoSet());
        transferResultSetToLastMileDto(resultSetQuery, logisticDto.getLastMileDtoSet());
        transferResultSetToWareHouseDto(resultSetQuery, logisticDto.getWarehouseDtoSet());
    }

    public void transferResultSetToFulfilmentDto(ResultSetQuery resultSetQuery, Set<FulfilmentDto> fulfilmentDtoSet) {
        if (resultSetQuery.getFulfilmentId() == null) {
            return;
        }
        FulfilmentDto fulfilmentDto = new FulfilmentDto(
                resultSetQuery.getFulfilmentId(),
                resultSetQuery.getFulfilmentName(),
                resultSetQuery.getFulfilmentShortname());
        fulfilmentDtoSet.add(fulfilmentDto);
    }

    public void transferResultSetToLastMileDto(ResultSetQuery resultSetQuery, Set<LastMileDto> lastMileDtoSet) {
        if (resultSetQuery.getLastMileId() == null) {
            return;
        }
        LastMileDto lastMileDto = new LastMileDto(
                resultSetQuery.getLastMileId(),
                resultSetQuery.getLastMileName(),
                resultSetQuery.getLastMileShortname());
        lastMileDtoSet.add(lastMileDto);
    }

    public void transferResultSetToWareHouseDto(ResultSetQuery resultSetQuery, Set<WarehouseDto> warehouseDtoSet) {
        if (resultSetQuery.getWarehouseId() == null) {
            return;
        }
        WarehouseDto warehouseDto = new WarehouseDto(
                resultSetQuery.getWarehouseId(),
                resultSetQuery.getWareHouseName(),
                resultSetQuery.getWareHouseShortname());
        warehouseDtoSet.add(warehouseDto);
    }
}
