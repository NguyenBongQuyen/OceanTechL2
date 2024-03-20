package com.octl2.api.controller;

import com.octl2.api.commons.OctResponse;
import com.octl2.api.dto.FulfilmentInfo;
import com.octl2.api.service.FulfilmentMappingService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/fulfilment-mapping")
@RequiredArgsConstructor
@Validated
public class FulfilmentMappingController {
    private final FulfilmentMappingService fulfilmentMappingService;

    @GetMapping
    public OctResponse<List<FulfilmentInfo>> getLogistic() {
        List<FulfilmentInfo> result = fulfilmentMappingService.getLogistics();
        return OctResponse.build(result);
    }
}
