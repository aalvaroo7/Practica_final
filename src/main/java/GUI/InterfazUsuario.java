package GUI;

import Gestion_experimentos.Experimento;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfazUsuario extends JFrame {
    private JButton abrirArchivoButton;
    private JButton crearExperimentoButton;
    private JButton crearPoblacionButton;
    private JButton visualizarPoblacionesButton;
    private JButton borrarPoblacionButton;
    private JButton verInfoPoblacionButton;
    private JButton guardarButton;
    private JButton guardarComoButton;

    public InterfazUsuario() {
        setTitle("Interfaz de Usuario");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        abrirArchivoButton = new JButton("Abrir Archivo");
        crearExperimentoButton = new JButton("Crear Experimento");
        crearPoblacionButton = new JButton("Crear Población");
        visualizarPoblacionesButton = new JButton("Visualizar Poblaciones");
        borrarPoblacionButton = new JButton("Borrar Población");
        verInfoPoblacionButton = new JButton("Ver Info Población");
        guardarButton = new JButton("Guardar");
        guardarComoButton = new JButton("Guardar Como");

        abrirArchivoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implementar lógica para abrir archivo
                Experimento experimentoCargado = Experimento.abrirExperimento("ruta/al/archivo_experimento.txt");
                // Ahora puedes usar experimentoCargado para mostrar la información del experimento o hacer otras operaciones
            }
        });

        crearExperimentoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implementar lógica para crear experimento
            }
        });

        crearPoblacionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implementar lógica para crear población
            }
        });

        visualizarPoblacionesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implementar lógica para visualizar poblaciones
            }
        });

        borrarPoblacionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implementar lógica para borrar población
            }
        });

        verInfoPoblacionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implementar lógica para ver info de población
            }
        });

        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implementar lógica para guardar
            }
        });

        guardarComoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implementar lógica para guardar como
            }
        });

        add(abrirArchivoButton);
        add(crearExperimentoButton);
        add(crearPoblacionButton);
        add(visualizarPoblacionesButton);
        add(borrarPoblacionButton);
        add(verInfoPoblacionButton);
        add(guardarButton);
        add(guardarComoButton);
    }

    public static void main(String[] args) {
        InterfazUsuario interfazUsuario = new InterfazUsuario();
        interfazUsuario.setVisible(true);
    }
}
