package com.tgf.ecoapp.ui.others.settings;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import androidx.recyclerview.widget.RecyclerView;

import com.tgf.ecoapp.R;

import java.util.List;

/**
 * Created by Martin B. on 13/6/23.
 * martin.blazquez.dam@gmail.com
 */
public class SettingsDetailAdapter extends RecyclerView.Adapter<SettingsDetailAdapter.ViewHolder> {

    private List<SettingOption> options; // lista de opciones del ajuste

    // Constructor, getters y setters

    public SettingsDetailAdapter(List<SettingOption> options) {
        this.options = options;
    }

    // ViewHolder para mantener las vistas de los elementos de la lista
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public RadioButton optionName;

        public ViewHolder(View view) {
            super(view);
            optionName = view.findViewById(R.id.option_name);
        }
    }

    // Crear nuevas vistas (invocada por el layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // crear una nueva vista
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_setting_option, parent, false);
        return new ViewHolder(v);
    }

    // Reemplaza el contenido de una vista (invocada por el layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // obtener el elemento de la lista de opciones en esta posición

        SettingOption option = options.get(position);
    // actualizar el RadioButton con el nombre de la opción
        holder.optionName.setText(option.getName());
    }

    // Devuelve el tamaño de la lista de opciones (invocada por el layout manager)
    @Override
    public int getItemCount() {
        return options.size();
    }
}