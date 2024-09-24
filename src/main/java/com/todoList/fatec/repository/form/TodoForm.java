package com.todoList.fatec.repository.form;

import lombok.*;

import java.time.LocalDateTime;

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
