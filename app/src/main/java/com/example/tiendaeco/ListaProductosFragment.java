package com.example.tiendaeco;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tiendaeco.R;
import com.example.tiendaeco.Producto;

import java.util.ArrayList;
import java.util.List;

public class ListaProductosFragment extends Fragment {

    private RecyclerView recyclerView;
    private ProductoAdapter adapter;
    private List<Producto> listaProductos;

    public ListaProductosFragment() {
        // Constructor vacío obligatorio
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflar el layout del fragmento
        View view = inflater.inflate(R.layout.fragment_lista_productos, container, false);
        recyclerView = view.findViewById(R.id.recycler_productos);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        cargarProductos(); // Llena la lista con 4 productos

        adapter = new ProductoAdapter(getContext(), listaProductos);
        recyclerView.setAdapter(adapter);

        return view;
    }

    private void cargarProductos() {
        listaProductos = new ArrayList<>();
        listaProductos.add(new Producto("Cepillo de Bambú", "Cepillo ecológico", 10.99, R.drawable.toothbrush));
        listaProductos.add(new Producto("Serum Facial", "Hecho con aceites esenciales", 7.50, R.drawable.serum));
        listaProductos.add(new Producto("Silla de bambú", "100% orgánico", 4.99, R.drawable.wood_chair));
        listaProductos.add(new Producto("Espejo de pared", "Mantiene bebidas frías/calientes", 15.00, R.drawable.mirror));
    }
}

