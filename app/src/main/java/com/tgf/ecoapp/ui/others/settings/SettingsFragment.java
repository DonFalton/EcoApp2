package com.tgf.ecoapp.ui.others.settings;

import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tgf.ecoapp.R;

import java.util.ArrayList;
import java.util.List;

public class SettingsFragment extends Fragment {
    private RecyclerView settingsRecyclerView;
    private SettingsAdapter settingsAdapter;

    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        settingsRecyclerView = view.findViewById(R.id.settings_list);
        settingsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Pass the current context to the adapter
        settingsAdapter = new SettingsAdapter(getActivity(), getSettingsList());
        settingsRecyclerView.setAdapter(settingsAdapter);

        return view;
    }

    private List<Setting> getSettingsList() {
        Log.d("SettingsFragment", "Creating settings list");
        List<Setting> settings = new ArrayList<>();
        settings.add(new Setting("Dirección"));
        settings.add(new Setting("Notificaciones"));
        settings.add(new Setting("Idioma"));
        settings.add(new Setting("Tema"));
        Log.d("SettingsFragment", "Settings list size: " + settings.size());
        return settings;
    }

}
