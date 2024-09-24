package com.todoList.fatec.service;

import com.todoList.fatec.regraexception.RegraNegocioException;
import com.todoList.fatec.repository.TodoRepository;
import com.todoList.fatec.repository.dto.TodoDTO;
import com.todoList.fatec.repository.entity.Todo;
import com.todoList.fatec.repository.form.TodoForm;
import com.todoList.fatec.service.mapper.TodoMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class TodoAtualizarServiceTest {

    @Autowired
    private TodoAtualizarService todoAtualizarService;

    @MockBean
    private TodoRepository todoRepository;

    @MockBean
    private TodoMapper todoMapper;

    @Test
    @DisplayName("Deve atualizar todo com sucesso")
    void deveAtualizarTodoComSucesso() {
        TodoForm todoForm = TodoForm.builder()
                .id(1L)
                .nome("Todo Atualizado")
                .descricao("Descrição atualizada")
                .status("Pendente")
                .observacoes("Observações atualizadas")
                .build();

        Todo todoEntity = Todo.builder()
                .id(1L)
                .nome("Todo Antigo")
                .descricao("Descrição antiga")
                .status("Concluído")
                .observacoes("Sem observações")
                .dataAtualizacao(LocalDateTime.now())
                .build();

        TodoDTO todoDTO = TodoDTO.builder()
                .id(1L)
                .nome("Todo Atualizado")
                .descricao("Descrição atualizada")
                .status("Pendente")
                .observacoes("Observações atualizadas")
                .dataAtualizacao(LocalDateTime.now())
                .build();

        when(todoRepository.findById(1L)).thenReturn(Optional.of(todoEntity));
        when(todoMapper.paraDTO(any(Todo.class))).thenReturn(todoDTO);
        when(todoRepository.save(any(Todo.class))).thenReturn(todoEntity);

        TodoDTO result = todoAtualizarService.atualizarTodo(1L, todoForm);

        assertNotNull(result);
        assertEquals(todoForm.getNome(), result.getNome());
        assertEquals(todoForm.getDescricao(), result.getDescricao());
        assertEquals(todoForm.getStatus(), result.getStatus());
        assertEquals(todoForm.getObservacoes(), result.getObservacoes());
        verify(todoRepository).save(any(Todo.class));
    }

    @Test
    @DisplayName("Deve lançar exceção quando todo não encontrado")
    void deveLancarExcecaoQuandoTodoNaoEncontrado() {
        when(todoRepository.findById(1L)).thenReturn(Optional.empty());

        RegraNegocioException exception = assertThrows(RegraNegocioException.class, () -> {
            todoAtualizarService.atualizarTodo(1L, new TodoForm());
        });

        assertEquals("Todo não encontrado", exception.getMessage());
    }
}
