package com.example.todo;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RetrofitDao mService;
    private RecyclerView recyclerView;
    private TodoAdapter todoAdapter;
    private List<Todo> todos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mService = new RetrofitService().getApi(RetrofitDao.class);
        recyclerView = findViewById(R.id.recycler_view);
        todoAdapter = new TodoAdapter();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_screen, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add) {
            loadUsers();
            return true;
        } else return false;
    }

    private void loadUsers() {
        CustomBottomSheet sheet = new CustomBottomSheet(this, user -> {
            loadTodos();
        });
        sheet.show();
    }

    private void loadTodos() {

        recyclerView.setAdapter(todoAdapter);

        mService.getTodos().enqueue(new Callback<List<Todo>>() {
            @Override
            public void onResponse(@NonNull Call<List<Todo>> call, @NonNull Response<List<Todo>> response) {
                todos = response.body();
                todoAdapter.submitList(todos);
            }

            @Override
            public void onFailure(@NonNull Call<List<Todo>> call, @NonNull Throwable t) {

            }
        });
    }
}