package com.ojass.controller;

import com.ojass.entity.Topping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Controller
@Transactional
public class ToppingsController {

    @PersistenceContext
    private EntityManager em;

	private static final Logger logger = LoggerFactory.getLogger(ToppingsController.class);

    @RequestMapping(value = "/toppings/{toppingId}", method = RequestMethod.GET)
    @ResponseBody
	public Topping get(@PathVariable Long toppingId) {
        logger.info("Fetching topping with id: {}", toppingId);
        Topping topping = em.find(Topping.class, toppingId);
        logger.info("Found topping: {}", topping);
        return topping;
    }

    @RequestMapping(value = "/toppings", method = RequestMethod.GET)
    @ResponseBody
    public List<Topping> get() {
        logger.info("Fetching all toppings");
        Query query = em.createQuery("Select t from Topping t", Topping.class);
        return query.getResultList();
    }

    @RequestMapping(value = "/toppings", method = RequestMethod.POST)
    public ResponseEntity<String> create(@RequestBody Topping topping) {
        logger.info("Creating new topping: {}", topping);
        em.persist(topping);
        logger.info("Created topping: {}", topping);
        return new ResponseEntity<String>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/toppings/{toppingId}", method = RequestMethod.PUT)
    public ResponseEntity<String> update(@RequestBody Topping topping, @PathVariable Long toppingId) {

        logger.info("update topping: {}", toppingId);
        logger.info("Fetching topping with id: {}", toppingId);
        Topping existingTopping = em.find(Topping.class, toppingId);
        if (existingTopping == null){
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
        logger.info("Found topping: {}", existingTopping);
        em.merge(topping);
        return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
    }


    @RequestMapping(value = "/toppings/{toppingId}", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@PathVariable Long toppingId) {

        logger.info("delete topping: {}", toppingId);
        Topping existingTopping = em.find(Topping.class, toppingId);
        if (existingTopping == null){
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
        logger.info("Found topping: {}", existingTopping);
        em.remove(existingTopping);
        return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
    }
}
