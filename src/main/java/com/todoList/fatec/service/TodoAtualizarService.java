package com.todoList.fatec.service;

import com.todoList.fatec.regraexception.RegraNegocioException;
import com.todoList.fatec.repository.TodoRepository;
import com.todoList.fatec.repository.dto.TodoDTO;
import com.todoList.fatec.repository.entity.Todo;
import com.todoList.fatec.repository.form.TodoForm;
import com.todoList.fatec.service.mapper.TodoMapper;
import com.todoList.fatec.service.validadores.ValidarDadosUsuarioService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TodoAtualizarService {

    @Lazy
    private final TodoRepository todoRepository;
    @Lazy
    private final TodoMapper todoMapper;
    @Lazy
    private final ValidarDadosUsuarioService validarDadosUsuarioService;

    public TodoAtualizarService(TodoRepository todoRepository, TodoMapper todoMapper, ValidarDadosUsuarioService validarDadosUsuarioService) {
        this.todoRepository = todoRepository;
        this.todoMapper = todoMapper;
        this.validarDadosUsuarioService = validarDadosUsuarioService;
    }

    public TodoDTO atualizarTodo(Long id, TodoForm todoForm) {
        validarDadosUsuarioService.validar(todoForm);

        Optional<Todo> todoOptional = todoRepository.findById(id);

        if (todoOptional.isEmpty()) {
            throw new RegraNegocioException("Todo não encontrado");
        }

        Todo todo = todoOptional.get();
        todo.setNome(todoForm.getNome());
        todo.setDescricao(todoForm.getDescricao());
        todo.setStatus(todoForm.getStatus());
        todo.setObservacoes(todoForm.getObservacoes());
        todo.setDataAtualizacao(LocalDateTime.now());

        Todo updatedTodo = todoRepository.save(todo);

        return todoMapper.paraDTO(updatedTodo);
    }
}
