package com.octl2.api.service.impl;

import com.octl2.api.commons.exception.ErrorMessages;
import com.octl2.api.commons.exception.OctException;
import com.octl2.api.consts.Const;
import com.octl2.api.consts.ExcelConst;
import com.octl2.api.dto.DeliveryDto;
import com.octl2.api.dto.LogisticExcel;
import com.octl2.api.dto.ProvinceDto;
import com.octl2.api.dto.ResultSetQuery;
import com.octl2.api.repository.ProvinceRepository;
import com.octl2.api.service.ProvinceService;
import com.octl2.api.transfer.ProvinceTransfer;
import com.octl2.api.utils.ExcelUtil;
import com.octl2.api.validation.DeliveryValidation;
import com.octl2.api.validation.LogisticValidation;
import com.octl2.api.validation.ProvinceValidation;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProvinceServiceImpl implements ProvinceService {
    @Value("${spring.levelMapping}")
    private int levelMapping;
    private final ProvinceRepository provinceRepository;
    private final ProvinceTransfer provinceTransfer;
    private final ProvinceValidation provinceValidation;
    private final LogisticValidation logisticValidation;
    private final DeliveryValidation deliveryValidation;

    @Override
    public ResultSetQuery getById(int id) {
        return provinceRepository.getDtoById(id);
    }

    private List<ResultSetQuery> getProvinceAndLogistic() {
        switch (levelMapping) {
            case Const.LEVEL_MAPPING_ONE:
                return provinceRepository.getProvinceAndLogisticLevelOne();
            case Const.LEVEL_MAPPING_TWO:
                return provinceRepository.getProvinceAndLogisticLevelTwo();
            case Const.LEVEL_MAPPING_THREE:
                return provinceRepository.getProvinceAndLogisticLevelThree();
            default:
                throw new OctException(ErrorMessages.LEVEL_MAPPING_NOT_ALLOWED);
        }
    }

    @Override
    public List<ProvinceDto> getLogistics() {
        List<ResultSetQuery> resultSetQueries = getProvinceAndLogistic();
        return provinceTransfer.transferResultSetToProvinceDto(resultSetQueries);
    }

    @Override
    public ByteArrayResource exportFileExcel() {
        List<LogisticExcel> logisticExcelList = exportAllProvinces();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet(ExcelConst.LOGISTIC_SHEET_EXCEL_NAME);
            int rowIndex = ExcelConst.ROW_START_INDEX;
            ExcelUtil.writeHeaderRow(sheet, rowIndex++, levelMapping);
            for (LogisticExcel logisticExcel : logisticExcelList) {
                Row row = sheet.createRow(rowIndex++);
                ExcelUtil.writeDataRows(logisticExcel, row, levelMapping);
            }
            return ExcelUtil.writeFileExcel(workbook);
        } catch (IOException e) {
            throw new OctException(ErrorMessages.EXPORT_EXCEL_ERROR);
        }
    }

    private List<LogisticExcel> exportAllProvinces() {
        List<ResultSetQuery> resultSetQueryList = getProvinceAndLogistic();
        if (resultSetQueryList.isEmpty()) {
            throw new OctException(ErrorMessages.NOT_FOUND);
        }
        List<LogisticExcel> logisticExcelList = new ArrayList<>();
        for (ResultSetQuery resultSetQuery : resultSetQueryList) {
            LogisticExcel logisticExcel = new LogisticExcel(resultSetQuery);
            logisticExcelList.add(logisticExcel);
        }
        return logisticExcelList;
    }

    @Override
    public void updateLogistic(int locationId, DeliveryDto deliveryDto) {
        provinceValidation.checkExist(locationId);
        deliveryValidation.validUpdateNotNull(deliveryDto);
        logisticValidation.checkExist(deliveryDto);
        switch (levelMapping) {
            case Const.LEVEL_MAPPING_ONE:
                provinceRepository.updateLogisticLevelOne(locationId, deliveryDto);
                break;
            case Const.LEVEL_MAPPING_TWO:
                provinceRepository.updateLogisticLevelTwo(locationId, deliveryDto);
                break;
            case Const.LEVEL_MAPPING_THREE:
                provinceRepository.updateLogisticLevelThree(locationId, deliveryDto);
                break;
            default:
                throw new OctException(ErrorMessages.LEVEL_MAPPING_NOT_ALLOWED);
        }
    }

}
