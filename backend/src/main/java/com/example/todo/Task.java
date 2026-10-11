package com.example.todo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
public class Task {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank @Size(max = 120)
    private String title;
    @Size(max = 2000)
    private String description = "";
    private String project = "Personal";
    private LocalDate dueDate;
    private Integer priority = 4;
    private boolean completed = false;
    private LocalDateTime createdAt = LocalDateTime.now();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description == null ? "" : description; }
    public String getProject() { return project; }
    public void setProject(String project) { this.project = (project == null || project.isBlank()) ? "Personal" : project; }
    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }
    public Integer getPriority() { return priority; }
    public void setPriority(Integer priority) { this.priority = (priority == null || priority < 1 || priority > 4) ? 4 : priority; }
    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
