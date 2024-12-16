package com.example.Todolist.Controllers;

import com.example.Todolist.Models.Task;
import com.example.Todolist.Services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Task task = taskService.getTaskById(id);
        return task != null ? ResponseEntity.ok(task) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task updatedTask) {
        Task task = taskService.updateTask(id, updatedTask);
        return task != null ? ResponseEntity.ok(task) : ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}/complete")
    public ResponseEntity<Task> markTaskAsCompleted(@PathVariable Long id) {
        Task task = taskService.markTaskAsCompleted(id);
        return task != null ? ResponseEntity.ok(task) : ResponseEntity.notFound().build();
    }

    @GetMapping("/filter")
    public List<Task> getTasksByCompletionStatus(@RequestParam boolean completed) {
        return taskService.getTasksByCompletionStatus(completed);
    }

    @GetMapping("/search")
    public List<Task> searchTasksByTitle(@RequestParam String keyword) {
        return taskService.searchTasksByTitle(keyword);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTaskById(@PathVariable Long id) {
        String result = taskService.deleteTaskById(id);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAllTasks() {
        String result = taskService.deleteAllTasks();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countAllTasks() {
        long count = taskService.countAllTasks();
        return ResponseEntity.ok(count);
    }
}
