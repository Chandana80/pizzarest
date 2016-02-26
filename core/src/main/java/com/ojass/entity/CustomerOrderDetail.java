package com.ojass.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class CustomerOrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String toppings;

    @ManyToOne(cascade =  CascadeType.MERGE)
    private Base base;

    @Column
    private BigDecimal total;

     public CustomerOrderDetail() {
    }

    @Override
    public String toString() {
        return "CustomerOrderDetail{" +
                "id=" + id +
                ", toppings='" + toppings + '\'' +
                ", total='" + total + '\'' +
//                ", base='" + base.toString() + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToppings() {
        return toppings;
    }

    public void setToppings(String toppings) {
        this.toppings = toppings;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Base getBase() {
        return base;
    }

    public void setBase(Base base) {
        this.base = base;
    }
}
