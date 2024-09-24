package com.todoList.fatec.service.validadores;

import com.todoList.fatec.regraexception.RegraNegocioException;
import com.todoList.fatec.repository.enums.StatusEnum;
import com.todoList.fatec.repository.form.TodoForm;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ValidarDadosUsuarioService {

    public void validarList(List<TodoForm> todoForms) {
        for (TodoForm todo : todoForms) {
            validarStatus(todo.getStatus());
        }
    }

    public void validar(TodoForm todoForms) {
            validarStatus(todoForms.getStatus());
    }

    private void validarStatus(String status) {
        try {
            StatusEnum.valueOf(status.toUpperCase());
        } catch (Exception e) {
            throw new RegraNegocioException("Status inválido: " + status);
        }
    }
}
