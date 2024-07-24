package com.example.ei_assesment;


import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.TextView;
        import androidx.annotation.NonNull;
        import androidx.recyclerview.widget.RecyclerView;
        import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
    private List<Task> tasks;

    public TaskAdapter(List<Task> tasks) {
        this.tasks = tasks;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_item, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task task = tasks.get(position);
        holder.description.setText(task.getDescription());
        holder.dueDate.setText(task.getDueDate());
        holder.status.setText(task.isCompleted() ? "Completed" : "Pending");
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        public TextView description;
        public TextView dueDate;
        public TextView status;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            description = itemView.findViewById(R.id.task_description);
            dueDate = itemView.findViewById(R.id.task_due_date);
            status = itemView.findViewById(R.id.task_status);
        }
    }
}