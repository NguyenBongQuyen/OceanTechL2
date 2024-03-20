package com.octl2.api.service.impl;

import com.octl2.api.commons.exception.ErrorMessages;
import com.octl2.api.commons.exception.OctException;
import com.octl2.api.consts.Const;
import com.octl2.api.dto.DeliveryDto;
import com.octl2.api.dto.DistrictDto;
import com.octl2.api.dto.ResultSetQuery;
import com.octl2.api.repository.DistrictRepository;
import com.octl2.api.service.DistrictService;
import com.octl2.api.transfer.DistrictTransfer;
import com.octl2.api.validation.DeliveryValidation;
import com.octl2.api.validation.DistrictValidation;
import com.octl2.api.validation.LogisticValidation;
import com.octl2.api.validation.ProvinceValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DistrictServiceImpl implements DistrictService {
    @Value("${spring.levelMapping}")
    private int levelMapping;
    private final DistrictRepository districtRepository;
    private final DistrictTransfer districtTransfer;
    private final DistrictValidation districtValidation;
    private final LogisticValidation logisticValidation;
    private final ProvinceValidation provinceValidation;
    private final DeliveryValidation deliveryValidation;

    @Override
    public ResultSetQuery getById(int id) {
        return districtRepository.getDtoById(id);
    }

    private List<ResultSetQuery> getDistrictAndLogistic(int provinceId) {
        provinceValidation.checkExist(provinceId);
        switch (levelMapping) {
            case Const.LEVEL_MAPPING_ONE:
                return districtRepository.getDistrictAndLogisticLevelOne(provinceId);
            case Const.LEVEL_MAPPING_TWO:
                return districtRepository.getDistrictAndLogisticLevelTwo(provinceId);
            case Const.LEVEL_MAPPING_THREE:
                return districtRepository.getDistrictAndLogisticLevelThree(provinceId);
            default:
                throw new OctException(ErrorMessages.LEVEL_MAPPING_NOT_ALLOWED);
        }
    }

    @Override
    public List<DistrictDto> getLogistics(int provinceId) {
        List<ResultSetQuery> resultSetQueries = getDistrictAndLogistic(provinceId);
        return districtTransfer.transferResultSetToDistrictDto(resultSetQueries);
    }

    @Override
    public void updateLogistic(int locationId, DeliveryDto deliveryDto) {
        districtValidation.checkExist(locationId);
        deliveryValidation.validUpdateNotNull(deliveryDto);
        logisticValidation.checkExist(deliveryDto);
        switch (levelMapping) {
            case Const.LEVEL_MAPPING_ONE:
                throw new OctException(ErrorMessages.LOCATION_SMALLER_LEVEL_MAPPING);
            case Const.LEVEL_MAPPING_TWO:
                districtRepository.updateLogisticLevelTwo(locationId, deliveryDto);
                break;
            case Const.LEVEL_MAPPING_THREE:
                districtRepository.updateLogisticLevelThree(locationId, deliveryDto);
                break;
            default:
                throw new OctException(ErrorMessages.LEVEL_MAPPING_NOT_ALLOWED);
        }
    }
}
