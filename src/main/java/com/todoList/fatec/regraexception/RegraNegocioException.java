package com.todoList.fatec.regraexception;

/*Essa classe cria um tipo de erro personalizado chamado "RegraNegocioException".
Ela é usada para indicar que alguma regra de negócio não foi respeitada no programa, mostrando uma mensagem de erro específica.*/

public class RegraNegocioException extends RuntimeException {

    public RegraNegocioException(String message) {
        super(message);
    }
}
