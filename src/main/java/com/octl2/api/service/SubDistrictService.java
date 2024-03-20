package com.octl2.api.service;

import com.octl2.api.dto.DeliveryDto;
import com.octl2.api.dto.ResultSetQuery;
import com.octl2.api.dto.SubDistrictDto;

import java.util.List;

public interface SubDistrictService {
    ResultSetQuery getById(int id);

    List<SubDistrictDto> getLogistics(int districtId);

    void updateLogistic(int locationId, DeliveryDto deliveryDto);

}
