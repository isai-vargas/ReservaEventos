package org.example.reservaseventos.controller;

import org.example.reservaseventos.model.Reserva;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class ReservaController {
    private Stack<Reserva> reservasRecientes; // Para gestionar las reservas recientes
    private Queue<Reserva> reservasPendientes; // Para gestionar las reservas en espera

    public ReservaController() {
        reservasRecientes = new Stack<>();
        reservasPendientes = new LinkedList<>();
    }

    public void agregarReserva(Reserva reserva) {
        reservasRecientes.push(reserva); // Agregar a la pila de reservas recientes
        agregarReservaPendiente(reserva); // También agregar a la cola de reservas pendientes
    }

    public Reserva eliminarReservaReciente() {
        if (!reservasRecientes.isEmpty()) {
            return reservasRecientes.pop(); // Eliminar la reserva más reciente
        }
        return null;
    }

    public void agregarReservaPendiente(Reserva reserva) {
        reservasPendientes.add(reserva); // Agregar a la cola de reservas pendientes
    }

    public Reserva confirmarReservaPendiente() {
        if (!reservasPendientes.isEmpty()) {
            return reservasPendientes.poll(); // Confirmar la reserva más antigua en espera
        }
        return null;
    }

    public Stack<Reserva> getReservasRecientes() {
        return reservasRecientes;
    }

    public Queue<Reserva> getReservasPendientes() {
        return reservasPendientes;
    }
}
