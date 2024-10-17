package org.example.reservaseventos.model;

public class Evento extends BaseEvento {

    public Evento(String nombre, String fecha, String lugar) {
        super(nombre, fecha, lugar);
    }

    @Override
    public String mostrarInfo() {
        return "Detalles del Evento: " + nombre + " en " + lugar + " el " + fecha;
    }
}
