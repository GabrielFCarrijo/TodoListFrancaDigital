package com.todoList.fatec.repository.enums;

/*
Um enum serve para definir um conjunto fixo de opções ou valores
Essa classe cria uma lista de opções fixas chamadas StatusEnum, que representam os possíveis estados de uma tarefa:

INICIADO: Quando a tarefa começou.
EM_ANDAMENTO: Quando a tarefa está sendo feita.
CONCLUIDO: Quando a tarefa foi finalizada.
CANCELADO: Quando a tarefa foi interrompida.
*/

public enum StatusEnum {
    INICIADO,
    EM_ANDAMENTO,
    CONCLUIDO,
    CANCELADO
}
