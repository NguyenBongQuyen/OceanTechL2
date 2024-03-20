package com.octl2.api.dto;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FulfilmentDto {
    private Integer id;
    private String name;
    private String shortname;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FulfilmentDto that = (FulfilmentDto) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(shortname, that.shortname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, shortname);
    }
}
