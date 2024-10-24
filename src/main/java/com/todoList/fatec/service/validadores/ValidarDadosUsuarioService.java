package com.todoList.fatec.service.validadores;

import com.todoList.fatec.regraexception.RegraNegocioException;
import com.todoList.fatec.repository.enums.StatusEnum;
import com.todoList.fatec.repository.form.TodoForm;
import org.springframework.stereotype.Component;

import java.util.List;

// Essa classe é responsável por validar os dados do usuário, especialmente o status das tarefas.
// @Component: Indica que essa classe pode ser usada como um componente do Spring, permitindo que seja injetada em outras partes do código.
@Component
public class ValidarDadosUsuarioService {

    // Método para validar uma lista de formulários de tarefas (TodoForm).
    public void validarList(List<TodoForm> todoForms) {
        // Para cada tarefa na lista, chama o método de validação do status.
        for (TodoForm todo : todoForms) {
            validarStatus(todo.getStatus()); // Valida o status da tarefa.
        }
    }

    // Método para validar um único formulário de tarefa (TodoForm).
    public void validar(TodoForm todoForms) {
        // Chama o método de validação do status para o formulário específico.
        validarStatus(todoForms.getStatus()); // Valida o status da tarefa.
    }

    // Método privado que verifica se o status fornecido é válido.
    private void validarStatus(String status) {
        try {
            // Tenta converter o status para maiúsculas e verificar se ele existe no enum StatusEnum.
            StatusEnum.valueOf(status.toUpperCase());
        } catch (Exception e) {
            // Se o status não for válido, lança uma exceção personalizada com uma mensagem de erro.
            throw new RegraNegocioException("Status inválido: " + status);
        }
    }
}
