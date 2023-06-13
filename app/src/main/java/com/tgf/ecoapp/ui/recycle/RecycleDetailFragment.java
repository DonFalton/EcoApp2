package com.tgf.ecoapp.ui.recycle;

/**
 * Created by Martin B. on 12/6/23.
 * martin.blazquez.dam@gmail.com
 */
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.tgf.ecoapp.R;

public class RecycleDetailFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recycle_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView contentTextView = view.findViewById(R.id.container_content);
        TextView descriptionTextView = view.findViewById(R.id.container_description);

        // get the details passed from RecycleFragment
        Bundle bundle = getArguments();
        if (bundle != null) {
            String content = bundle.getString("contenido");
            String description = bundle.getString("descripcion");

            // set the details to the TextViews
            contentTextView.setText(content);
            descriptionTextView.setText(description);
        }
    }
}

