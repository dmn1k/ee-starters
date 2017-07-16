package com.github.dmn1k.springdatademo.service;

import com.github.dmn1k.springdatademo.model.TodoItem;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface TodoItemRestRepository extends PagingAndSortingRepository<TodoItem, Long> {
    List<TodoItem> findByDescription(@Param("description") String description);
}