package com.example.tiendaeco;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FormularioRegistro extends AppCompatActivity {

    private EditText etNombre, etCorreo, etContrasena, etTelefonoFormulario, etDireccionFormulario;
    private Button btnRegister, btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acttivity_formulario_registro);

        etNombre = findViewById(R.id.etNombre);
        etCorreo = findViewById(R.id.etCorreo);
        etContrasena = findViewById(R.id.etContrasena);
        etTelefonoFormulario = findViewById(R.id.etTelefonoFormulario);
        etDireccionFormulario = findViewById(R.id.etDireccionFormulario);

        btnRegister = findViewById(R.id.btnRegister);
        btnLogin = findViewById(R.id.btnLogin);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener datos del formulario
                String nombre = etNombre.getText().toString().trim();
                String correo = etCorreo.getText().toString().trim();
                String contrasena = etContrasena.getText().toString().trim();
                String telefono = etTelefonoFormulario.getText().toString().trim();
                String direccion = etDireccionFormulario.getText().toString().trim();

                // Validación básica (opcional)
                if (nombre.isEmpty() || correo.isEmpty() || contrasena.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Por favor, completa todos los campos obligatorios.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Crear objeto cliente
                Cliente nuevoCliente = new Cliente(nombre, correo, contrasena, telefono, direccion);

                // Guardar en SharedPreferences
                ClientePrefs.guardarCliente(FormularioRegistro.this, nuevoCliente);

                Toast.makeText(getApplicationContext(), "¡Registro exitoso!", Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), "¡Ya puedes ingresar!", Toast.LENGTH_SHORT).show();

                SharedPreferences prefs = getSharedPreferences("clientes_prefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("nombre", etNombre.getText().toString());
                editor.apply();


                // Limpiar campos
                etNombre.setText("");
                etCorreo.setText("");
                etContrasena.setText("");
                etTelefonoFormulario.setText("");
                etDireccionFormulario.setText("");

                // Redirigir al formulario de ingreso
                Intent intent = new Intent(FormularioRegistro.this, FormularioIngreso.class);
                startActivity(intent);
                finish();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FormularioRegistro.this, FormularioIngreso.class);
                startActivity(intent);
            }
        });
    }
}