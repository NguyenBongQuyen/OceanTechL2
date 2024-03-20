package com.octl2.api.transfer;

import com.octl2.api.dto.DistrictDto;
import com.octl2.api.dto.ResultSetQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class DistrictTransfer {
    private final LogisticTransfer logisticTransfer;

    public List<DistrictDto> transferResultSetToDistrictDto(List<ResultSetQuery> resultSetQueries) {
        Map<Integer, DistrictDto> districtDtoMap = new LinkedHashMap<>();
        for (ResultSetQuery resultSetQuery : resultSetQueries) {
            DistrictDto districtDto = districtDtoMap.computeIfAbsent(resultSetQuery.getDistrictId(), k -> new DistrictDto(resultSetQuery));
            logisticTransfer.transferLogistics(resultSetQuery, districtDto);
        }
        return new ArrayList<>(districtDtoMap.values());
    }
}
