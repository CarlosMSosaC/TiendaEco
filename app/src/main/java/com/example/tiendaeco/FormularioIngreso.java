package com.example.tiendaeco;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tiendaeco.MainActivity;
import com.example.tiendaeco.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import android.content.SharedPreferences;


public class FormularioIngreso extends AppCompatActivity {

    private Button btnGoogle;
    private GoogleSignInClient mGoogleSignInClient;
    private static final int RC_SIGN_IN = 123;
    private static final String TAG = "GoogleSignIn";

    private EditText etCorreo, etContrasena;
    private Button btnIngresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_ingreso);

        etCorreo = findViewById(R.id.etCorreo);
        etContrasena = findViewById(R.id.etContrasena);
        btnIngresar = findViewById(R.id.btnIngresar);

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String correoIngresado = etCorreo.getText().toString().trim();
                String contrasenaIngresada = etContrasena.getText().toString().trim();

                Cliente clienteGuardado = ClientePrefs.obtenerCliente(FormularioIngreso.this);

                if (clienteGuardado != null &&
                        clienteGuardado.getCorreo().equals(correoIngresado) &&
                        clienteGuardado.getContrasena().equals(contrasenaIngresada)) {

                    Toast.makeText(FormularioIngreso.this, "¡Bienvenido!", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(FormularioIngreso.this, CartaProductos.class);
                    intent.putExtra("USER_EMAIL", clienteGuardado.getCorreo());
                    intent.putExtra("USER_NAME", clienteGuardado.getNombreCompleto());
                    startActivity(intent);

                    etCorreo.setText("");
                    etContrasena.setText("");

                } else {
                    Toast.makeText(FormularioIngreso.this, "Correo o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Configurar Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestProfile()
                .build();

        // Crear el cliente de Google SignIn
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        btnGoogle = findViewById(R.id.btnIngresarGoogle);

        btnGoogle.setOnClickListener(v -> signIn());
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Inicio de sesión exitoso
            Log.d(TAG, "signInSuccess: " + account.getEmail());
            Toast.makeText(this, "Bienvenido " + account.getDisplayName(), Toast.LENGTH_SHORT).show();

            // GUARDAR EL NOMBRE EN SHARED PREFERENCES
            SharedPreferences prefs = getSharedPreferences("clientes_prefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("nombre", account.getDisplayName());
            editor.apply();

            Intent intent = new Intent(this, CartaProductos.class);
            intent.putExtra("USER_EMAIL", account.getEmail());
            intent.putExtra("USER_NAME", account.getDisplayName());
            startActivity(intent);

        } catch (ApiException e) {
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode(), e);
            Toast.makeText(this, "Error al iniciar sesión con Google", Toast.LENGTH_SHORT).show();
        }
    }
}
