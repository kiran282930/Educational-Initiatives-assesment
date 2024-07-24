package com.example.ei_assesment;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TaskManager {
    private List<Task> tasks;
    private Stack<Memento> history;
    private Stack<Memento> redoStack;

    public TaskManager() {
        tasks = new ArrayList<>();
        history = new Stack<>();
        redoStack = new Stack<>();
    }

    public void addTask(Task task) {
        saveState();
        tasks.add(task);
    }

    public void deleteTask(String description) {
        saveState();
        tasks.removeIf(task -> task.getDescription().equals(description));
    }

    public void markTaskCompleted(String description) {
        saveState();
        for (Task task : tasks) {
            if (task.getDescription().equals(description)) {
                task.markCompleted();
                break;
            }
        }
    }

    public List<Task> getTasks(String filter) {
        if (filter.equals("completed")) {
            List<Task> completedTasks = new ArrayList<>();
            for (Task task : tasks) {
                if (task.isCompleted()) {
                    completedTasks.add(task);
                }
            }
            return completedTasks;
        } else if (filter.equals("pending")) {
            List<Task> pendingTasks = new ArrayList<>();
            for (Task task : tasks) {
                if (!task.isCompleted()) {
                    pendingTasks.add(task);
                }
            }
            return pendingTasks;
        } else {
            return tasks;
        }
    }

    public void undo() {
        if (!history.isEmpty()) {
            redoStack.push(new Memento(new ArrayList<>(tasks)));
            tasks = history.pop().getState();
        }
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            saveState();
            tasks = redoStack.pop().getState();
        }
    }

    private void saveState() {
        history.push(new Memento(new ArrayList<>(tasks)));
    }

    private class Memento {
        private List<Task> state;

        public Memento(List<Task> state) {
            this.state = state;
        }

        public List<Task> getState() {
            return state;
        }
    }
}