package com.ojass.controller;

import com.ojass.entity.Base;
import com.ojass.entity.CustomerOrder;
import com.ojass.entity.Topping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;

@Controller
@Transactional
public class OrdersController {

    @PersistenceContext
    private EntityManager em;

	private static final Logger logger = LoggerFactory.getLogger(OrdersController.class);

    @RequestMapping(value = "/orders", method = RequestMethod.POST)
    public ResponseEntity<String> create(@RequestBody  CustomerOrder order) {
        logger.info("Creating new order: {}", order);

        if (!validateOrder(order)) {
            return new ResponseEntity<String>(HttpStatus.NOT_ACCEPTABLE);
        }
        if (!processInventory(order)){
            return new ResponseEntity<String>(HttpStatus.NOT_ACCEPTABLE);
        }
        persisDetails(order);

        logger.info("Created order: {}", order);
        return new ResponseEntity<String>(HttpStatus.CREATED);
    }

    private boolean validateOrder(CustomerOrder order) {
        boolean isOrder = true;
        logger.info("validating order");
        if (order.getOrderDetail() == null){
            isOrder = false;
            logger.info("order detail is empty");
        }
        return isOrder;
    }

    private boolean processInventory(CustomerOrder order){
        boolean inventoryExists = true;
        logger.info("validating base::"+order.getOrderDetail().getBase().getId());
        if (order.getOrderDetail().getBase()!= null){
            logger.info("base exists");
            Base base = em.find(Base.class, order.getOrderDetail().getBase().getId());
            if (base == null || base.getQty() - order.getQty() <0) {
                inventoryExists = false;
                logger.info("base validation failed");
            } else {
                order.getOrderDetail().setBase(base);
            }
        }
        String toppings = order.getOrderDetail().getToppings();
        String[] toppingsArray = toppings.split(",");
        for (String toppingId:toppingsArray){
            Topping topping = em.find(Topping.class, Long.valueOf(toppingId.trim()));
            if (topping.getQty().subtract(topping.getQtyPerServing()).compareTo(BigDecimal.ZERO) <= 0){
                inventoryExists = false;
                logger.info("topping validation failed");
                break;
            }
        }
        return inventoryExists;
    }

    private void persisDetails(CustomerOrder order){
        logger.info("persisting details");
        //update base inventory
        order.getOrderDetail().getBase().setQty(order.getOrderDetail().getBase().getQty() - order.getQty());

        //update toppings inventory
        String toppings = order.getOrderDetail().getToppings();
        String[] toppingsArray = toppings.split(",");
        for (String toppingId:toppingsArray){
            Topping topping = em.find(Topping.class, Long.valueOf(toppingId.trim()));
            topping.setQty(topping.getQty().subtract(topping.getQtyPerServing()));
            em.merge(topping);
        }
        em.persist(order);
    }
}
