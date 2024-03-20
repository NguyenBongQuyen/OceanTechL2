package com.octl2.api.validation;

import com.octl2.api.commons.exception.ErrorMessages;
import com.octl2.api.commons.exception.OctException;
import com.octl2.api.repository.DistrictRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DistrictValidation {
    private final DistrictRepository districtRepository;

    public void checkExist(int districtId) {
        if (!districtRepository.existsById(districtId)) {
            throw new OctException(ErrorMessages.DISTRICT_ID_NOT_FOUND);
        }
    }
}
