package com.tgf.ecoapp.ui.others.settings;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tgf.ecoapp.R;

import java.util.List;

/**
 * Created by Martin B. on 10/6/23.
 * martin.blazquez.dam@gmail.com
 */
public class SettingsAdapter extends RecyclerView.Adapter<SettingsAdapter.ViewHolder> {
    private List<SettingOption> settingsOptions;
    private FragmentManager fragmentManager;

    // Constructor
    public SettingsAdapter(List<SettingOption> settingsOptions, FragmentManager fragmentManager) {
        this.settingsOptions = settingsOptions;
        this.fragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Asume que tienes un layout llamado 'item_setting_option' que define el aspecto de cada elemento
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_setting_option, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final int safePosition = holder.getAdapterPosition();

        // Set the title for this option
        holder.settingTitle.setText(settingsOptions.get(safePosition).getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = null;
                try {
                    fragment = settingsOptions.get(safePosition).getFragmentClass().newInstance();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                if (fragment != null) {
                    fragmentManager.beginTransaction()
                            .replace(R.id.container, fragment)
                            .addToBackStack(null)
                            .commit();
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return settingsOptions.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Mantén las referencias a los elementos de la vista aquí. Asume que tienes un TextView en tu layout 'item_setting_option'
        TextView settingTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            settingTitle = itemView.findViewById(R.id.setting_title);
        }
    }
}
