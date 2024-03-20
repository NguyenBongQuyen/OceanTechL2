package com.octl2.api.transfer;

import com.octl2.api.dto.ProvinceDto;
import com.octl2.api.dto.ResultSetQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class ProvinceTransfer {
    private final LogisticTransfer logisticTransfer;

    public List<ProvinceDto> transferResultSetToProvinceDto(List<ResultSetQuery> resultSetQueries) {
        Map<Integer, ProvinceDto> provinceDtoMap = new LinkedHashMap<>();
        for (ResultSetQuery resultSetQuery : resultSetQueries) {
            ProvinceDto provinceDto = provinceDtoMap.computeIfAbsent(resultSetQuery.getProvinceId(), k -> new ProvinceDto(resultSetQuery));
            logisticTransfer.transferLogistics(resultSetQuery, provinceDto);
        }
        return new ArrayList<>(provinceDtoMap.values());
    }
}

