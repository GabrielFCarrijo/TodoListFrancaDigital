package com.todoList.fatec.service;

import com.todoList.fatec.repository.TodoRepository;
import com.todoList.fatec.repository.dto.TodoDTO;
import com.todoList.fatec.repository.entity.Todo;
import com.todoList.fatec.service.mapper.TodoMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Lazy;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class TodoConsultaDinamicaServiceTest {

    @Autowired
    @Lazy
    private TodoConsultaDinamicaService todoConsultaDinamicaService;

    @MockBean
    private TodoRepository todoRepository;

    @MockBean
    private TodoMapper todoMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Deve retornar lista de Todos ao buscar pelo nome")
    void deveBuscarPorNome() {
        String nomeBusca = "Todo";

        Todo todo1 = new Todo(1L, "Todo 1", "Descrição 1", "Pendente", null, null, null);
        Todo todo2 = new Todo(2L, "Todo 2", "Descrição 2", "Concluído", null, null, null);

        List<Todo> todos = List.of(todo1, todo2);

        when(todoRepository.findByNomeContainingIgnoreCase(nomeBusca)).thenReturn(todos);

        List<TodoDTO> todoDTOs = List.of(
                new TodoDTO(1L, "Todo 1", "Descrição 1", "Pendente", null, null, null),
                new TodoDTO(2L, "Todo 2", "Descrição 2", "Concluído", null, null, null)
        );
        when(todoMapper.paraListDTO(todos)).thenReturn(todoDTOs);

        List<TodoDTO> resultado = todoConsultaDinamicaService.buscarPorNome(nomeBusca);

        assertEquals(2, resultado.size());
        assertEquals("Todo 1", resultado.get(0).getNome());
        assertEquals("Todo 2", resultado.get(1).getNome());
    }

    @Test
    @DisplayName("Deve retornar lista vazia ao buscar por nome inexistente")
    void deveRetornarListaVaziaAoBuscarNomeInexistente() {
        String nomeBusca = "Inexistente";

        List<Todo> todos = new ArrayList<>();

        when(todoRepository.findByNomeContainingIgnoreCase(nomeBusca)).thenReturn(todos);

        when(todoMapper.paraListDTO(todos)).thenReturn(new ArrayList<>());

        List<TodoDTO> resultado = todoConsultaDinamicaService.buscarPorNome(nomeBusca);

        assertEquals(0, resultado.size());
    }
}
