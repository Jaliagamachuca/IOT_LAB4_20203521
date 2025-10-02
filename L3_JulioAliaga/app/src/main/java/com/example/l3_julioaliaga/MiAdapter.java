package com.example.l3_julioaliaga;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MiAdapter extends RecyclerView.Adapter<MiAdapter.ViewHolder> {
    private ArrayList<String> datos;

    public MiAdapter(ArrayList<String> datos) {
        this.datos = datos;
    }

    public void updateData(ArrayList<String> nuevos) {
        this.datos = nuevos;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_1, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.text.setText(datos.get(position));
    }

    @Override
    public int getItemCount() {
        return datos == null ? 0 : datos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView text;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(android.R.id.text1);
        }
    }
}

