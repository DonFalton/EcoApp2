package com.tgf.ecoapp.ui.others;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.tgf.ecoapp.R;

import java.util.HashMap;
import java.util.Map;

public class ContactFragment extends Fragment {
    private static final String TAG = "ContactFragment";

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private EditText etName, etSubject, etEmail, etMessage;
    private Button btnSend;

    public ContactFragment() {
        // Required empty public constructor
    }

    public static ContactFragment newInstance(String param1, String param2) {
        ContactFragment fragment = new ContactFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact, container, false);

        etName = view.findViewById(R.id.et_name);
        etSubject = view.findViewById(R.id.et_subject);
        etEmail = view.findViewById(R.id.et_email);
        etMessage = view.findViewById(R.id.et_message);
        btnSend = view.findViewById(R.id.btn_send);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });

        return view;
    }

    private void sendMessage() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String, Object> message = new HashMap<>();
        message.put("name", etName.getText().toString());
        message.put("subject", etSubject.getText().toString());
        message.put("email", etEmail.getText().toString());
        message.put("message", etMessage.getText().toString());

        db.collection("messages")
                .add(message)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        // Aquí puedes mostrar un mensaje de éxito o hacer cualquier otra cosa que necesites hacer después de enviar el mensaje
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Aquí puedes manejar cualquier error que ocurra al intentar enviar el mensaje
                        Log.w(TAG, "Error adding document", e);
                    }
                });
    }
}
