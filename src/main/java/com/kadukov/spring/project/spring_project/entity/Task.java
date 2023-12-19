package com.kadukov.spring.project.spring_project.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Date;

@Entity
@Table(name = "tasks")
public class Task{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;
    @Column
    private String title;
    @Column
    private String description;
    @Column
    private Integer priority;
    @Column
    private boolean is_done;
    @Column
    private String owner;
    @Column
    private LocalDate deadline;

    public Task() {
    }

    public Task(Integer id, String title, String description, Integer priority, boolean is_done, String owner, LocalDate deadline) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.is_done = is_done;
        this.owner = owner;
        this.deadline = deadline;
    }

    public static Comparator<Task> priorityComparator = Comparator.comparingInt(Task::getPriority).reversed();
    public static Comparator<Task> titleComparator = Comparator.comparing(Task::getTitle);
    public static Comparator<Task> statusComparator = Comparator.comparing(Task::isIs_done);
    public static Comparator<Task> deadlineComparator = Comparator.comparing(Task::getDeadline);
    public static Comparator<Task> descriptionComparator = Comparator.comparing(Task::getDescription);


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public boolean isIs_done() {
        return is_done;
    }

    public void setIs_done(boolean is_done) {
        this.is_done = is_done;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", priority=" + priority +
                ", is_done=" + is_done +
                ", owner='" + owner + '\'' +
                ", deadline=" + deadline +
                '}';
    }
}
