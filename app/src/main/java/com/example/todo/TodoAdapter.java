package com.example.todo;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder> {

    private List<Todo> todos = new ArrayList<>();

    @SuppressLint("NotifyDataSetChanged")
    public void submitList(List<Todo> users) {
        this.todos = users;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_todo, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Todo todo = todos.get(position);
        holder.tvTitle.setText(todo.getTitle());
        holder.tvId.setText(String.valueOf(todo.getId()));
        holder.checkBox.setChecked(todo.isCompleted());
    }

    @Override
    public int getItemCount() {
        return todos != null ? todos.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvTitle, tvId;
        public CheckBox checkBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvId = itemView.findViewById(R.id.tv_id);
            checkBox = itemView.findViewById(R.id.check_box);

        }
    }
}
