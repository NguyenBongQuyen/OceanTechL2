package com.octl2.api.validation;

import com.octl2.api.commons.exception.ErrorMessages;
import com.octl2.api.commons.exception.OctInvalidInputException;
import com.octl2.api.commons.suberror.ApiSubError;
import com.octl2.api.commons.suberror.ApiValidatorError;
import com.octl2.api.consts.MessageConst;
import com.octl2.api.dto.DeliveryDto;
import com.octl2.api.repository.PartnerRepository;
import com.octl2.api.repository.WarehouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class LogisticValidation {
    private final PartnerRepository partnerRepository;
    private final WarehouseRepository warehouseRepository;

    public void checkExist(DeliveryDto deliveryDto) {
        List<ApiSubError> errorList = new ArrayList<>();
        if (!partnerRepository.existsByFulfilmentId(deliveryDto.getFulfilmentId())) {
            errorList.add(new ApiValidatorError(MessageConst.FULFILMENT_ID, deliveryDto.getFulfilmentId(), ErrorMessages.FULFILMENT_ID_NOT_FOUND.getMessage()));
        }
        if (!partnerRepository.existsByLastMileId(deliveryDto.getLastMileId())) {
            errorList.add(new ApiValidatorError(MessageConst.LAST_MILE_ID, deliveryDto.getLastMileId(), ErrorMessages.LAST_MILE_ID_NOT_FOUND.getMessage()));
        }
        if (!warehouseRepository.existsById(deliveryDto.getWarehouseId())) {
            errorList.add(new ApiValidatorError(MessageConst.WAREHOUSE_ID, deliveryDto.getWarehouseId(), ErrorMessages.WAREHOUSE_ID_NOT_FOUND.getMessage()));
        }
        if (!errorList.isEmpty()) {
            throw new OctInvalidInputException(ErrorMessages.INVALID_VALUE, errorList);
        }
    }


}
