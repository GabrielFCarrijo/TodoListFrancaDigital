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

    // Dependências do repositório e mapeador, carregadas de forma 'Lazy'.
    @Lazy
    private final TodoRepository todoRepository; // Repositório para acessar dados das tarefas.
    @Lazy
    private final TodoMapper todoMapper; // Mapeador para converter tarefas em DTOs.

    // Construtor que inicializa as dependências.
    public TodoConsultaDinamicaService(TodoRepository todoRepository, TodoMapper todoMapper) {
        this.todoRepository = todoRepository; // Inicializa o repositório.
        this.todoMapper = todoMapper; // Inicializa o mapeador.
    }

    // Método que busca tarefas pelo nome, ignorando maiúsculas e minúsculas.
    public List<TodoDTO> buscarPorNome(String nome) {
        // Obtém a lista de tarefas que contém o nome fornecido.
        List<Todo> todos = todoRepository.findByNomeContainingIgnoreCase(nome);
        // Converte a lista de tarefas em uma lista de DTOs e a retorna.
        return todoMapper.paraListDTO(todos);
    }
}