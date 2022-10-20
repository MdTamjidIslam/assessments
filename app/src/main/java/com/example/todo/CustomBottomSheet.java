package com.example.todo;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomBottomSheet extends Dialog {

    private UserAdapter adapter;
    private final OnItemClickListener listener;

    public CustomBottomSheet(@NonNull Context context, @NonNull OnItemClickListener listener) {
        super(context);
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_drop_down_view);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        ImageButton button = findViewById(R.id.close);
        adapter = new UserAdapter();
        adapter.attachListener(user -> {
            listener.onClick(user);
            dismiss();
        });

        recyclerView.setAdapter(adapter);
        RetrofitDao service = new RetrofitService().getApi(RetrofitDao.class);
        service.getUsers().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(@NonNull Call<List<User>> call, @NonNull Response<List<User>> response) {
                adapter.submitList(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<User>> call, @NonNull Throwable t) {

            }
        });

        button.setOnClickListener(view -> dismiss());

    }

}
