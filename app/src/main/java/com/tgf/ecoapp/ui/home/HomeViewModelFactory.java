package com.tgf.ecoapp.ui.home;
/**
 * Created by Martin B. on 8/6/23.
 * martin.blazquez.dam@gmail.com
 */
import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;


public class HomeViewModelFactory implements ViewModelProvider.Factory {
    private Application application;

    public HomeViewModelFactory(Application application) {
        this.application = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new HomeViewModel(application);
    }
}
