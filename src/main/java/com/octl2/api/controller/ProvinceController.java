package com.octl2.api.controller;

import com.octl2.api.commons.OctResponse;
import com.octl2.api.consts.ExcelConst;
import com.octl2.api.consts.MessageConst;
import com.octl2.api.dto.DeliveryDto;
import com.octl2.api.dto.ProvinceDto;
import com.octl2.api.dto.ResultSetQuery;
import com.octl2.api.service.ProvinceService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/api/v1/provinces")
@RequiredArgsConstructor
@Validated
public class ProvinceController {
    private final ProvinceService provinceService;

    @GetMapping("/{id}")
    public OctResponse<ResultSetQuery> getById(@PathVariable @Min(1) int id) {
        ResultSetQuery result = provinceService.getById(id);
        return OctResponse.build(result);
    }

    @GetMapping
    public OctResponse<List<ProvinceDto>> getProvinceAndLogistic() {
        List<ProvinceDto> result = provinceService.getLogistics();
        return OctResponse.build(result);
    }

    @GetMapping("/excel")
    public ResponseEntity<ByteArrayResource> exportExcelByLevel() {
        ByteArrayResource byteArrayResource = provinceService.exportFileExcel();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", ExcelConst.LOGISTIC_EXCEL_NAME);
        return ResponseEntity.ok().headers(headers).body(byteArrayResource);
    }

    @PutMapping("/{locationId}")
    public OctResponse<String> updateLogistic(@PathVariable("locationId") int locationId,
                                              @RequestBody DeliveryDto deliveryDto) {
        provinceService.updateLogistic(locationId, deliveryDto);
        return OctResponse.build(MessageConst.UPDATE_SUCCESS);
    }

}

