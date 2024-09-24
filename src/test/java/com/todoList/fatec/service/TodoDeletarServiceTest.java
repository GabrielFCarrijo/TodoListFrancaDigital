package com.todoList.fatec.service;

import com.todoList.fatec.regraexception.RegraNegocioException;
import com.todoList.fatec.repository.TodoRepository;
import com.todoList.fatec.repository.entity.Todo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class TodoDeletarServiceTest {

    @Autowired
    private TodoDeletarService todoDeletarService;

    @MockBean
    private TodoRepository todoRepository;

    @Test
    @DisplayName("Deve deletar todo com sucesso")
    void deveDeletarTodoComSucesso() {
        when(todoRepository.findById(1L)).thenReturn(Optional.of(new Todo()));

        todoDeletarService.deletar(1L);

        verify(todoRepository).deleteById(1L);
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar deletar todo inexistente")
    void deveLancarExcecaoAoTentarDeletarTodoInexistente() {
        when(todoRepository.findById(1L)).thenReturn(Optional.empty());

        RegraNegocioException exception = assertThrows(RegraNegocioException.class, () -> {
            todoDeletarService.deletar(1L);
        });

        assertEquals("Todo não existente", exception.getMessage());
    }
}
