package com.thanhdao.course_online.entity;

import com.thanhdao.course_online.entity.common.BaseSoftDeleteEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "`order`")
public class Order extends BaseSoftDeleteEntity {

    @Column(length = Integer.MAX_VALUE)
    private String paymentMethod;
    @Column
    private Boolean paymentStatus;
    @Column(length = Integer.MAX_VALUE)
    private String coupon;
    @Column(length = Integer.MAX_VALUE)
    private String paymentId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private Set<OrderDetail> orderDetails;

}
