package com.andresvasquez.demoproject;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.andresvasquez.demoproject.adapter.MenuItemRecyclerViewAdapter;
import com.andresvasquez.demoproject.model.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String LOG = MainActivity.class.getSimpleName();
    private Context context;
    private RecyclerView taskRecyclerView;

    private List<MenuItem> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_main);
        initViews();
        fillMenuItems();

        addEvents();
    }

    private void initViews() {
        taskRecyclerView = findViewById(R.id.taskRecyclerView);
        MenuItemRecyclerViewAdapter adapter = new MenuItemRecyclerViewAdapter(context, items);
        taskRecyclerView.setAdapter(adapter);
        taskRecyclerView.setLayoutManager(new GridLayoutManager(context, 2));
        //taskRecyclerView.setLayoutManager(new LinearLayoutManager(context));
    }

    private void addEvents() {

    }

    private void fillMenuItems() {
        items.add(new MenuItem(1, getString(R.string.menu_kitchen), R.drawable.icon_kitchen));
        items.add(new MenuItem(2, getString(R.string.menu_book), R.drawable.icon_book));
        items.add(new MenuItem(3, getString(R.string.menu_movies), R.drawable.icon_movies));
        items.add(new MenuItem(4, getString(R.string.menu_fitness), R.drawable.icon_fitness));
        items.add(new MenuItem(5, getString(R.string.menu_health), R.drawable.icon_health));
    }
}
