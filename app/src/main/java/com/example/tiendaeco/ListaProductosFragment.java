package com.example.tiendaeco;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class ListaProductosFragment extends Fragment implements OnProductoAgregadoListener {

    private RecyclerView recyclerView;
    private ProductoAdapter adapter;
    private List<Producto> listaProductos;

    private TextView tvNombreCliente;

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

        adapter = new ProductoAdapter(getContext(), listaProductos, this);
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView ivCart = view.findViewById(R.id.ivCart);  // Buscar en la vista del fragmento
        TextView cartItemCount = view.findViewById(R.id.cartItemCount);

        // Aquí agregamos el TextView para el nombre del cliente
        TextView tvNombreCliente = view.findViewById(R.id.tvNombreCliente);
        SharedPreferences prefs = requireContext().getSharedPreferences("clientes_prefs", Context.MODE_PRIVATE);
        String nombre = prefs.getString("nombre", "Cliente");
        tvNombreCliente.setText(nombre);

        if (ivCart != null) {
            ivCart.setOnClickListener(v -> {
                Intent intent = new Intent(getActivity(), ActivityCarrito.class);
                startActivity(intent);
            });
        }

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

    @Override
    public void onProductoAgregado() {
        TextView cartItemCount = getView().findViewById(R.id.cartItemCount);
        int total = CarritoProductos.obtenerProductos().size();
        if (cartItemCount != null) {
            if (total > 0) {
                cartItemCount.setText(String.valueOf(total));
                cartItemCount.setVisibility(View.VISIBLE);
            } else {
                cartItemCount.setVisibility(View.GONE);
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

