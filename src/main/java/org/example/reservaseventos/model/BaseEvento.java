package org.example.reservaseventos.model;

public class BaseEvento {
    protected String nombre;
    protected String fecha;
    protected String lugar;

    public BaseEvento(String nombre, String fecha, String lugar) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.lugar = lugar;
    }

    public String getNombre() {
        return nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public String getLugar() {
        return lugar;
    }

    // Método que puede ser sobrescrito en la subclase
    public String mostrarInfo() {
        return "Evento: " + nombre + ", Fecha: " + fecha + ", Lugar: " + lugar;
    }
}
