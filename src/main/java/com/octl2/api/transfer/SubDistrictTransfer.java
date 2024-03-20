package com.octl2.api.transfer;

import com.octl2.api.dto.ResultSetQuery;
import com.octl2.api.dto.SubDistrictDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class SubDistrictTransfer {
    private final LogisticTransfer logisticTransfer;

    public List<SubDistrictDto> transferResultSetToSubDistrictDto(List<ResultSetQuery> resultSetQueries) {
        Map<Integer, SubDistrictDto> subDistrictDtoMap = new LinkedHashMap<>();
        for (ResultSetQuery resultSetQuery : resultSetQueries) {
            SubDistrictDto subDistrictDto = subDistrictDtoMap.computeIfAbsent(resultSetQuery.getSubDistrictId(), k -> new SubDistrictDto(resultSetQuery));
            logisticTransfer.transferLogistics(resultSetQuery, subDistrictDto);
        }
        return new ArrayList<>(subDistrictDtoMap.values());
    }
}
