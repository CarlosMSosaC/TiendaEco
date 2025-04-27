package com.example.tiendaeco;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FormularioRegistro extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acttivity_formulario_registro);

        Button btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FormularioRegistro.this, FormularioIngreso.class);
                startActivity(intent);
            }
        });

        Button btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aquí se puede hacer el proceso de registro, como almacenar en base de datos, etc.

                // Mostrar un mensaje de "Registro exitoso"
                Toast.makeText(getApplicationContext(), "¡Registro exitoso!", Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), "¡Ya puedes ingresar!", Toast.LENGTH_SHORT).show();

                EditText etNombre = findViewById(R.id.etNombre);
                EditText etCorreo = findViewById(R.id.etCorreo);
                EditText etContrasena = findViewById(R.id.etContrasena);
                EditText etTelefonoFormulario = findViewById(R.id.etTelefonoFormulario);
                EditText etDireccionFormulario = findViewById(R.id.etDireccionFormulario);

                etNombre.setText("");  // Limpiar el campo de nombre
                etCorreo.setText("");  // Limpiar el campo de correo
                etContrasena.setText("");  // Limpiar el campo de contraseña
                etTelefonoFormulario.setText("");  // Limpiar el campo de teléfono
                etDireccionFormulario.setText("");  // Limpiar el campo de dirección

                // Aquí también puedes agregar el código para redirigir al login, si lo deseas
                // Intent intent = new Intent(ActivityFormulario.this, LoginActivity.class);
                // startActivity(intent);
                // finish();
            }
        });

    }
}