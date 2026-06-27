package com.javaprogram.crud.controller;

import com.javaprogram.crud.dto.TaskRequest;
import com.javaprogram.crud.model.Task;
import com.javaprogram.crud.repository.TaskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskRepository repo;

    TaskController(TaskRepository repo) { this.repo = repo; }

    // GET /api/tasks — list all tasks
    @GetMapping
    public List<Task> getAll() {
        return repo.findAll();
    }

    // GET /api/tasks/{id} — get one task
    @GetMapping("/{id}")
    public ResponseEntity<Task> getById(@PathVariable Long id) {
        return repo.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/tasks — create a task
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task create(@RequestBody TaskRequest req) {
        Task task = new Task(req.getTitle(), req.getDescription());
        return repo.save(task);
    }

    // PUT /api/tasks/{id} — update a task
    @PutMapping("/{id}")
    public ResponseEntity<Task> update(@PathVariable Long id, @RequestBody TaskRequest req) {
        return repo.findById(id).map(task -> {
            task.setTitle(req.getTitle());
            task.setDescription(req.getDescription());
            task.setCompleted(req.isCompleted());
            return ResponseEntity.ok(repo.save(task));
        }).orElse(ResponseEntity.notFound().build());
    }

    // DELETE /api/tasks/{id} — delete a task
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // GET /api/tasks?completed=true — filter by completion status
    @GetMapping(params = "completed")
    public List<Task> getByStatus(@RequestParam boolean completed) {
        return repo.findByCompleted(completed);
    }
}
