package com.example.tiendaeco;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class CartaProductos extends AppCompatActivity implements OnProductoAgregadoListener {

    private TextView cartItemCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_productos); // este layout debe contener un FrameLayout

        cartItemCount = findViewById(R.id.cartItemCount); // Debes tener un TextView con este ID junto al icono de carrito
        actualizarContadorCarrito(); // Mostrar el contador al iniciar

        // Cargar el fragmento de productos
        cargarFragmento(new ListaProductosFragment());
    }

    private void cargarFragmento(Fragment fragmento) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.contenedor_fragmento, fragmento)
                .commit();
    }

    // Método para actualizar el contador del carrito
    public void actualizarContadorCarrito() {
        int total = CarritoProductos.obtenerProductos().size();
        if (total > 0) {
            cartItemCount.setText(String.valueOf(total));
            cartItemCount.setVisibility(View.VISIBLE);
        } else {
            cartItemCount.setVisibility(View.GONE);
        }
    }
    @Override
    public void onProductoAgregado() {
        actualizarContadorCarrito();
    }



}

