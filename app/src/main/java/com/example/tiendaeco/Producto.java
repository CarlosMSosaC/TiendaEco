package com.example.tiendaeco;

import java.util.List;

public class Producto {
    private String nombre;
    private String descripcion;
    private double precio;
    private int imagen; // solo una imagen por producto

    public Producto(String nombre, String descripcion, double precio, int imagen) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.imagen = imagen;
    }

    // Getters
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public double getPrecio() { return precio; }
    public int getImagen() { return imagen; }
}


