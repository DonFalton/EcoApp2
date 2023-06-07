package com.tgf.ecoapp.ui.home;

import static android.content.Context.MODE_PRIVATE;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class HomeViewModel extends AndroidViewModel {

    private final MutableLiveData<String> mText;
    private static final String PREFS_NAME = "MyPrefs";
    private static final String FRASE_KEY = "FraseKey";

    public HomeViewModel(Application application) {
        super(application);
        mText = new MutableLiveData<>();

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Obtén el número de la última frase que se mostró de SharedPreferences
        SharedPreferences prefs = getApplication().getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        int lastFraseNumber = prefs.getInt(FRASE_KEY, 0);

        // Calcula el número de la próxima frase
        int nextFraseNumber = (lastFraseNumber % 40) + 1;

        // Recupera el documento que tiene el "Día" igual al número calculado
        db.collection("FRASES")
                .whereEqualTo("Día", String.valueOf(nextFraseNumber))  // Asegúrate de que "Día" esté escrito exactamente como en tu base de datos
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (DocumentSnapshot document : task.getResult()) {
                            String data = document.getString("Frase");
                            mText.setValue(data);
                        }

                        // Almacena el número de la frase mostrada en SharedPreferences
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putInt(FRASE_KEY, nextFraseNumber);
                        editor.apply();
                    }
                });
    }

    public LiveData<String> getText() {
        return mText;
    }
}
