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
@Table(name = "cf_default_delivery")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Delivery {
    @Id
    @Column(name = "cf_default_do_id")
    private Integer id;

    @Column(name = "location_id")
    private Integer locationId;

    @Column(name = "fulfilment_id")
    private Integer fulfilmentId;

    @Column(name = "lastmile_id")
    private Integer lastMileId;

    @Column(name = "warehouse_id")
    private Integer warehouseId;

}

