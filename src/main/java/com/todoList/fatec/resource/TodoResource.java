package com.todoList.fatec.resource;

import com.todoList.fatec.repository.dto.TodoDTO;
import com.todoList.fatec.repository.form.TodoForm;
import com.todoList.fatec.service.TodoAtualizarService;
import com.todoList.fatec.service.TodoConsultaDinamicaService;
import com.todoList.fatec.service.TodoInserirService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
Essa classe é responsável por receber pedidos do usuário relacionados a tarefas (Todos) e devolver respostas.
@RestController: Indica que essa classe é um controlador que lida com pedidos e respostas em formato JSON.
@RequestMapping("/api/todo"): Define que todos os caminhos de acesso a esse controlador começam com "/api/todo".
@RequiredArgsConstructor Gera automaticamente um construtor que aceita todos os serviços que a classe precisa.
*/

@RestController
@RequestMapping("/api/todo")
@RequiredArgsConstructor
public class TodoResource {

    // Declara os serviços que serão usados para gerenciar as tarefas.
    private final TodoInserirService todoInserirService;
    private final TodoConsultaDinamicaService todoConsultaDinamicaService;
    private final TodoAtualizarService todoAtualizarService;
    //private final TodoDeletarService todoDeletarService;

    // Método para adicionar novas tarefas. Ele é chamado quando alguém envia um pedido para "/inserir".
    @PostMapping("/inserir")
    public ResponseEntity<List<TodoDTO>> inserirTodos(@RequestBody List<TodoForm> todoForms) {
        List<TodoDTO> todoDTOs = todoInserirService.inserirTodoList(todoForms);
        return new ResponseEntity<>(todoDTOs, HttpStatus.CREATED);
    }

    // Método para buscar tarefas pelo nome. Ele é chamado quando alguém envia um pedido para "/buscar".
    @GetMapping("/buscar")
    public List<TodoDTO> buscarPorNome(@RequestParam String nome) {
        return todoConsultaDinamicaService.buscarPorNome(nome);
    }

    // Método para atualizar uma tarefa existente. Ele é chamado quando alguém envia um pedido para "/{id}".
    @PutMapping("/{id}")
    public ResponseEntity<TodoDTO> atualizarTodo(@PathVariable Long id, @RequestBody TodoForm todoForm) {
        TodoDTO todoResponse = todoAtualizarService.atualizarTodo(id, todoForm);

        if (todoResponse != null) {
            return ResponseEntity.ok(todoResponse);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Método para deletar uma tarefa. Ele é chamado quando alguém envia um pedido para "/{id}".
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarTodo(@PathVariable Long id) {
        //todoDeletarService.deletar(id);
        return ResponseEntity.ok().build();
    }
}
