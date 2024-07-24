package com.example.ei_assesment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TaskManager taskManager;
    private TaskAdapter taskAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskManager = new TaskManager();
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Button addButton = findViewById(R.id.add_button);
        Button undoButton = findViewById(R.id.undo_button);
        Button redoButton = findViewById(R.id.redo_button);
        Button filterAllButton = findViewById(R.id.filter_all);
        Button filterCompletedButton = findViewById(R.id.filter_completed);
        Button filterPendingButton = findViewById(R.id.filter_pending);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTask();
            }
        });

        undoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                taskManager.undo();
                updateTaskList(taskManager.getTasks("all"));
            }
        });

        redoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                taskManager.redo();
                updateTaskList(taskManager.getTasks("all"));
            }
        });

        filterAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateTaskList(taskManager.getTasks("all"));
            }
        });

        filterCompletedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateTaskList(taskManager.getTasks("completed"));
            }
        });

        filterPendingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateTaskList(taskManager.getTasks("pending"));
            }
        });
    }

    private void addTask() {
        EditText descriptionInput = findViewById(R.id.description_input);
        EditText dueDateInput = findViewById(R.id.due_date_input);

        String description = descriptionInput.getText().toString();
        String dueDate = dueDateInput.getText().toString();

        Task task = new Task.TaskBuilder()
                .setDescription(description)
                .setDueDate(dueDate)
                .build();

        taskManager.addTask(task);
        updateTaskList(taskManager.getTasks("all"));
    }

    private void updateTaskList(List<Task> tasks) {
        taskAdapter = new TaskAdapter(tasks);
        recyclerView.setAdapter(taskAdapter);
    }
}