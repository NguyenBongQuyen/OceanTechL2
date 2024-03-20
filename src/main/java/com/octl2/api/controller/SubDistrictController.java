package com.octl2.api.controller;

import com.octl2.api.commons.OctResponse;
import com.octl2.api.consts.MessageConst;
import com.octl2.api.dto.DeliveryDto;
import com.octl2.api.dto.ResultSetQuery;
import com.octl2.api.dto.SubDistrictDto;
import com.octl2.api.service.SubDistrictService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/api/v1/subdistricts")
@RequiredArgsConstructor
@Validated
public class SubDistrictController {
    private final SubDistrictService subDistrictService;

    @GetMapping("/{id}")
    public OctResponse<ResultSetQuery> getById(@PathVariable @Min(1) int id) {
        ResultSetQuery result = subDistrictService.getById(id);
        return OctResponse.build(result);
    }

    @GetMapping("/district-id/{districtId}")
    public OctResponse<List<SubDistrictDto>> getLogistic(@PathVariable("districtId") int districtId) {
        List<SubDistrictDto> result = subDistrictService.getLogistics(districtId);
        return OctResponse.build(result);
    }

    @PutMapping("/{locationId}")
    public OctResponse<String> updateLogistic(@PathVariable("locationId") int locationId,
                                              @RequestBody DeliveryDto deliveryDto) {
        subDistrictService.updateLogistic(locationId, deliveryDto);
        return OctResponse.build(MessageConst.UPDATE_SUCCESS);
    }

}
