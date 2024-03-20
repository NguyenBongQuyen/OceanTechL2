package com.octl2.api.service;

import com.octl2.api.dto.DeliveryDto;
import com.octl2.api.dto.DistrictDto;
import com.octl2.api.dto.ResultSetQuery;

import java.util.List;

public interface DistrictService {
    ResultSetQuery getById(int id);

    List<DistrictDto> getLogistics(int provinceId);

    void updateLogistic(int locationId, DeliveryDto deliveryDto);

}
