package com.octl2.api.service.impl;

import com.octl2.api.dto.*;
import com.octl2.api.repository.FulfilmentMappingRepository;
import com.octl2.api.service.FulfilmentMappingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FulfilmentMappingServiceImpl implements FulfilmentMappingService {
    private final FulfilmentMappingRepository fulfilmentMappingRepository;

    @Override
    public List<FulfilmentInfo> getLogistics() {
        List<ResultSetQuery> resultSetQueries = fulfilmentMappingRepository.getLogistics();
        Map<Integer, FulfilmentInfo> fulfilmentInfoMap = new LinkedHashMap<>();
        for (ResultSetQuery resultSetQuery : resultSetQueries) {
            FulfilmentInfo fulfilmentInfo = fulfilmentInfoMap.computeIfAbsent(resultSetQuery.getFulfilmentId(), k -> new FulfilmentInfo(resultSetQuery));
            addInformationToFulfilment(fulfilmentInfo, resultSetQuery);
        }
        return new ArrayList<>(fulfilmentInfoMap.values());
    }

    private void addInformationToFulfilment(FulfilmentInfo fulfilmentInfo, ResultSetQuery resultSetQuery) {
        if (resultSetQuery.getLastMileId() != null) {
            fulfilmentInfo.getLastMileDtoSet().add(new LastMileDto(resultSetQuery));
        }
        if (resultSetQuery.getWarehouseId() != null) {
            fulfilmentInfo.getWarehouseDtoSet().add(new WarehouseDto(resultSetQuery));
        }
    }
}
