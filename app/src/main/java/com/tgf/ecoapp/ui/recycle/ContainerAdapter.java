package com.tgf.ecoapp.ui.recycle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tgf.ecoapp.R;

import java.util.List;

/**
 * Created by Martin B. on 5/6/23.
 * martin.blazquez.dam@gmail.com
 */
public class ContainerAdapter extends RecyclerView.Adapter<ContainerAdapter.ViewHolder> {

    private final List<Container> containerList;
    private final OnContainerClickListener onContainerClickListener;

    // Constructor
    public ContainerAdapter(List<Container> containerList, OnContainerClickListener listener) {
        this.containerList = containerList;
        this.onContainerClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_container, parent, false);
        return new ViewHolder(view, onContainerClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Container container = containerList.get(position);
        holder.name.setText(container.getName());
        holder.image.setImageResource(container.getImageResId());
    }

    @Override
    public int getItemCount() {
        return containerList.size();
    }

    public interface OnContainerClickListener {
        void onContainerClick(int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name;
        ImageView image;
        OnContainerClickListener onContainerClickListener;

        public ViewHolder(View itemView, OnContainerClickListener onContainerClickListener) {
            super(itemView);
            name = itemView.findViewById(R.id.container_name);
            image = itemView.findViewById(R.id.container_image);
            this.onContainerClickListener = onContainerClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onContainerClickListener.onContainerClick(getAdapterPosition());
        }
    }
}
