package com.tgf.ecoapp.ui.recycle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.tgf.ecoapp.R;

public class ContainerDetailFragment extends Fragment {

    private static final String CONTAINER_KEY = "container";

    public static ContainerDetailFragment newInstance(Container container) {
        ContainerDetailFragment fragment = new ContainerDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(CONTAINER_KEY, container);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_container_detail, container, false);

        TextView name = view.findViewById(R.id.container_name);
        TextView description = view.findViewById(R.id.container_description);
        ImageView image = view.findViewById(R.id.container_image);

        if (getArguments() != null) {
            Container containerData = (Container) getArguments().getSerializable(CONTAINER_KEY);
            if (containerData != null) {
                name.setText(containerData.getName());
                description.setText(containerData.getDescription());
                image.setImageResource(containerData.getImageResId());
            }
        }

        return view;
    }
}
