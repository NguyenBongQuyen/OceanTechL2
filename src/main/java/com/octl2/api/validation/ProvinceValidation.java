package com.octl2.api.validation;

import com.octl2.api.commons.exception.ErrorMessages;
import com.octl2.api.commons.exception.OctException;
import com.octl2.api.repository.ProvinceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProvinceValidation {
    private final ProvinceRepository provinceRepository;

    public void checkExist(int provinceId) {
        if (!provinceRepository.existsById(provinceId)) {
            throw new OctException(ErrorMessages.PROVINCE_ID_NOT_FOUND);
        }
    }
}
