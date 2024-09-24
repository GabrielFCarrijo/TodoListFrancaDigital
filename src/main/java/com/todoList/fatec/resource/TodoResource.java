package com.todoList.fatec.resource;

import com.todoList.fatec.repository.dto.TodoDTO;
import com.todoList.fatec.repository.form.TodoForm;
import com.todoList.fatec.service.TodoAtualizarService;
import com.todoList.fatec.service.TodoConsultaDinamicaService;
import com.todoList.fatec.service.TodoDeletarService;
import com.todoList.fatec.service.TodoInserirService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
@RequiredArgsConstructor
public class TodoResource {

    private final TodoInserirService todoInserirService;
    private final TodoConsultaDinamicaService todoConsultaDinamicaService;
    private final TodoAtualizarService todoAtualizarService;
    private final TodoDeletarService todoDeletarService;

    @PostMapping("/inserir")
    public ResponseEntity<List<TodoDTO>> inserirTodos(@RequestBody List<TodoForm> todoForms) {
        List<TodoDTO> todoDTOs = todoInserirService.inserirTodoList(todoForms);
        return new ResponseEntity<>(todoDTOs, HttpStatus.CREATED);
    }

    @GetMapping("/buscar")
    public List<TodoDTO> buscarPorNome(@RequestParam String nome) {
        return todoConsultaDinamicaService.buscarPorNome(nome);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoDTO> atualizarTodo(@PathVariable Long id, @RequestBody TodoForm todoForm) {
        TodoDTO todoResponse = todoAtualizarService.atualizarTodo(id, todoForm);

        if (todoResponse != null) {
            return ResponseEntity.ok(todoResponse);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarTodo(@PathVariable Long id) {
        todoDeletarService.deletar(id);
        return ResponseEntity.ok().build();
    }
}
