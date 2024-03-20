package com.octl2.api.validation;

import com.octl2.api.commons.exception.ErrorMessages;
import com.octl2.api.commons.exception.OctException;
import com.octl2.api.repository.SubDistrictRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SubDistrictValidation {
    private final SubDistrictRepository subDistrictRepository;

    public void checkExist(int subDistrictId) {
        if (!subDistrictRepository.existsById(subDistrictId)) {
            throw new OctException(ErrorMessages.SUB_DISTRICT_ID_NOT_FOUND);
        }
    }
}
