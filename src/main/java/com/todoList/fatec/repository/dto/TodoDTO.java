package com.todoList.fatec.repository.dto;

import lombok.*;

import java.time.LocalDateTime;

/*
Essa classe define um "Todo" (tarefa) com várias informações, como nome, descrição, status e datas. As anotações ajudam a evitar código repetido:

@AllArgsConstructor: Cria um construtor com todos os atributos.
@NoArgsConstructor: Cria um construtor sem atributos.
@Builder: Permite construir objetos de forma flexível.
@Getter/@Setter: Cria métodos para acessar e modificar os atributos (pega e altera valores).
*/

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class TodoDTO {

    private Long id;
    private String nome;
    private String descricao;
    private String status;
    private String observacoes;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;
}
