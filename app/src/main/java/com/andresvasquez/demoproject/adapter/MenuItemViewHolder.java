package com.andresvasquez.demoproject.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.andresvasquez.demoproject.R;

public class MenuItemViewHolder extends RecyclerView.ViewHolder {
    public ImageView iconImageView;
    public TextView titleTexView;

    public MenuItemViewHolder(@NonNull View itemView) {
        super(itemView);
        this.iconImageView = itemView.findViewById(R.id.iconImageView);
        this.titleTexView = itemView.findViewById(R.id.titleTexView);
    }
}
