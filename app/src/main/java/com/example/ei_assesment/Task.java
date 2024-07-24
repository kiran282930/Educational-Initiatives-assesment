package com.example.ei_assesment;

public class Task {
    private String description;
    private String dueDate;
    private boolean completed;

    private Task(String description, String dueDate) {
        this.description = description;
        this.dueDate = dueDate;
        this.completed = false;
    }

    public void markCompleted() {
        this.completed = true;
    }

    public void markPending() {
        this.completed = false;
    }

    public String getDescription() {
        return description;
    }

    public String getDueDate() {
        return dueDate;
    }

    public boolean isCompleted() {
        return completed;
    }

    public static class TaskBuilder {
        private String description;
        private String dueDate;

        public TaskBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public TaskBuilder setDueDate(String dueDate) {
            this.dueDate = dueDate;
            return this;
        }

        public Task build() {
            return new Task(description, dueDate);
        }
    }
}