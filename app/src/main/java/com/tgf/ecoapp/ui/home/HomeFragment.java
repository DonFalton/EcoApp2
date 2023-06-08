package com.tgf.ecoapp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.tgf.ecoapp.databinding.FragmentHomeBinding;

import java.text.DateFormat;
import java.util.Date;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        HomeViewModel homeViewModel =
                new ViewModelProvider(this, new HomeViewModelFactory(requireActivity().getApplication()))
                        .get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // TextView for date and time
        final TextView textViewDateTime = binding.textDateTime;
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        textViewDateTime.setText(currentDateTimeString);

        // TextView for phrase
        final TextView textViewHome = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textViewHome::setText);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
