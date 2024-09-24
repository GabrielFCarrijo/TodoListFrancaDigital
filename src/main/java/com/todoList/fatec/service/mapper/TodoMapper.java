package com.todoList.fatec.service.mapper;

import com.todoList.fatec.repository.dto.TodoDTO;
import com.todoList.fatec.repository.entity.Todo;
import com.todoList.fatec.repository.form.TodoForm;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TodoMapper {

    public TodoDTO paraDTO(Todo todo) {
        return TodoDTO.builder()
                .id(todo.getId())
                .nome(todo.getNome())
                .descricao(todo.getDescricao())
                .status(todo.getStatus())
                .observacoes(todo.getObservacoes())
                .dataCriacao(todo.getDataCriacao())
                .dataAtualizacao(todo.getDataAtualizacao())
                .build();
    }

    public List<TodoDTO> paraListDTO(List<Todo> todos) {
        return todos.stream()
                .map(this::paraDTO)
                .collect(Collectors.toList());
    }

    public Todo paraEntity(TodoForm todoForm) {
        return Todo.builder()
                .id(todoForm.getId())
                .nome(todoForm.getNome())
                .descricao(todoForm.getDescricao())
                .status(todoForm.getStatus())
                .observacoes(todoForm.getObservacoes())
                .dataCriacao(LocalDateTime.now())
                .dataAtualizacao(todoForm.getDataAtualizacao())
                .build();
    }

    public List<Todo> paraListEntity(List<TodoForm> todosForm) {
        return todosForm.stream()
                .map(this::paraEntity)
                .collect(Collectors.toList());
    }

    public TodoDTO paraDTOFromForm(TodoForm todoForm) {
        return TodoDTO.builder()
                .id(todoForm.getId())
                .nome(todoForm.getNome())
                .descricao(todoForm.getDescricao())
                .status(todoForm.getStatus())
                .observacoes(todoForm.getObservacoes())
                .dataCriacao(todoForm.getDataCriacao())
                .dataAtualizacao(todoForm.getDataAtualizacao())
                .build();
    }
}
