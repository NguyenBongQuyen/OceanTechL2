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
@Table(name = "bp_partner")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Partner {
    @Id
    @Column(name = "partner_id")
    private Integer id;

    @Column(name = "partner_type")
    private Integer partnerType;

    @Column(name = "name")
    private String name;

    @Column(name = "shortname")
    private String shortname;

}
