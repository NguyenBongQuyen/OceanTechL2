package com.octl2.api.service;

import com.octl2.api.dto.DeliveryDto;
import com.octl2.api.dto.ProvinceDto;
import com.octl2.api.dto.ResultSetQuery;
import org.springframework.core.io.ByteArrayResource;

import java.util.List;

public interface ProvinceService {
    ResultSetQuery getById(int id);

    List<ProvinceDto> getLogistics();

    ByteArrayResource exportFileExcel();

    void updateLogistic(int locationId, DeliveryDto deliveryDto);

}
