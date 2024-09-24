package com.todoList.fatec.service;


import com.todoList.fatec.repository.TodoRepository;
import com.todoList.fatec.repository.dto.TodoDTO;
import com.todoList.fatec.repository.entity.Todo;
import com.todoList.fatec.service.mapper.TodoMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoConsultaDinamicaService {

    @Lazy
    private final TodoRepository todoRepository;
    @Lazy
    private final TodoMapper todoMapper;

    public TodoConsultaDinamicaService(TodoRepository todoRepository, TodoMapper todoMapper) {
        this.todoRepository = todoRepository;
        this.todoMapper = todoMapper;
    }

    public List<TodoDTO> buscarPorNome(String nome) {
        List<Todo> todos = todoRepository.findByNomeContainingIgnoreCase(nome);
        return todoMapper.paraListDTO(todos);
    }

}
