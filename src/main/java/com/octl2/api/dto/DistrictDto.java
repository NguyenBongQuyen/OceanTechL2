package com.octl2.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DistrictDto extends LogisticDto {
    private Integer id;
    private Integer provinceId;
    private String name;
    private String code;

    public DistrictDto(ResultSetQuery resultSetQuery) {
        if (resultSetQuery == null) {
            return;
        }
        this.id = resultSetQuery.getDistrictId();
        this.provinceId = resultSetQuery.getProvinceId();
        this.name = resultSetQuery.getDistrictName();
        this.code = resultSetQuery.getDistrictCode();
        super.setFulfilmentDtoSet(new HashSet<>());
        super.setLastMileDtoSet(new HashSet<>());
        super.setWarehouseDtoSet(new HashSet<>());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DistrictDto that = (DistrictDto) o;
        return Objects.equals(id, that.id)
                && Objects.equals(provinceId, that.provinceId)
                && Objects.equals(name, that.name)
                && Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, code);
    }
}
