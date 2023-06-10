package com.tgf.ecoapp.ui.others.settings;

import androidx.fragment.app.Fragment;

/**
 * Created by Martin B. on 10/6/23.
 * martin.blazquez.dam@gmail.com
 */
public class SettingOption {
    private String title;
    private Class<? extends Fragment> fragmentClass;

    public SettingOption(String title, Class<? extends Fragment> fragmentClass) {
        this.title = title;
        this.fragmentClass = fragmentClass;
    }

    public String getTitle() {
        return title;
    }

    public Class<? extends Fragment> getFragmentClass() {
        return fragmentClass;
    }
}

