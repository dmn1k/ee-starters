package com.github.dmn1k.dbdemo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class TodoItem implements Serializable {

    @Id
    @GeneratedValue(generator = "todo_seq_gen")
    @SequenceGenerator(name = "todo_seq_gen", sequenceName = "TODO_SEQ", allocationSize = 1)
    private long id;

    private String description;

    public TodoItem() {
        // for jpa
    }

    public TodoItem(String description) {
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
