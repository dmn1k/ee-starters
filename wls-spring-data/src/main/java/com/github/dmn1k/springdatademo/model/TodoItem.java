package com.github.dmn1k.springdatademo.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TodoItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String description;

    public TodoItem() {
        // for jpa
    }

    @JsonCreator
    public TodoItem(@JsonProperty("id") long id, @JsonProperty("description") String description) {
        this.id = id;
        this.description = description;
    }

    
    public TodoItem(String description) {
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}
