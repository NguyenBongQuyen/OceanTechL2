package com.octl2.api.commons.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMessages implements ErrorMessage {
    SUCCESS(200, "Success"),

    BAD_REQUEST(400, "Bad request"),
    INVALID_VALUE(400_001, "Invalid value"),
    SAVE_DATABASE_ERROR(400_002, "Save database error"),
    EXPORT_EXCEL_ERROR(400_003, "Export excel error"),
    NOT_FOUND(404, "Resource not found"),
    NOT_NULL(404, "Resource not null"),
    PROVINCE_ID_NOT_FOUND(404_001, "Province does not exist"),
    DISTRICT_ID_NOT_FOUND(404_002, "District does not exist"),
    SUB_DISTRICT_ID_NOT_FOUND(404_003, "SubDistrict does not exist"),
    FULFILMENT_ID_NOT_FOUND(404_004, "Fulfilment does not exist"),
    LAST_MILE_ID_NOT_FOUND(404_005, "LastMile does not exist"),
    WAREHOUSE_ID_NOT_FOUND(404_006, "Warehouse does not exist"),
    LOCATION_ID_NOT_FOUND(404_007, "Location does not exist"),
    LEVEL_MAPPING_NOT_ALLOWED(405_001, "Level mapping not allowed"),
    LOCATION_SMALLER_LEVEL_MAPPING(405_002, "Location smaller than level mapping"),;

    private final int code;
    private final String message;
}
