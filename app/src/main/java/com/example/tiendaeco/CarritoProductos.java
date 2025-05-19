package com.example.tiendaeco;

import java.util.ArrayList;
import java.util.List;

public class CarritoProductos {
    private static List<Producto> productosCarrito = new ArrayList<>();

    public static void agregarProducto(Producto producto) {
        productosCarrito.add(producto);
    }

    public static List<Producto> obtenerProductos() {
        return new ArrayList<>(productosCarrito); // evitar referencia directa
    }

    public static void vaciarCarrito() {
        productosCarrito.clear();
    }
}