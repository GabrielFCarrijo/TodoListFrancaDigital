package com.todoList.fatec.service;

import com.todoList.fatec.repository.TodoRepository;
import com.todoList.fatec.repository.dto.TodoDTO;
import com.todoList.fatec.repository.entity.Todo;
import com.todoList.fatec.repository.form.TodoForm;
import com.todoList.fatec.service.validadores.ValidarDadosUsuarioService;
import com.todoList.fatec.service.mapper.TodoMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoInserirService {

    @Lazy
    private final TodoRepository todoRepository;
    @Lazy
    private final TodoMapper todoMapper;
    @Lazy
    private final ValidarDadosUsuarioService validarDadosUsuarioService;

    public TodoInserirService(TodoRepository todoRepository, TodoMapper todoMapper, ValidarDadosUsuarioService validarDadosUsuarioService) {
        this.todoRepository = todoRepository;
        this.todoMapper = todoMapper;
        this.validarDadosUsuarioService = validarDadosUsuarioService;
    }

    public List<TodoDTO> inserirTodoList(List<TodoForm> todoForms) {
        validarDadosUsuarioService.validarList(todoForms);

        List<Todo> todos = todoMapper.paraListEntity(todoForms);
        List<Todo> savedTodos = todoRepository.saveAll(todos);

        return savedTodos.stream()
                .map(todoMapper::paraDTO)
                .collect(Collectors.toList());
    }
}
