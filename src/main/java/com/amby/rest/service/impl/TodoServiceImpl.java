package com.amby.rest.service.impl;

import com.amby.rest.repository.TodoRepository;
import com.amby.rest.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    TodoRepository todoRepository;

}
