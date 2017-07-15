package com.github.dmn1k.jaxrsdemo;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
@Startup
public class Bootstrapper {
    private static final Logger LOGGER = LoggerFactory.getLogger(Bootstrapper.class);
    
    @PersistenceContext(unitName = "todos")
    private EntityManager em;

    @PostConstruct
    public void init() {
        addItem("Do laundry");
        addItem("Workout");
    }

    private void addItem(String desc) {
        TodoItem item = new TodoItem(desc);
        em.persist(item);
        
        // flush to force persist and get generated id
        em.flush();
        LOGGER.info("Persisted todo item with id {}", item.getId());
    }
}
