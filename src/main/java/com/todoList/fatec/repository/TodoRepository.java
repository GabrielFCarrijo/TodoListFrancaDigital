package com.todoList.fatec.repository;

import com.todoList.fatec.repository.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/*
Essa interface, TodoRepository, é usada para interagir com o banco de dados e gerenciar as tarefas (Todo).
Ela estende JpaRepository, que fornece métodos prontos para operações básicas, como salvar e buscar dados. Além disso:
*/

public interface TodoRepository extends JpaRepository<Todo, Long> {

    /*
    findByNomeContainingIgnoreCase: É um método personalizado que permite buscar tarefas pelo nome, ignorando maiúsculas e
    minúsculas, ou seja, ele encontra todas as tarefas que contêm o nome especificado.
    */

    List<Todo> findByNomeContainingIgnoreCase(String nome);
}
