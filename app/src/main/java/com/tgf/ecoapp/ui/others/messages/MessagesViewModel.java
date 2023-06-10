package com.tgf.ecoapp.ui.others.messages;

import android.os.Message;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

/**
 * Created by Martin B. on 5/6/23.
 * martin.blazquez.dam@gmail.com
 */
public class MessagesViewModel extends ViewModel {

    private final MutableLiveData<List<Message>> messages;

    public MessagesViewModel() {
        messages = new MutableLiveData<>();
        // Carga los mensajes iniciales
        loadMessages();
    }

    private void loadMessages() {
        // Aquí iría la lógica para cargar los mensajes, por ejemplo desde una base de datos
        // Cuando los mensajes estén cargados, puedes actualizar el LiveData
        // messages.setValue(loadedMessages);
    }

    public LiveData<List<Message>> getMessages() {
        return messages;
    }
}