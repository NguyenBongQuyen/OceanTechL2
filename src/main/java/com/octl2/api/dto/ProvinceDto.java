package com.octl2.api.dto;

import lombok.*;

import java.util.HashSet;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProvinceDto extends LogisticDto {
    private Integer id;
    private String name;
    private String code;

    public ProvinceDto(ResultSetQuery resultSetQuery) {
        if (resultSetQuery == null) {
            return;
        }
        this.id = resultSetQuery.getProvinceId();
        this.name = resultSetQuery.getProvinceName();
        this.code = resultSetQuery.getProvinceCode();
        super.setFulfilmentDtoSet(new HashSet<>());
        super.setLastMileDtoSet(new HashSet<>());
        super.setWarehouseDtoSet(new HashSet<>());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProvinceDto that = (ProvinceDto) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, code);
    }
}
