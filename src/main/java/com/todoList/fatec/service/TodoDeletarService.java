package com.todoList.fatec.service;

import com.todoList.fatec.regraexception.RegraNegocioException;
import com.todoList.fatec.repository.TodoRepository;
import com.todoList.fatec.service.mapper.TodoMapper;
import com.todoList.fatec.service.validadores.ValidarDadosUsuarioService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class TodoDeletarService {

    @Lazy
    private final TodoRepository todoRepository;

    public TodoDeletarService(TodoRepository todoRepository, TodoMapper todoMapper, ValidarDadosUsuarioService validarDadosUsuarioService) {
        this.todoRepository = todoRepository;
    }

    public void deletar(Long id) {
        var todo = todoRepository.findById(id);
        if (todo.isEmpty()) {
            throw new RegraNegocioException("Todo não existente");
        }
        todoRepository.deleteById(id);
    }
}
