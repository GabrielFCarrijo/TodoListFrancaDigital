package com.todoList.fatec.repository.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/*
Essa classe representa uma tabela no banco de dados chamada todolist, onde ficam guardadas informações sobre tarefas (Todo).
As anotações ajudam a mapear essa classe para o banco e evitar código repetido:

@Entity: Diz que essa classe é uma tabela no banco.
@Table(name = "todolist"): Define o nome da tabela como "todolist".
@Id/@GeneratedValue: Define o campo "id" como identificador único e gerado automaticamente.
@Column: Mapeia os campos para as colunas do banco de dados.
*/

@Entity
@Table(name = "todolist")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String descricao;

    private String status;

    private String observacoes;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;
}
