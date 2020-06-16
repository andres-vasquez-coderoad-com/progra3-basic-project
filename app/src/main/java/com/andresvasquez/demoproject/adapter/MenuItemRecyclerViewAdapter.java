package com.andresvasquez.demoproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.andresvasquez.demoproject.R;
import com.andresvasquez.demoproject.model.MenuItem;

import java.util.List;

public class MenuItemRecyclerViewAdapter extends RecyclerView.Adapter<MenuItemViewHolder> {

    private List<MenuItem> menuItems;
    private LayoutInflater inflater;

    public MenuItemRecyclerViewAdapter(Context context, List<MenuItem> menuItems) {
        this.menuItems = menuItems;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MenuItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = this.inflater.inflate(R.layout.menu_item_layout, null);
        return new MenuItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuItemViewHolder holder, int position) {
        final MenuItem menuItem = this.menuItems.get(position);
        holder.iconImageView.setImageResource(menuItem.getIcon());
        holder.titleTexView.setText(menuItem.getTitle());
    }

    @Override
    public int getItemCount() {
        return this.menuItems.size();
    }
}
