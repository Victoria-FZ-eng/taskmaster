package com.example.taskmaster;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
    List<Task> allTasks = new ArrayList<>();

    public TaskAdapter(List<Task> allTasks) {
        this.allTasks = allTasks;
    }
    public static class TaskViewHolder extends RecyclerView.ViewHolder{

        public Task task;
        View itemView;

        public TaskViewHolder(@NonNull  View itemView) {
            super(itemView);
            this.itemView=itemView;
        }
    }
    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_task,parent,false);
        TaskViewHolder taskViewHolder = new TaskViewHolder(view);
        return taskViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TaskAdapter.TaskViewHolder holder, int position) {

        holder.task = allTasks.get(position);

        Button title = holder.itemView.findViewById(R.id.taskTitleFrag);
//        TextView body = holder.itemView.findViewById(R.id.);
//        TextView state = holder.itemView.findViewById(R.id.);

        title.setText(holder.task.title);


    }

    @Override
    public int getItemCount() {
        return allTasks.size();
    }



}
