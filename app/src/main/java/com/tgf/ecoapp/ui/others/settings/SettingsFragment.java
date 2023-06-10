package com.tgf.ecoapp.ui.others.settings;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tgf.ecoapp.R;

import java.util.ArrayList;
import java.util.List;

public class SettingsFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public SettingsFragment() {
        // Required empty public constructor
    }

    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        // Get the RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);

        // Create the list of options
        List<SettingOption> settingsOptions = new ArrayList<>();
        settingsOptions.add(new SettingOption("Dirección", AddressFragment.class));
        settingsOptions.add(new SettingOption("Notificaciones", NotificationsFragment.class));
        settingsOptions.add(new SettingOption("Idioma", LanguageFragment.class));
        settingsOptions.add(new SettingOption("Tema", ThemeFragment.class));

        // Create and set the adapter
        // Usa getChildFragmentManager() aquí
        SettingsAdapter adapter = new SettingsAdapter(settingsOptions, getChildFragmentManager());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }
}
