package com.octl2.api.validation;

import com.octl2.api.commons.exception.ErrorMessages;
import com.octl2.api.commons.exception.OctInvalidInputException;
import com.octl2.api.commons.suberror.ApiSubError;
import com.octl2.api.commons.suberror.ApiValidatorError;
import com.octl2.api.consts.MessageConst;
import com.octl2.api.dto.DeliveryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class DeliveryValidation {
    public void validUpdateNotNull(DeliveryDto deliveryDto) {
        List<ApiSubError> errorList = new ArrayList<>();
        if (Objects.isNull(deliveryDto.getFulfilmentId())) {
            errorList.add(new ApiValidatorError(MessageConst.FULFILMENT_ID, null, MessageConst.FULFILMENT_ID_NULL));
        }
        if (Objects.isNull(deliveryDto.getLastMileId())) {
            errorList.add(new ApiValidatorError(MessageConst.LAST_MILE_ID, null, MessageConst.LAST_MILE_ID_NULL));
        }
        if (Objects.isNull(deliveryDto.getWarehouseId())) {
            errorList.add(new ApiValidatorError(MessageConst.WAREHOUSE_ID, null, MessageConst.WAREHOUSE_ID_NULL));
        }
        if (!errorList.isEmpty()) {
            throw new OctInvalidInputException(ErrorMessages.INVALID_VALUE, errorList);
        }
    }
}
