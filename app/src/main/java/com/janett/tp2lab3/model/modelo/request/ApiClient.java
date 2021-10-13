package com.janett.tp2lab3.model.modelo.request;

import android.content.Context;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.janett.tp2lab3.model.modelo.modelo.Usuario;

public class ApiClient {
    private static Usuario usuario;

    private static Usuario conectar(Usuario u)
    {
        usuario= u;
        return usuario;
    }

    public static void guardar(Context context, Usuario u)
    {
        Usuario usuario =conectar(u);
        File nuevo = new File(context.getFilesDir(),"usuario.obj");

        try {
            FileOutputStream fileOut=new FileOutputStream(nuevo);
            BufferedOutputStream buffer = new BufferedOutputStream(fileOut);
            ObjectOutputStream salida=new ObjectOutputStream(buffer);
            salida.writeObject(usuario);
            salida.close();
        } catch (FileNotFoundException e) {
            Toast.makeText(context.getApplicationContext(),"Archivo no existente",Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Toast.makeText(context.getApplicationContext(),"No se puede grabar "+e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

    public static Usuario leer() {
        return usuario;
    }

    public static Usuario login(Context context, String mail, String pass) {
        Usuario us = null;
        File nuevo = new File(context.getFilesDir(), "usuario.obj");

        try {
            FileInputStream fileOut = new FileInputStream(nuevo);
            BufferedInputStream buffer = new BufferedInputStream(fileOut);
            ObjectInputStream entrada = new ObjectInputStream(buffer);
            Usuario usu = (Usuario) entrada.readObject();
            if (mail.equals(usu.getEmail()) && pass.equals(usu.getPassword())) {
                us = conectar(usu);

            }
            entrada.close();
        } catch (IOException e) {
            Toast.makeText(context.getApplicationContext(), "Usuario no existente", Toast.LENGTH_LONG).show();
        } catch (ClassNotFoundException e) {
            Toast.makeText(context.getApplicationContext(), "No se puede leer objeto" + e.getMessage(), Toast.LENGTH_LONG).show();
        }
        return us;
    }
}