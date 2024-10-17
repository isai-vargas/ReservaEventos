package org.example.reservaseventos.view;

import org.example.reservaseventos.controller.EventoController;
import org.example.reservaseventos.controller.ReservaController;
import org.example.reservaseventos.model.Evento;
import org.example.reservaseventos.model.Reserva;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainView extends JFrame {
    private EventoController eventoController;
    private ReservaController reservaController;

    private JTextField txtNombreEvento;
    private JTextField txtFechaEvento;
    private JTextField txtLugarEvento;
    private JTextField txtUsuarioReserva;
    private JTextArea txtReservasRecientes;
    private JTextArea txtReservasPendientes;

    public MainView(EventoController eventoController, ReservaController reservaController) {
        this.eventoController = eventoController;
        this.reservaController = reservaController;
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        setTitle("Gestión de Reservas de Eventos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panelFormulario = new JPanel(new GridLayout(5, 2));
        panelFormulario.add(new JLabel("Nombre del Evento:"));
        txtNombreEvento = new JTextField();
        panelFormulario.add(txtNombreEvento);

        panelFormulario.add(new JLabel("Fecha del Evento:"));
        txtFechaEvento = new JTextField();
        panelFormulario.add(txtFechaEvento);

        panelFormulario.add(new JLabel("Lugar del Evento:"));
        txtLugarEvento = new JTextField();
        panelFormulario.add(txtLugarEvento);

        panelFormulario.add(new JLabel("Usuario para Reserva:"));
        txtUsuarioReserva = new JTextField();
        panelFormulario.add(txtUsuarioReserva);

        JButton btnAgregarReserva = new JButton("Agregar Reserva");
        panelFormulario.add(btnAgregarReserva);
        btnAgregarReserva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarReserva();
            }
        });

        JButton btnEliminarReciente = new JButton("Eliminar Última Reserva");
        panelFormulario.add(btnEliminarReciente);
        btnEliminarReciente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarReservaReciente();
            }
        });

        JButton btnConfirmarPendiente = new JButton("Confirmar Reserva Pendiente");
        panelFormulario.add(btnConfirmarPendiente);
        btnConfirmarPendiente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmarReservaPendiente();
            }
        });

        add(panelFormulario, BorderLayout.NORTH);

        JPanel panelReservas = new JPanel(new GridLayout(1, 2));
        txtReservasRecientes = new JTextArea();
        txtReservasPendientes = new JTextArea();
        panelReservas.add(new JScrollPane(txtReservasRecientes));
        panelReservas.add(new JScrollPane(txtReservasPendientes));
        add(panelReservas, BorderLayout.CENTER);

        actualizarListasDeReservas();
    }

    private void agregarReserva() {
        String nombreEvento = txtNombreEvento.getText();
        String fechaEvento = txtFechaEvento.getText();
        String lugarEvento = txtLugarEvento.getText();
        String usuario = txtUsuarioReserva.getText();

        Evento evento = new Evento(nombreEvento, fechaEvento, lugarEvento);
        Reserva reserva = new Reserva(evento, usuario, false);

        eventoController.agregarEvento(evento);
        reservaController.agregarReserva(reserva); // Agregar a reservas recientes y pendientes

        txtNombreEvento.setText("");
        txtFechaEvento.setText("");
        txtLugarEvento.setText("");
        txtUsuarioReserva.setText("");

        actualizarListasDeReservas();
    }

    private void eliminarReservaReciente() {
        Reserva reservaEliminada = reservaController.eliminarReservaReciente();
        if (reservaEliminada != null) {
            JOptionPane.showMessageDialog(this, "Reserva reciente eliminada: " + reservaEliminada.getEvento().mostrarInfo());
        } else {
            JOptionPane.showMessageDialog(this, "No hay reservas recientes para eliminar.");
        }
        actualizarListasDeReservas();
    }

    private void confirmarReservaPendiente() {
        Reserva reservaConfirmada = reservaController.confirmarReservaPendiente();
        if (reservaConfirmada != null) {
            JOptionPane.showMessageDialog(this, "Reserva pendiente confirmada: " + reservaConfirmada.getEvento().mostrarInfo());
        } else {
            JOptionPane.showMessageDialog(this, "No hay reservas pendientes para confirmar.");
        }
        actualizarListasDeReservas();
    }

    private void actualizarListasDeReservas() {
        StringBuilder reservasRecientes = new StringBuilder("Reservas Recientes:\n");
        for (Reserva reserva : reservaController.getReservasRecientes()) {
            reservasRecientes.append(reserva.getEvento().mostrarInfo()).append(" - ").append(reserva.getUsuario()).append("\n");
        }
        txtReservasRecientes.setText(reservasRecientes.toString());

        StringBuilder reservasPendientes = new StringBuilder("Reservas Pendientes:\n");
        for (Reserva reserva : reservaController.getReservasPendientes()) {
            reservasPendientes.append(reserva.getEvento().mostrarInfo()).append(" - ").append(reserva.getUsuario()).append("\n");
        }
        txtReservasPendientes.setText(reservasPendientes.toString());
    }

    public static void main(String[] args) {
        EventoController eventoController = new EventoController();
        ReservaController reservaController = new ReservaController();
        MainView mainView = new MainView(eventoController, reservaController);
        mainView.setVisible(true);
    }
}
