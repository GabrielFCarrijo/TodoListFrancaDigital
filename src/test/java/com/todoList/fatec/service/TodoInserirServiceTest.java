package com.todoList.fatec.service;

import com.todoList.fatec.repository.TodoRepository;
import com.todoList.fatec.repository.dto.TodoDTO;
import com.todoList.fatec.repository.entity.Todo;
import com.todoList.fatec.repository.enums.StatusEnum;
import com.todoList.fatec.repository.form.TodoForm;
import com.todoList.fatec.service.mapper.TodoMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Lazy;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class TodoInserirServiceTest {

    @Autowired
    @Lazy
    TodoInserirService todoInserirService;
    @MockBean
    TodoRepository todoRepository;
    @MockBean
    TodoMapper todoMapper;

    @Test
    @DisplayName("Deve inserir todo list com sucesso")
    void deveInserirTodoListComSucesso () {
        TodoForm todoForm = TodoForm.builder()
                .id(1l)
                .nome("Todo")
                .descricao("Descrição do todo")
                .status(StatusEnum.CONCLUIDO.name())
                .dataAtualizacao(LocalDateTime.now())
                .dataCriacao(LocalDateTime.now())
                .build();

        Todo todoEntity = Todo.builder()
                .id(1l)
                .nome("Todo")
                .descricao("Descrição do todo")
                .status(StatusEnum.CONCLUIDO.name())
                .dataAtualizacao(LocalDateTime.now())
                .dataCriacao(LocalDateTime.now())
                .build();

        TodoDTO todoDTO = TodoDTO.builder()
                .id(1l)
                .nome("Todo")
                .descricao("Descrição do todo")
                .status(StatusEnum.CONCLUIDO.name())
                .dataAtualizacao(LocalDateTime.now())
                .dataCriacao(LocalDateTime.now())
                .build();

        when(todoMapper.paraListEntity(List.of(todoForm))).thenReturn(List.of(todoEntity));
        when(todoRepository.saveAll(List.of(todoEntity))).thenReturn(List.of(todoEntity));
        when(todoMapper.paraDTO(todoEntity)).thenReturn(todoDTO);

        var result = todoInserirService.inserirTodoList(List.of(todoForm));

        assertNotNull(result);
        verify(todoRepository).saveAll(List.of(todoEntity));
    }
}
