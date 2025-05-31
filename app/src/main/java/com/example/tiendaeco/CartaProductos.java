package com.example.tiendaeco;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class CartaProductos extends AppCompatActivity implements OnProductoAgregadoListener, OnProductoEliminadoListener {

    private TextView cartItemCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_productos);
        cartItemCount = findViewById(R.id.cartItemCount);

        // Cargar el fragmento de productos
        cargarFragmento(new ListaProductosFragment());
    }

    private void cargarFragmento(Fragment fragmento) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.contenedor_fragmento, fragmento)
                .commit();
    }

    // Metodo para actualizar el contador del carrito
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

    @Override
    public void onProductoEliminado() {
        actualizarContadorCarrito();
    }

}

