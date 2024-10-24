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

    // Dependência do repositório, carregada de forma 'Lazy'.
    @Lazy
    private final TodoRepository todoRepository; // Repositório para acessar e manipular dados de tarefas.

    // Dependência do mapeador, carregada de forma 'Lazy'.
    @Lazy
    private final TodoMapper todoMapper; // Usado para converter entre entidades e DTOs de tarefa.

    // Dependência do serviço de validação de dados do usuário, carregada de forma 'Lazy'.
    @Lazy
    private final ValidarDadosUsuarioService validarDadosUsuarioService; // Serviço para validar dados das tarefas.

    // Construtor que inicializa as dependências.
    public TodoInserirService(TodoRepository todoRepository, TodoMapper todoMapper, ValidarDadosUsuarioService validarDadosUsuarioService) {
        this.todoRepository = todoRepository; // Inicializa o repositório.
        this.todoMapper = todoMapper; // Inicializa o mapeador.
        this.validarDadosUsuarioService = validarDadosUsuarioService; // Inicializa o serviço de validação.
    }

    // Método que insere uma lista de tarefas no sistema.
    public List<TodoDTO> inserirTodoList(List<TodoForm> todoForms) {
        // Valida a lista de tarefas recebida.
        validarDadosUsuarioService.validarList(todoForms);

        // Converte os formulários de tarefa em entidades de tarefa.
        List<Todo> todos = todoMapper.paraListEntity(todoForms);
        // Salva todas as tarefas no repositório.
        List<Todo> savedTodos = todoRepository.saveAll(todos);

        // Converte as tarefas salvas de volta para DTOs e retorna a lista.
        return savedTodos.stream()
                .map(todoMapper::paraDTO) // Mapeia cada entidade para DTO.
                .collect(Collectors.toList()); // Coleta e retorna a lista de DTOs.
    }
}