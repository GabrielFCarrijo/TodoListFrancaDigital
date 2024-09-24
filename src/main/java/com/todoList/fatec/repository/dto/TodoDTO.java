package com.todoList.fatec.repository.dto;

import lombok.*;

import java.time.LocalDateTime;

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
