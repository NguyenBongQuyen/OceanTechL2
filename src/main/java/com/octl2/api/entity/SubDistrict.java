package com.octl2.api.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "lc_subdistrict")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubDistrict {
    @Id
    @Column(name = "subdistrict_id")
    private Integer id;

    @Column(name = "district_id")
    private Integer districtId;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

}
