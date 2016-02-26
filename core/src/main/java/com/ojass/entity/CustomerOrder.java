package com.ojass.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class CustomerOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String contact;

    @Column
    private String name;

    @Column
    private BigDecimal total;

    @OneToOne(cascade = CascadeType.ALL)
    private CustomerOrderDetail orderDetail;

    @Column
    private int qty;

    public CustomerOrder() {
    }

    @Override
    public String toString() {
        return "CustomerOrder{" +
                "id=" + id +
                ", contact='" + contact + '\'' +
                ", qty='" + qty + '\'' +
                ", name='" + name + '\'' +
                ", total='" + total + '\'' +
                ", orderDetail='" + orderDetail.toString() + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CustomerOrderDetail getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(CustomerOrderDetail orderDetail) {
        this.orderDetail = orderDetail;
    }
}
