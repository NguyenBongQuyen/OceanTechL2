package com.octl2.api.controller;

import com.octl2.api.commons.OctResponse;
import com.octl2.api.consts.MessageConst;
import com.octl2.api.dto.DeliveryDto;
import com.octl2.api.dto.DistrictDto;
import com.octl2.api.dto.ResultSetQuery;
import com.octl2.api.service.DistrictService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/api/v1/districts")
@RequiredArgsConstructor
@Validated
public class DistrictController {
    private final DistrictService districtService;

    @GetMapping("/{id}")
    public OctResponse<ResultSetQuery> getById(@PathVariable @Min(1) int id) {
        ResultSetQuery result = districtService.getById(id);
        return OctResponse.build(result);
    }

    @GetMapping("/province-id/{provinceId}")
    public OctResponse<List<DistrictDto>> getDistrictAndLogistic(@PathVariable("provinceId") int provinceId) {
        List<DistrictDto> result = districtService.getLogistics(provinceId);
        return OctResponse.build(result);
    }

    @PutMapping("/{locationId}")
    public OctResponse<String> updateLogistic(@PathVariable("locationId") int locationId,
                                              @RequestBody DeliveryDto deliveryDto) {
        districtService.updateLogistic(locationId, deliveryDto);
        return OctResponse.build(MessageConst.UPDATE_SUCCESS);
    }

}
