package org.example.reservaseventos.controller;

import org.example.reservaseventos.model.Evento;

import java.util.ArrayList;
import java.util.List;

public class EventoController {
    private List<Evento> eventos;

    public EventoController() {
        eventos = new ArrayList<>();
    }

    public void agregarEvento(Evento evento) {
        eventos.add(evento);
    }

    public void eliminarEvento(Evento evento) {
        eventos.remove(evento);
    }

    public List<Evento> getEventos() {
        return eventos;
    }
}
