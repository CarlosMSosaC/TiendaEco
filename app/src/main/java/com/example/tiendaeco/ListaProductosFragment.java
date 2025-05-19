package com.example.tiendaeco;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
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

        adapter = new ProductoAdapter(getContext(), listaProductos, (OnProductoAgregadoListener) getActivity());
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AppCompatActivity activity = (AppCompatActivity) getActivity();

        if (activity != null) {
            // Accede al icono del carrito en la Activity
            ImageView ivCart = activity.findViewById(R.id.ivCart);
            if (ivCart != null) {
                ivCart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(activity, ActivityCarrito.class);
                        activity.startActivity(intent);
                    }
                });
            }

            // Actualiza el contador del carrito
            TextView cartItemCount = activity.findViewById(R.id.cartItemCount);
            if (cartItemCount != null) {
                int total = CarritoProductos.obtenerProductos().size();
                if (total > 0) {
                    cartItemCount.setText(String.valueOf(total));
                    cartItemCount.setVisibility(View.VISIBLE);
                } else {
                    cartItemCount.setVisibility(View.GONE);
                }
            }
        }
    }

    private void cargarProductos() {
        listaProductos = new ArrayList<>();
        listaProductos.add(new Producto("Cepillo de Bambú", "Cepillo ecológico", 10.99, R.drawable.toothbrush));
        listaProductos.add(new Producto("Serum Facial", "Hecho con aceites esenciales", 7.50, R.drawable.serum));
        listaProductos.add(new Producto("Silla de bambú", "100% orgánico", 4.99, R.drawable.wood_chair));
        listaProductos.add(new Producto("Espejo de pared", "Espejo de 30cm de diametro", 15.00, R.drawable.mirror));
    }


}

