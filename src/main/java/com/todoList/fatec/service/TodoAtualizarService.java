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

// Essa classe é responsável por atualizar as tarefas (Todos) no sistema.
// @Service: Indica que essa classe é um serviço do Spring, que contém a lógica de negócios.
@Service
public class TodoAtualizarService {

    // As dependências são marcadas como 'Lazy' para que sejam carregadas apenas quando realmente necessárias.
    @Lazy
    private final TodoRepository todoRepository; // Repositório para acessar dados das tarefas.
    @Lazy
    private final TodoMapper todoMapper; // Mapeador para converter entre entidades e DTOs.
    @Lazy
    private final ValidarDadosUsuarioService validarDadosUsuarioService; // Serviço para validar os dados do usuário.

    // Construtor que recebe as dependências para inicializar a classe.
    public TodoAtualizarService(TodoRepository todoRepository, TodoMapper todoMapper, ValidarDadosUsuarioService validarDadosUsuarioService) {
        this.todoRepository = todoRepository; // Inicializa o repositório.
        this.todoMapper = todoMapper; // Inicializa o mapeador.
        this.validarDadosUsuarioService = validarDadosUsuarioService; // Inicializa o serviço de validação.
    }

    // Método para atualizar uma tarefa existente com os dados fornecidos.
    public TodoDTO atualizarTodo(Long id, TodoForm todoForm) {
        // Valida os dados da tarefa recebida.
        validarDadosUsuarioService.validar(todoForm);

        // Tenta encontrar a tarefa pelo ID fornecido.
        Optional<Todo> todoOptional = todoRepository.findById(id);

        // Se a tarefa não for encontrada, lança uma exceção com uma mensagem de erro.
        if (todoOptional.isEmpty()) {
            throw new RegraNegocioException("Todo não encontrado");
        }

        // Obtém a tarefa encontrada.
        Todo todo = todoOptional.get();
        // Atualiza os campos da tarefa com os dados do formulário.
        todo.setNome(todoForm.getNome());
        todo.setDescricao(todoForm.getDescricao());
        todo.setStatus(todoForm.getStatus());
        todo.setObservacoes(todoForm.getObservacoes());
        todo.setDataAtualizacao(LocalDateTime.now()); // Atualiza a data de modificação.

        // Salva a tarefa atualizada no repositório.
        Todo updatedTodo = todoRepository.save(todo);

        // Retorna a tarefa atualizada como um DTO.
        return todoMapper.paraDTO(updatedTodo);
    }
}
