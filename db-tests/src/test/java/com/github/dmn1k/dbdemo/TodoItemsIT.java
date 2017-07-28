package com.github.dmn1k.dbdemo;

import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class TodoItemsIT {
    @Rule
    public DatabaseRule database = new DatabaseRule();
    
    private TodoItems todoItems;
    
    @Before
    public void init() {
        todoItems = new TodoItems(database.getEntityManager());
    }
    
    @Test
    public void persistAndLoadRoundtrip(){
        todoItems.persist(new TodoItem("42"));
        
        // "flush" to force write to db and "clear" to clear 1st level cache and thus force actual read from db
        database.getEntityManager().flush();
        database.getEntityManager().clear();
        
        List<TodoItem> result = todoItems.getAll();
        
        assertEquals(1, result.size());
        assertEquals("42", result.get(0).getDescription());
    }
}
