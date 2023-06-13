package com.tgf.ecoapp.ui.others.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tgf.ecoapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Martin B. on 13/6/23.
 * martin.blazquez.dam@gmail.com
 */
public class SettingsDetailFragment extends Fragment {

    private RecyclerView optionsRecyclerView;
    private SettingsDetailAdapter optionsAdapter;

    public SettingsDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings_detail, container, false);

        optionsRecyclerView = view.findViewById(R.id.options_list);
        optionsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        optionsAdapter = new SettingsDetailAdapter(getOptionsList());
        optionsRecyclerView.setAdapter(optionsAdapter);

        return view;
    }

    private List<SettingOption> getOptionsList() {
        List<SettingOption> options = new ArrayList<>();

        // Retrieve setting name from arguments
        String settingName = getArguments().getString("setting_name");

        // Based on setting name, add options. This is just an example
        switch (settingName) {
            case "Dirección":
                options.add(new SettingOption("Opción Dirección 1"));
                options.add(new SettingOption("Opción Dirección 2"));
                break;
            case "Notificaciones":
                options.add(new SettingOption("Opción Notificaciones"));
                options.add(new SettingOption("Opción Notificaciones 1"));
                options.add(new SettingOption("Opción Notificaciones 2"));
                break;
            case "Idioma":
                options.add(new SettingOption("Opción Idioma 1"));
                options.add(new SettingOption("Opción Idioma 2"));
                break;
            case "Tema":
                options.add(new SettingOption("Opción Tema 1"));
                options.add(new SettingOption("Opción Tema 2"));
                break;
            default:
                break;
        }

        return options;
    }
}


