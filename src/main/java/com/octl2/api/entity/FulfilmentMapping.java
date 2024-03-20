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
@Table(name = "cf_mapping_ffm_lm")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FulfilmentMapping {
    @Id
    @Column(name = "cf_mapping_ffm_lm_id")
    private Integer id;

    @Column(name = "fulfilment_id")
    private Integer fulfilmentId;

    @Column(name = "lastmile_id")
    private Integer lastMileId;
}
