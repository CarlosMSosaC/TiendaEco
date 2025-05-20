package com.example.tiendaeco;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FragmentCarrito extends Fragment {

    private RecyclerView recyclerView;
    private CarritoAdapter adapter;
    private TextView tvTotal;

    public FragmentCarrito() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_carrito, container, false);

        recyclerView = view.findViewById(R.id.recycler_carrito);
        tvTotal = view.findViewById(R.id.tvTotal);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        List<Producto> productos = CarritoProductos.obtenerProductos();
        adapter = new CarritoAdapter(getContext(), productos);
        recyclerView.setAdapter(adapter);

        mostrarTotal(productos);

        return view;
    }

    private void mostrarTotal(List<Producto> productos) {
        double total = 0;
        for (Producto producto : productos) {
            total += producto.getPrecio();
        }
        tvTotal.setText("Total: $" + String.format("%.2f", total));
    }
}

