package com.tgf.ecoapp.ui.recycle;

/**
 * Created by Martin B. on 12/6/23.
 * martin.blazquez.dam@gmail.com
 */
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tgf.ecoapp.R;

import java.util.List;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.RecycleViewHolder> {
    private List<Container> containerList;
    private OnContainerListener onContainerListener;

    public RecycleAdapter(List<Container> containerList, OnContainerListener onContainerListener) {
        this.containerList = containerList;
        this.onContainerListener = onContainerListener;
    }

    @NonNull
    @Override
    public RecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_container, parent, false);
        return new RecycleViewHolder(view, onContainerListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewHolder holder, int position) {
        holder.containerName.setText(containerList.get(position).getNombre());
    }

    @Override
    public int getItemCount() {
        return containerList.size();
    }

    public class RecycleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView containerName;
        OnContainerListener onContainerListener;

        public RecycleViewHolder(@NonNull View itemView, OnContainerListener onContainerListener) {
            super(itemView);
            containerName = itemView.findViewById(R.id.container_name);
            this.onContainerListener = onContainerListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onContainerListener.onContainerClick(getAdapterPosition());
        }
    }

    public interface OnContainerListener {
        void onContainerClick(int position);
    }
}


