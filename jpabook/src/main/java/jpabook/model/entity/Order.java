package jpabook.model.entity;

import jpabook.common.OrderStatus;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "ORDERS")
@Data
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;

    @Column(name = "MEMBER_ID")
    private Long memberId;

    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;
}
