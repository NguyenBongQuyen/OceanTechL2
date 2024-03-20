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
public class SubDistrictDto extends LogisticDto {
    private Integer id;
    private Integer districtId;
    private String name;
    private String code;

    public SubDistrictDto(ResultSetQuery resultSetQuery) {
        if (resultSetQuery == null) {
            return;
        }
        this.id = resultSetQuery.getSubDistrictId();
        this.districtId = resultSetQuery.getDistrictId();
        this.name = resultSetQuery.getSubDistrictName();
        this.code = resultSetQuery.getSubDistrictCode();
        super.setFulfilmentDtoSet(new HashSet<>());
        super.setLastMileDtoSet(new HashSet<>());
        super.setWarehouseDtoSet(new HashSet<>());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubDistrictDto that = (SubDistrictDto) o;
        return Objects.equals(id, that.id)
                && Objects.equals(districtId, that.districtId)
                && Objects.equals(name, that.name)
                && Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, code);
    }
}
