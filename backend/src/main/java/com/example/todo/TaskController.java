package com.example.todo;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "*") // For a public deployment, replace * with your frontend domain.
public class TaskController {
    private final TaskRepository repository;
    public TaskController(TaskRepository repository) { this.repository = repository; }

    @GetMapping
    public List<Task> all(@RequestParam(required = false) Boolean completed,
                          @RequestParam(required = false) String project) {
        if (completed != null) return repository.findByCompleted(completed);
        if (project != null && !project.isBlank()) return repository.findByProjectIgnoreCase(project);
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Task one(@PathVariable Long id) { return findTask(id); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task create(@Valid @RequestBody Task task) {
        task.setId(null);
        return repository.save(task);
    }

    @PutMapping("/{id}")
    public Task update(@PathVariable Long id, @Valid @RequestBody Task input) {
        Task task = findTask(id);
        task.setTitle(input.getTitle());
        task.setDescription(input.getDescription());
        task.setProject(input.getProject());
        task.setDueDate(input.getDueDate());
        task.setPriority(input.getPriority());
        task.setCompleted(input.isCompleted());
        return repository.save(task);
    }

    @PatchMapping("/{id}/complete")
    public Task complete(@PathVariable Long id, @RequestParam boolean value) {
        Task task = findTask(id);
        task.setCompleted(value);
        return repository.save(task);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) { repository.delete(findTask(id)); }

    private Task findTask(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found"));
    }
}
