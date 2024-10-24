package com.todoList.fatec.service;

import com.todoList.fatec.regraexception.RegraNegocioException;
import com.todoList.fatec.repository.TodoRepository;
import com.todoList.fatec.service.mapper.TodoMapper;
import com.todoList.fatec.service.validadores.ValidarDadosUsuarioService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class TodoDeletarService {

    // Dependência do repositório, carregada de forma 'Lazy'.
    @Lazy
    private final TodoRepository todoRepository; // Repositório para acessar os dados das tarefas.

    // Construtor que inicializa a dependência do repositório.
    public TodoDeletarService(TodoRepository todoRepository, TodoMapper todoMapper, ValidarDadosUsuarioService validarDadosUsuarioService) {
        this.todoRepository = todoRepository; // Inicializa o repositório.
    }

    // Método que deleta uma tarefa pelo ID.
    public void deletar(Long id) {
        // Procura a tarefa pelo ID fornecido.
        var todo = todoRepository.findById(id);
        // Se a tarefa não existir, lança uma exceção informando que não foi encontrada.
        if (todo.isEmpty()) {
            throw new RegraNegocioException("Todo não existente");
        }
        // Se a tarefa existir, é deletada do repositório.
        todoRepository.deleteById(id);
    }
}