package com.octl2.api.utils;

import com.octl2.api.commons.exception.ErrorMessages;
import com.octl2.api.commons.exception.OctException;
import com.octl2.api.consts.Const;
import com.octl2.api.consts.ExcelConst;
import com.octl2.api.dto.LogisticExcel;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ByteArrayResource;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExcelUtil {
    public static void writeHeaderRow(Sheet sheet, int rowIndex, int levelMapping) {
        int index = ExcelConst.ROW_START_INDEX;
        CellStyle cellStyle = createStyleForHeader(sheet);
        Row row = sheet.createRow(rowIndex);

        Cell cell = row.createCell(index++);
        switch (levelMapping) {
            case Const.LEVEL_MAPPING_ONE:
                cell.setCellStyle(cellStyle);
                cell.setCellValue(ExcelConst.PROVINCE_ID);
                cell = row.createCell(index++);
                cell.setCellStyle(cellStyle);
                cell.setCellValue(ExcelConst.PROVINCE_NAME);
                cell = row.createCell(index++);
                cell.setCellStyle(cellStyle);
                cell.setCellValue(ExcelConst.PROVINCE_CODE);
                break;
            case Const.LEVEL_MAPPING_TWO:
                cell.setCellStyle(cellStyle);
                cell.setCellValue(ExcelConst.PROVINCE_ID);
                cell = row.createCell(index++);
                cell.setCellStyle(cellStyle);
                cell.setCellValue(ExcelConst.PROVINCE_NAME);
                cell = row.createCell(index++);
                cell.setCellStyle(cellStyle);
                cell.setCellValue(ExcelConst.PROVINCE_CODE);
                cell = row.createCell(index++);
                cell.setCellStyle(cellStyle);
                cell.setCellValue(ExcelConst.DISTRICT_ID);
                cell = row.createCell(index++);
                cell.setCellStyle(cellStyle);
                cell.setCellValue(ExcelConst.DISTRICT_NAME);
                cell = row.createCell(index++);
                cell.setCellStyle(cellStyle);
                cell.setCellValue(ExcelConst.DISTRICT_CODE);
                break;
            case Const.LEVEL_MAPPING_THREE:
                cell.setCellStyle(cellStyle);
                cell.setCellValue(ExcelConst.PROVINCE_ID);
                cell = row.createCell(index++);
                cell.setCellStyle(cellStyle);
                cell.setCellValue(ExcelConst.PROVINCE_NAME);
                cell = row.createCell(index++);
                cell.setCellStyle(cellStyle);
                cell.setCellValue(ExcelConst.PROVINCE_CODE);
                cell = row.createCell(index++);
                cell.setCellStyle(cellStyle);
                cell.setCellValue(ExcelConst.DISTRICT_ID);
                cell = row.createCell(index++);
                cell.setCellStyle(cellStyle);
                cell.setCellValue(ExcelConst.DISTRICT_NAME);
                cell = row.createCell(index++);
                cell.setCellStyle(cellStyle);
                cell.setCellValue(ExcelConst.DISTRICT_CODE);
                cell = row.createCell(index++);
                cell.setCellStyle(cellStyle);
                cell.setCellValue(ExcelConst.SUB_DISTRICT_ID);
                cell = row.createCell(index++);
                cell.setCellStyle(cellStyle);
                cell.setCellValue(ExcelConst.SUB_DISTRICT_NAME);
                cell = row.createCell(index++);
                cell.setCellStyle(cellStyle);
                cell.setCellValue(ExcelConst.SUB_DISTRICT_CODE);
                break;
            default:
                throw new OctException(ErrorMessages.LEVEL_MAPPING_NOT_ALLOWED);
        }
        cell = row.createCell(index++);
        cell.setCellStyle(cellStyle);
        cell.setCellValue(ExcelConst.FULFILMENT_ID);
        cell = row.createCell(index++);
        cell.setCellStyle(cellStyle);
        cell.setCellValue(ExcelConst.FULFILMENT_NAME);
        cell = row.createCell(index++);
        cell.setCellStyle(cellStyle);
        cell.setCellValue(ExcelConst.LAST_MILE_ID);
        cell = row.createCell(index++);
        cell.setCellStyle(cellStyle);
        cell.setCellValue(ExcelConst.LAST_MILE_NAME);
        cell = row.createCell(index++);
        cell.setCellStyle(cellStyle);
        cell.setCellValue(ExcelConst.WAREHOUSE_ID);
        cell = row.createCell(index);
        cell.setCellStyle(cellStyle);
        cell.setCellValue(ExcelConst.WAREHOUSE_NAME);
    }

    public static void writeDataRows(LogisticExcel logisticExcel, Row row, int levelMapping) {
        int index = ExcelConst.ROW_START_INDEX;

        Cell cell = row.createCell(index++);
        switch (levelMapping) {
            case Const.LEVEL_MAPPING_ONE:
                cell.setCellValue(validDataNull(logisticExcel.getProvinceId()));
                cell = row.createCell(index++);
                cell.setCellValue(logisticExcel.getProvinceName());
                cell = row.createCell(index++);
                cell.setCellValue(logisticExcel.getProvinceCode());
                break;
            case Const.LEVEL_MAPPING_TWO:
                cell.setCellValue(validDataNull(logisticExcel.getProvinceId()));
                cell = row.createCell(index++);
                cell.setCellValue(logisticExcel.getProvinceName());
                cell = row.createCell(index++);
                cell.setCellValue(logisticExcel.getProvinceCode());
                cell = row.createCell(index++);
                cell.setCellValue(validDataNull(logisticExcel.getDistrictId()));
                cell = row.createCell(index++);
                cell.setCellValue(logisticExcel.getDistrictName());
                cell = row.createCell(index++);
                cell.setCellValue(logisticExcel.getDistrictCode());
                break;
            case Const.LEVEL_MAPPING_THREE:
                cell.setCellValue(validDataNull(logisticExcel.getProvinceId()));
                cell = row.createCell(index++);
                cell.setCellValue(logisticExcel.getProvinceName());
                cell = row.createCell(index++);
                cell.setCellValue(logisticExcel.getProvinceCode());
                cell = row.createCell(index++);
                cell.setCellValue(validDataNull(logisticExcel.getDistrictId()));
                cell = row.createCell(index++);
                cell.setCellValue(logisticExcel.getDistrictName());
                cell = row.createCell(index++);
                cell.setCellValue(logisticExcel.getDistrictCode());
                cell = row.createCell(index++);
                cell.setCellValue(validDataNull(logisticExcel.getSubDistrictId()));
                cell = row.createCell(index++);
                cell.setCellValue(logisticExcel.getSubDistrictName());
                cell = row.createCell(index++);
                cell.setCellValue(logisticExcel.getSubDistrictCode());
                break;
            default:
                throw new OctException(ErrorMessages.LEVEL_MAPPING_NOT_ALLOWED);
        }
        cell = row.createCell(index++);
        cell.setCellValue(validDataNull(logisticExcel.getFulfilmentId()));
        cell = row.createCell(index++);
        cell.setCellValue(logisticExcel.getFulfilmentName());

        cell = row.createCell(index++);
        cell.setCellValue(validDataNull(logisticExcel.getLastMileId()));
        cell = row.createCell(index++);
        cell.setCellValue(logisticExcel.getLastMileName());

        cell = row.createCell(index++);
        cell.setCellValue(validDataNull(logisticExcel.getWareHouseId()));
        cell = row.createCell(index);
        cell.setCellValue(logisticExcel.getWareHouseName());
    }

    public static ByteArrayResource writeFileExcel(XSSFWorkbook workbook) throws IOException {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            workbook.write(outputStream);
            return new ByteArrayResource(outputStream.toByteArray());
        }
    }

    public static CellStyle createStyleForHeader(Sheet sheet) {
        // Create font
        Font font = sheet.getWorkbook().createFont();
        font.setFontName("Times New Roman");
        font.setBold(true);
        font.setFontHeightInPoints((short) 12); // font size
        font.setColor(IndexedColors.WHITE.getIndex()); // text color

        // Create CellStyle
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setFillForegroundColor(IndexedColors.AUTOMATIC.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        return cellStyle;
    }

    private static String validDataNull(Object data) {
        return (data != null) ? String.valueOf(data) : "";
    }
}
