package com.example.tiendaeco;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.List;

public class ActivityCarrito extends AppCompatActivity {

    private RecyclerView recyclerCarrito;
    private CarritoAdapter adapter;
    private List<Producto> productosEnCarrito;
    private TextView tvTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);

        recyclerCarrito = findViewById(R.id.recycler_carrito);
        recyclerCarrito.setLayoutManager(new LinearLayoutManager(this));

        tvTotal = findViewById(R.id.tvTotal); // Asegúrate de tener este TextView en tu layout

        // Obtener productos del carrito
        productosEnCarrito = CarritoProductos.obtenerProductos();
        adapter = new CarritoAdapter(this, productosEnCarrito);
        recyclerCarrito.setAdapter(adapter);

        mostrarTotal();
    }

    private void mostrarTotal() {
        double total = 0;
        for (Producto producto : productosEnCarrito) {
            total += producto.getPrecio();
        }

        if (tvTotal != null) {
            tvTotal.setText("Total: $" + String.format("%.2f", total));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Actualizar lista cuando se vuelve a mostrar la activity
        productosEnCarrito.clear();
        productosEnCarrito.addAll(CarritoProductos.obtenerProductos());
        adapter.notifyDataSetChanged();
    }
}

