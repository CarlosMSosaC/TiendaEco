package com.example.tiendaeco;

import java.util.ArrayList;
import java.util.List;

public class CarritoProductos {
    private static List<Producto> productosEnCarrito = new ArrayList<>();

    public static void agregarProducto(Producto producto) {
        productosEnCarrito.add(producto);
    }

    public static List<Producto> obtenerProductos() {
        return productosEnCarrito; // evitar referencia directa
    }

    public static void eliminarProducto(Producto producto) {
        productosEnCarrito.remove(producto);
    }

    public static void limpiarCarrito() {
        productosEnCarrito.clear();
    }
}