package com.ojass.controller;

import com.ojass.entity.Base;
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
public class BasesController {

    @PersistenceContext
    private EntityManager em;

	private static final Logger logger = LoggerFactory.getLogger(BasesController.class);

    @RequestMapping(value = "/bases/{baseId}", method = RequestMethod.GET)
    @ResponseBody
	public Base get(@PathVariable Long baseId) {
        logger.info("Fetching base with id: {}", baseId);
        Base base = em.find(Base.class, baseId);
        logger.info("Found base: {}", base);
        return base;
    }

    @RequestMapping(value = "/bases", method = RequestMethod.GET)
    @ResponseBody
    public List<Base> get() {
        logger.info("Fetching all bases");
        Query query = em.createQuery("Select t from Base t", Base.class);
        return query.getResultList();
    }

    @RequestMapping(value = "/bases", method = RequestMethod.POST)
    public ResponseEntity<String> create(@RequestBody Base base) {
        logger.info("Creating new base: {}", base);
        em.persist(base);
        logger.info("Created base: {}", base);
        return new ResponseEntity<String>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/bases/{baseId}", method = RequestMethod.PUT)
    public ResponseEntity<String> update(@RequestBody Base base, @PathVariable Long baseId) {

        logger.info("update base: {}", baseId);
        logger.info("Fetching base with id: {}", baseId);
        Base existingBase = em.find(Base.class, baseId);
        if (existingBase == null){
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
        logger.info("Found base: {}", existingBase);
        em.merge(base);
        return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
    }


    @RequestMapping(value = "/bases/{baseId}", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@PathVariable Long baseId) {

        logger.info("delete base: {}", baseId);
        Base existingBase = em.find(Base.class, baseId);
        if (existingBase == null){
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
        logger.info("Found base: {}", existingBase);
        em.remove(existingBase);
        return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
    }
}
