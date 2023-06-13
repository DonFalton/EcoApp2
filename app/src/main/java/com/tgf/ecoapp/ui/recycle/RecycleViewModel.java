package com.tgf.ecoapp.ui.recycle;

/**
 * Created by Martin B. on 12/6/23.
 * martin.blazquez.dam@gmail.com
 */
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewModel extends ViewModel {
    private final MutableLiveData<List<Container>> containers;
    private final FirebaseFirestore db;

    public RecycleViewModel() {
        containers = new MutableLiveData<>();
        db = FirebaseFirestore.getInstance();
        loadContainers();
    }

    private void loadContainers() {
        db.collection("RECICLAJE")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        List<Container> containerList = new ArrayList<>();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Container container = document.toObject(Container.class);
                            containerList.add(container);
                        }
                        containers.setValue(containerList);
                    }
                });
    }

    public LiveData<List<Container>> getContainers() {
        return containers;
    }
}

