package com.tgf.ecoapp.ui.others.settings;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tgf.ecoapp.R;

import java.util.List;

/**
 * Created by Martin B. on 10/6/23.
 * martin.blazquez.dam@gmail.com
 */
public class SettingsAdapter extends RecyclerView.Adapter<SettingsAdapter.ViewHolder> {
    private List<Setting> settings;
    private Context context;

    public SettingsAdapter(Context context, List<Setting> settings) {
        this.settings = settings;
        this.context = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView settingName;
        public View view;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            settingName = view.findViewById(R.id.setting_name);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_setting, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Setting setting = settings.get(position);
        Log.d("SettingsAdapter", "Binding view for setting: " + setting.getName());
        holder.settingName.setText(setting.getName());

        // Set OnClickListener
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pass data to SettingsDetailFragment
                Bundle bundle = new Bundle();
                bundle.putString("setting_name", setting.getName());

                // Navigate to SettingsDetailFragment
                SettingsDetailFragment settingsDetailFragment = new SettingsDetailFragment();
                settingsDetailFragment.setArguments(bundle);

                ((FragmentActivity) context).getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, settingsDetailFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return settings.size();
    }
}
