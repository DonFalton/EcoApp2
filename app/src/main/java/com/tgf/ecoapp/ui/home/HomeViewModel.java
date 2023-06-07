package com.tgf.ecoapp.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();

        // Obtiene la referencia a Firestore
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Accede a tu documento
        db.collection("FRASES").document("YkCRcGBxvrNdD1zhd2PC")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            // Asume que el campo que quieres mostrar se llama "miCampo"
                            String data = document.getString("Frase");
                            mText.setValue(data);
                        }
                    }
                });
    }

    public LiveData<String> getText() {
        return mText;
    }
}
