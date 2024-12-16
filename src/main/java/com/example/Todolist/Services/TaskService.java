package com.example.Todolist.Services;

import com.example.Todolist.Models.Task;
import com.example.Todolist.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long id) {
        Optional<Task> task = taskRepository.findById(id);
        return task.orElse(null);
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public Task updateTask(Long id, Task updatedTask) {
        return taskRepository.findById(id)
                .map(existingTask -> {
                    existingTask.setTitle(updatedTask.getTitle());
                    existingTask.setDescription(updatedTask.getDescription());
                    existingTask.setCompleted(updatedTask.isCompleted());
                    return taskRepository.save(existingTask);
                }).orElse(null);
    }

    public Task markTaskAsCompleted(Long id) {
        return taskRepository.findById(id)
                .map(task -> {
                    task.setCompleted(true);
                    return taskRepository.save(task);
                }).orElse(null);
    }

    public List<Task> getTasksByCompletionStatus(boolean completed) {
        return taskRepository.findAll().stream()
                .filter(task -> task.isCompleted() == completed)
                .toList();
    }

    public List<Task> searchTasksByTitle(String keyword) {
        return taskRepository.findAll().stream()
                .filter(task -> task.getTitle().toLowerCase().contains(keyword.toLowerCase()))
                .toList();
    }

    public String deleteTaskById(Long id) {
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
            return "Task with ID " + id + " deleted successfully!";
        } else {
            return "Task with ID " + id + " not found!";
        }
    }

    public String deleteAllTasks() {
        taskRepository.deleteAll();
        return "All tasks have been deleted successfully!";
    }

    public long countAllTasks() {
        return taskRepository.count();
    }
}
