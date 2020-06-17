package com.andresvasquez.demoproject.adapter;

import android.media.Image;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.andresvasquez.demoproject.R;

public class MenuItemViewHolder extends RecyclerView.ViewHolder {
    public ImageView iconImageView;
    public TextView titleTextView;

    public MenuItemViewHolder(@NonNull View itemView) {
        super(itemView);
        this.iconImageView = itemView.findViewById(R.id.iconImageView);
        this.titleTextView = itemView.findViewById(R.id.titleTextView);
    }
}
