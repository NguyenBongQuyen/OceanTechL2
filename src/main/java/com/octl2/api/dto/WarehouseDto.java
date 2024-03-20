package com.octl2.api.dto;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseDto {
    private Integer id;
    private String name;
    private String shortname;

    public WarehouseDto(ResultSetQuery resultSetQuery) {
        if (resultSetQuery == null) {
            return;
        }
        this.id = resultSetQuery.getWarehouseId();
        this.name = resultSetQuery.getWareHouseName();
        this.shortname = resultSetQuery.getWareHouseShortname();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WarehouseDto that = (WarehouseDto) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(shortname, that.shortname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, shortname);
    }
}
