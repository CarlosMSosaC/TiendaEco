package com.example.tiendaeco;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CarritoAdapter extends RecyclerView.Adapter<CarritoAdapter.CarritoViewHolder> {

    private Context context;
    private List<Producto> productos;

    public CarritoAdapter(Context context, List<Producto> productos) {
        this.context = context;
        this.productos = productos;
    }

    @NonNull
    @Override
    public CarritoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(context).inflate(R.layout.item_carrito, parent, false);
        return new CarritoViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull CarritoViewHolder holder, int position) {
        Producto producto = productos.get(position);
        holder.tvNombre.setText(producto.getNombre());
        holder.tvDescripcion.setText(producto.getDescripcion());
        holder.tvPrecio.setText("Precio: $" + producto.getPrecio());
        holder.imagenProducto.setImageResource(producto.getImagen());
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    public static class CarritoViewHolder extends RecyclerView.ViewHolder {
        ImageView imagenProducto;
        TextView tvNombre, tvDescripcion, tvPrecio;

        public CarritoViewHolder(@NonNull View itemView) {
            super(itemView);
            imagenProducto = itemView.findViewById(R.id.imagen_producto);
            tvNombre = itemView.findViewById(R.id.nombre_producto);
            tvDescripcion = itemView.findViewById(R.id.descripcion_producto);
            tvPrecio = itemView.findViewById(R.id.precio_producto);
        }
    }
}
