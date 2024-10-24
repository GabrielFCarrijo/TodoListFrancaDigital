package com.todoList.fatec.repository.form;

import lombok.*;

import java.time.LocalDateTime;


/*
Essa classe, TodoForm, é usada para representar um formulário de tarefa (Todo) com várias informações, como nome, descrição e status.
As anotações ajudam a reduzir o código repetido e facilitar a criação de objetos:

    @AllArgsConstructor: Cria um construtor que aceita todos os atributos da classe.
    @NoArgsConstructor: Cria um construtor sem parâmetros, permitindo criar um objeto vazio.
    @Builder: Permite criar objetos de forma mais flexível, utilizando um padrão de construção.
    @Getter/@Setter: Gera automaticamente métodos para obter (get) e definir (set) os valores dos atributos.
*/

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class TodoForm {

    private Long id;
    private String nome;
    private String descricao;
    private String status;
    private String observacoes;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;
}
