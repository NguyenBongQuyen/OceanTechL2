package com.octl2.api.service.impl;

import com.octl2.api.commons.exception.ErrorMessages;
import com.octl2.api.commons.exception.OctException;
import com.octl2.api.consts.Const;
import com.octl2.api.dto.DeliveryDto;
import com.octl2.api.dto.ResultSetQuery;
import com.octl2.api.dto.SubDistrictDto;
import com.octl2.api.repository.SubDistrictRepository;
import com.octl2.api.service.SubDistrictService;
import com.octl2.api.transfer.SubDistrictTransfer;
import com.octl2.api.validation.DeliveryValidation;
import com.octl2.api.validation.DistrictValidation;
import com.octl2.api.validation.LogisticValidation;
import com.octl2.api.validation.SubDistrictValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubDistrictServiceImpl implements SubDistrictService {
    @Value("${spring.levelMapping}")
    private int levelMapping;
    private final SubDistrictRepository subDistrictRepository;
    private final SubDistrictTransfer subDistrictTransfer;
    private final SubDistrictValidation subDistrictValidation;
    private final LogisticValidation logisticValidation;
    private final DistrictValidation districtValidation;
    private final DeliveryValidation deliveryValidation;

    @Override
    public ResultSetQuery getById(int id) {
        return subDistrictRepository.getDtoById(id);
    }

    private List<ResultSetQuery> getSubDistrictAndLogistic(int districtId) {
        districtValidation.checkExist(districtId);
        switch (levelMapping) {
            case Const.LEVEL_MAPPING_ONE:
                return subDistrictRepository.getSubDistrictAndLogisticLevelOne(districtId);
            case Const.LEVEL_MAPPING_TWO:
                return subDistrictRepository.getSubDistrictAndLogisticLevelTwo(districtId);
            case Const.LEVEL_MAPPING_THREE:
                return subDistrictRepository.getSubDistrictAndLogisticLevelThree(districtId);
            default:
                throw new OctException(ErrorMessages.LEVEL_MAPPING_NOT_ALLOWED);
        }
    }

    @Override
    public List<SubDistrictDto> getLogistics(int districtId) {
        List<ResultSetQuery> resultSetQueries = getSubDistrictAndLogistic(districtId);
        return subDistrictTransfer.transferResultSetToSubDistrictDto(resultSetQueries);
    }

    @Override
    public void updateLogistic(int locationId, DeliveryDto deliveryDto) {
        subDistrictValidation.checkExist(locationId);
        deliveryValidation.validUpdateNotNull(deliveryDto);
        logisticValidation.checkExist(deliveryDto);
        switch (levelMapping) {
            case Const.LEVEL_MAPPING_ONE:
            case Const.LEVEL_MAPPING_TWO:
                throw new OctException(ErrorMessages.LOCATION_SMALLER_LEVEL_MAPPING);
            case Const.LEVEL_MAPPING_THREE:
                subDistrictRepository.updateLogisticLevelThree(locationId, deliveryDto);
                break;
            default:
                throw new OctException(ErrorMessages.LEVEL_MAPPING_NOT_ALLOWED);
        }
    }
}
