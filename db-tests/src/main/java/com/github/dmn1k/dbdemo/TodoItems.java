package com.github.dmn1k.dbdemo;

import java.util.List;
import java.util.Optional;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class TodoItems {
    
    @PersistenceContext(unitName = "todos")
    private EntityManager em;

    public TodoItems(EntityManager em) {
        this.em = em;
    }

    public TodoItems() {
    }
    
    
    public Optional<TodoItem> find(long id){
        return Optional.ofNullable(em.find(TodoItem.class, id));
    }
    
    public List<TodoItem> getAll(){
        return em.createQuery("SELECT t FROM TodoItem t", TodoItem.class).getResultList();
    }
    
    public void persist(TodoItem item){
        em.persist(item);
    }
}
