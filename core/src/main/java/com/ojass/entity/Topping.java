package com.ojass.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Topping {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String name;

    @Column
    private BigDecimal qty;

    @Column
    private BigDecimal qtyPerServing;

    @Column
    private BigDecimal pricePerServing;

    public Topping() {
    }

    @Override
    public String toString() {
        return "Topping{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    public BigDecimal getQtyPerServing() {
        return qtyPerServing;
    }

    public void setQtyPerServing(BigDecimal qtyPerServing) {
        this.qtyPerServing = qtyPerServing;
    }

    public BigDecimal getPricePerServing() {
        return pricePerServing;
    }

    public void setPricePerServing(BigDecimal pricePerServing) {
        this.pricePerServing = pricePerServing;
    }
}
