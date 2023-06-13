package com.tgf.ecoapp.ui.recycle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tgf.ecoapp.R;

import java.util.ArrayList;

public class RecycleFragment extends Fragment implements RecycleAdapter.OnContainerListener {
    private RecycleViewModel viewModel;
    private RecycleAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recycle, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.recycle_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new RecycleAdapter(new ArrayList<>(), this);
        recyclerView.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(RecycleViewModel.class);

        viewModel.getContainers().observe(getViewLifecycleOwner(), containers -> {
            // update UI
            adapter = new RecycleAdapter(containers, this);
            recyclerView.setAdapter(adapter);
        });
    }

    @Override
    public void onContainerClick(int position) {
        Container container = viewModel.getContainers().getValue().get(position);

        // bundle to pass to the details fragment
        Bundle bundle = new Bundle();
        bundle.putString("nombre", container.getNombre());
        bundle.putString("contenido", container.getContenido());
        bundle.putString("color", container.getColor());
        bundle.putString("descripcion", container.getDescripción());

        // navigate to the details fragment
        NavController navController = Navigation.findNavController(requireView());
        navController.navigate(R.id.action_recycleFragment_to_recycleDetailFragment, bundle);
    }
}
