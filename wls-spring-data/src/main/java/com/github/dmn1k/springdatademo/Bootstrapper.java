package com.github.dmn1k.springdatademo;

import com.github.dmn1k.springdatademo.model.TodoItem;
import com.github.dmn1k.springdatademo.service.TodoItemRestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrapper implements ApplicationRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(Bootstrapper.class);
    
    @Autowired
    private TodoItemRestRepository todoItemRepo;
    
    @Override
    public void run(ApplicationArguments args) throws Exception {
        addItem("Do laundry");
        addItem("Workout");
    }

    private void addItem(String desc) {
        TodoItem item = todoItemRepo.save(new TodoItem(desc));
        LOGGER.info("Persisted todo item with id {}", item.getId());
    }
}