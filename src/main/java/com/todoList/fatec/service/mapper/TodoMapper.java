package com.todoList.fatec.service.mapper;

import com.todoList.fatec.repository.dto.TodoDTO;
import com.todoList.fatec.repository.entity.Todo;
import com.todoList.fatec.repository.form.TodoForm;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

// Essa classe é responsável por transformar objetos de um tipo para outro, como converter tarefas em formatos diferentes.
// @Component: Indica que essa classe pode ser usada como um componente do Spring, permitindo que seja injetada em outras partes do código.
@Component
public class TodoMapper {

    // Método para converter uma tarefa (Todo) em um objeto de transferência de dados (TodoDTO).
    public TodoDTO paraDTO(Todo todo) {
        // Usa o padrão de construção para criar um TodoDTO com os mesmos dados da tarefa.
        return TodoDTO.builder()
                .id(todo.getId()) // Copia o ID da tarefa.
                .nome(todo.getNome()) // Copia o nome da tarefa.
                .descricao(todo.getDescricao()) // Copia a descrição da tarefa.
                .status(todo.getStatus()) // Copia o status da tarefa.
                .observacoes(todo.getObservacoes()) // Copia as observações da tarefa.
                .dataCriacao(todo.getDataCriacao()) // Copia a data de criação da tarefa.
                .dataAtualizacao(todo.getDataAtualizacao()) // Copia a data de atualização da tarefa.
                .build(); // Constrói e retorna o TodoDTO.
    }

    // Método para converter uma lista de tarefas em uma lista de objetos de transferência de dados.
    public List<TodoDTO> paraListDTO(List<Todo> todos) {
        // Usa um fluxo (stream) para mapear cada tarefa para seu respectivo DTO e coletar em uma lista.
        return todos.stream()
                .map(this::paraDTO) // Chama o método paraDTO em cada tarefa.
                .collect(Collectors.toList()); // Coleta os resultados em uma lista.
    }

    // Método para converter um formulário de tarefa (TodoForm) em uma tarefa (Todo).
    public Todo paraEntity(TodoForm todoForm) {
        // Usa o padrão de construção para criar um Todo com os dados do formulário.
        return Todo.builder()
                .id(todoForm.getId()) // Copia o ID do formulário.
                .nome(todoForm.getNome()) // Copia o nome do formulário.
                .descricao(todoForm.getDescricao()) // Copia a descrição do formulário.
                .status(todoForm.getStatus()) // Copia o status do formulário.
                .observacoes(todoForm.getObservacoes()) // Copia as observações do formulário.
                .dataCriacao(LocalDateTime.now()) // Define a data de criação como o momento atual.
                .dataAtualizacao(todoForm.getDataAtualizacao()) // Copia a data de atualização do formulário.
                .build(); // Constrói e retorna o Todo.
    }

    // Método para converter uma lista de formulários de tarefas em uma lista de tarefas.
    public List<Todo> paraListEntity(List<TodoForm> todosForm) {
        // Usa um fluxo (stream) para mapear cada formulário para sua respectiva tarefa e coletar em uma lista.
        return todosForm.stream()
                .map(this::paraEntity) // Chama o método paraEntity em cada formulário.
                .collect(Collectors.toList()); // Coleta os resultados em uma lista.
    }

    // Método para converter um formulário de tarefa (TodoForm) diretamente em um objeto de transferência de dados (TodoDTO).
    public TodoDTO paraDTOFromForm(TodoForm todoForm) {
        // Usa o padrão de construção para criar um TodoDTO com os dados do formulário.
        return TodoDTO.builder()
                .id(todoForm.getId()) // Copia o ID do formulário.
                .nome(todoForm.getNome()) // Copia o nome do formulário.
                .descricao(todoForm.getDescricao()) // Copia a descrição do formulário.
                .status(todoForm.getStatus()) // Copia o status do formulário.
                .observacoes(todoForm.getObservacoes()) // Copia as observações do formulário.
                .dataCriacao(todoForm.getDataCriacao()) // Copia a data de criação do formulário.
                .dataAtualizacao(todoForm.getDataAtualizacao()) // Copia a data de atualização do formulário.
                .build(); // Constrói e retorna o TodoDTO.
    }
}
