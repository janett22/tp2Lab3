package com.janett.tp2lab3.model.modelo.ui.registro;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.janett.tp2lab3.model.modelo.modelo.Usuario;
import com.janett.tp2lab3.model.modelo.request.ApiClient;
import com.janett.tp2lab3.model.modelo.ui.Login.MainActivity;


public class RegistroActivityViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Usuario> usuario;
    private ApiClient apiClient;

    public RegistroActivityViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        apiClient = new ApiClient();
    }
    public LiveData<Usuario> getUsuario() {
        if (usuario == null) {
            usuario = new MutableLiveData<>();
        }
        return usuario;
    }


    public void guardarDatos(Long dni, String nombre, String apellido, String mail, String contraseña) {
        Usuario usuario = new Usuario(dni, nombre, apellido, mail, contraseña);
        apiClient.guardar(context, usuario);
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public void cargarDatos(Boolean login) {
        if(login) {
            Usuario usuario = apiClient.leer();
            this.usuario.setValue(usuario);
        }
    }
}
