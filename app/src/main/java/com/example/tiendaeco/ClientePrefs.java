package com.example.tiendaeco;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;

public class ClientePrefs {
    private static final String PREF_NAME = "clientes_prefs";
    private static final String KEY_CLIENTE = "cliente_data";

    public static void guardarCliente(Context context, Cliente cliente) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        Gson gson = new Gson();
        String clienteJson = gson.toJson(cliente);

        editor.putString(KEY_CLIENTE, clienteJson);
        editor.apply();
    }

    public static Cliente obtenerCliente(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        String clienteJson = prefs.getString(KEY_CLIENTE, null);

        if (clienteJson != null) {
            Gson gson = new Gson();
            return gson.fromJson(clienteJson, Cliente.class);
        }
        return null;
    }

    public static void eliminarCliente(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove(KEY_CLIENTE);
        editor.apply();
    }

    public static boolean existeCliente(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return prefs.contains(KEY_CLIENTE);
    }
}

