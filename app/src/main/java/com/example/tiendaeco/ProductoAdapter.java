package com.example.tiendaeco;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.tiendaeco.OnProductoAgregadoListener;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder> {

    private Context context;
    private List<Producto> listaProductos;

    private OnProductoAgregadoListener listener;

    public ProductoAdapter(Context context, List<Producto> listaProductos, OnProductoAgregadoListener listener) {
        this.context = context;
        this.listaProductos = listaProductos;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(context).inflate(R.layout.item_producto, parent, false);
        return new ProductoViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoViewHolder holder, int position) {
        Producto producto = listaProductos.get(position);

        holder.tvNombre.setText(producto.getNombre());
        holder.tvDescripcion.setText(producto.getDescripcion());
        holder.tvPrecio.setText("Precio: $" + producto.getPrecio());
        holder.imagenProducto.setImageResource(producto.getImagen());

        holder.btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CarritoProductos.agregarProducto(producto);
                Toast.makeText(context, producto.getNombre() + " agregado al carrito", Toast.LENGTH_SHORT).show();
                if (listener != null){
                    listener.onProductoAgregado();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaProductos.size();
    }

    public static class ProductoViewHolder extends RecyclerView.ViewHolder {
        ImageView imagenProducto;
        TextView tvNombre, tvDescripcion, tvPrecio;
        Button btnAgregar;

        public ProductoViewHolder(@NonNull View itemView) {
            super(itemView);
            imagenProducto = itemView.findViewById(R.id.imagen_producto);
            tvNombre = itemView.findViewById(R.id.nombre_producto);
            tvDescripcion = itemView.findViewById(R.id.descripcion_producto);
            tvPrecio = itemView.findViewById(R.id.precio_producto);
            btnAgregar = itemView.findViewById(R.id.btnAgregar);
        }
    }
}


