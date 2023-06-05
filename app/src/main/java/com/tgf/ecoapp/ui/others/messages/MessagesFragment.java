package com.tgf.ecoapp.ui.others.messages;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.tgf.ecoapp.databinding.FragmentMessagesBinding;
import com.tgf.ecoapp.ui.others.messages.MessagesViewModel;

public class MessagesFragment extends Fragment {

    private FragmentMessagesBinding binding;
    private MessagesViewModel messagesViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        messagesViewModel =
                new ViewModelProvider(this).get(MessagesViewModel.class);

        binding = FragmentMessagesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Aquí podrías por ejemplo observar los mensajes del ViewModel y actualizar la UI cuando cambien
        messagesViewModel.getMessages().observe(getViewLifecycleOwner(), messages -> {
            // Actualiza la UI
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
