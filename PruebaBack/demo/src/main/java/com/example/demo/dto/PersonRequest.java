package com.example.demo.dto;

public class PersonRequest {
    private String nombre;
    private String apellido;

    public PersonRequest() {}

    public PersonRequest(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }
}