package org.example.reservaseventos.model;

public class Reserva {
    private Evento evento;
    private String usuario;
    private boolean confirmada;

    public Reserva(Evento evento, String usuario, boolean confirmada) {
        this.evento = evento;
        this.usuario = usuario;
        this.confirmada = confirmada;
    }

    // Getters y setters
    public Evento getEvento() { return evento; }
    public void setEvento(Evento evento) { this.evento = evento; }

    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }

    public boolean isConfirmada() { return confirmada; }
    public void setConfirmada(boolean confirmada) { this.confirmada = confirmada; }
}
