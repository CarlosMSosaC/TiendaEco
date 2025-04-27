package com.example.tiendaeco;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class FormularioIngreso extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_ingreso);

        Button btnIngresar = findViewById(R.id.btnIngresar);

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FormularioIngreso.this, CartaProductos.class);
                startActivity(intent);

                EditText etCorreo = findViewById(R.id.etCorreo);
                EditText etContrasena = findViewById(R.id.etContrasena);

                etCorreo.setText("");  // Limpiar el campo de correo
                etContrasena.setText("");  // Limpiar el campo de contraseña
            }
        });
    }
}
