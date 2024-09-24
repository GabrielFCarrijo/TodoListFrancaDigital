package com.todoList.fatec.service;

import com.todoList.fatec.repository.dto.TodoDTO;
import com.todoList.fatec.repository.entity.Todo;
import com.todoList.fatec.repository.form.TodoForm;
import com.todoList.fatec.service.mapper.TodoMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class TodoMapperTest {

    private TodoMapper todoMapper;

    @BeforeEach
    void setUp() {
        todoMapper = new TodoMapper();
    }

    @Test
    @DisplayName("Deve converter Todo para TodoDTO")
    void deveConverterTodoParaTodoDTO() {
        Todo todo = Todo.builder()
                .id(1L)
                .nome("Todo Teste")
                .descricao("Descrição do Todo Teste")
                .status("Pendente")
                .observacoes("Sem observações")
                .dataCriacao(LocalDateTime.now())
                .dataAtualizacao(LocalDateTime.now())
                .build();

        TodoDTO todoDTO = todoMapper.paraDTO(todo);

        assertNotNull(todoDTO);
        assertEquals(todo.getId(), todoDTO.getId());
        assertEquals(todo.getNome(), todoDTO.getNome());
        assertEquals(todo.getDescricao(), todoDTO.getDescricao());
        assertEquals(todo.getStatus(), todoDTO.getStatus());
        assertEquals(todo.getObservacoes(), todoDTO.getObservacoes());
        assertEquals(todo.getDataCriacao(), todoDTO.getDataCriacao());
        assertEquals(todo.getDataAtualizacao(), todoDTO.getDataAtualizacao());
    }

    @Test
    @DisplayName("Deve converter lista de Todo para lista de TodoDTO")
    void deveConverterListaDeTodoParaListaDeTodoDTO() {
        Todo todo1 = Todo.builder()
                .id(1L)
                .nome("Todo 1")
                .descricao("Descrição do Todo 1")
                .status("Pendente")
                .observacoes("Observação 1")
                .dataCriacao(LocalDateTime.now())
                .dataAtualizacao(LocalDateTime.now())
                .build();

        Todo todo2 = Todo.builder()
                .id(2L)
                .nome("Todo 2")
                .descricao("Descrição do Todo 2")
                .status("Concluído")
                .observacoes("Observação 2")
                .dataCriacao(LocalDateTime.now())
                .dataAtualizacao(LocalDateTime.now())
                .build();

        List<Todo> todos = List.of(todo1, todo2);

        List<TodoDTO> todoDTOs = todoMapper.paraListDTO(todos);

        assertNotNull(todoDTOs);
        assertEquals(2, todoDTOs.size());
        assertEquals(todo1.getNome(), todoDTOs.get(0).getNome());
        assertEquals(todo2.getNome(), todoDTOs.get(1).getNome());
    }

    @Test
    @DisplayName("Deve converter TodoForm para Todo")
    void deveConverterTodoFormParaTodo() {
        TodoForm todoForm = TodoForm.builder()
                .id(1L)
                .nome("Todo Form Teste")
                .descricao("Descrição do Todo Form Teste")
                .status("Pendente")
                .observacoes("Sem observações")
                .dataCriacao(LocalDateTime.now())
                .dataAtualizacao(LocalDateTime.now())
                .build();

        Todo todo = todoMapper.paraEntity(todoForm);

        assertNotNull(todo);
        assertEquals(todoForm.getId(), todo.getId());
        assertEquals(todoForm.getNome(), todo.getNome());
        assertEquals(todoForm.getDescricao(), todo.getDescricao());
        assertEquals(todoForm.getStatus(), todo.getStatus());
        assertEquals(todoForm.getObservacoes(), todo.getObservacoes());
        assertEquals(todoForm.getDataCriacao(), todo.getDataCriacao());
        assertEquals(todoForm.getDataAtualizacao(), todo.getDataAtualizacao());
    }

    @Test
    @DisplayName("Deve converter lista de TodoForm para lista de Todo")
    void deveConverterListaDeTodoFormParaListaDeTodo() {
        TodoForm todoForm1 = TodoForm.builder()
                .id(1L)
                .nome("Todo Form 1")
                .descricao("Descrição do Todo Form 1")
                .status("Pendente")
                .observacoes("Observação 1")
                .dataCriacao(LocalDateTime.now())
                .dataAtualizacao(LocalDateTime.now())
                .build();

        TodoForm todoForm2 = TodoForm.builder()
                .id(2L)
                .nome("Todo Form 2")
                .descricao("Descrição do Todo Form 2")
                .status("Concluído")
                .observacoes("Observação 2")
                .dataCriacao(LocalDateTime.now())
                .dataAtualizacao(LocalDateTime.now())
                .build();

        List<TodoForm> todoForms = List.of(todoForm1, todoForm2);

        List<Todo> todos = todoMapper.paraListEntity(todoForms);

        assertNotNull(todos);
        assertEquals(2, todos.size());
        assertEquals(todoForm1.getNome(), todos.get(0).getNome());
        assertEquals(todoForm2.getNome(), todos.get(1).getNome());
    }

    @Test
    @DisplayName("Deve converter TodoForm para TodoDTO")
    void deveConverterTodoFormParaTodoDTO() {
        TodoForm todoForm = TodoForm.builder()
                .id(1L)
                .nome("Todo Form Teste")
                .descricao("Descrição do Todo Form Teste")
                .status("Pendente")
                .observacoes("Sem observações")
                .dataCriacao(LocalDateTime.now())
                .dataAtualizacao(LocalDateTime.now())
                .build();

        TodoDTO todoDTO = todoMapper.paraDTOFromForm(todoForm);

        assertNotNull(todoDTO);
        assertEquals(todoForm.getId(), todoDTO.getId());
        assertEquals(todoForm.getNome(), todoDTO.getNome());
        assertEquals(todoForm.getDescricao(), todoDTO.getDescricao());
        assertEquals(todoForm.getStatus(), todoDTO.getStatus());
        assertEquals(todoForm.getObservacoes(), todoDTO.getObservacoes());
        assertEquals(todoForm.getDataCriacao(), todoDTO.getDataCriacao());
        assertEquals(todoForm.getDataAtualizacao(), todoDTO.getDataAtualizacao());
    }
}
