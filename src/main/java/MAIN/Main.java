package MAIN;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.ArrayList;
import Gestion_experimentos.*;

public class Main extends JFrame {
    private JButton abrirArchivoButton;
    private JButton crearExperimentoButton;
    private JButton crearPoblacionButton;
    private JButton visualizarNombresButton;
    private JButton borrarPoblacionButton;
    private JButton verInfoButton;
    private JButton realizarSimulacionButton;
    private JButton guardarButton;
    private JButton guardarComoButton;

    private Experimento experimentoActual;

    public MainFrame() {
        setLayout(new FlowLayout());

        abrirArchivoButton = new JButton("Abrir un archivo que contenga un experimento");
        abrirArchivoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Código para abrir un archivo que contenga un experimento
            }
        });
        add(abrirArchivoButton);

        crearExperimentoButton = new JButton("Crear un nuevo experimento");
        crearExperimentoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Código para crear un nuevo experimento
            }
        });
        add(crearExperimentoButton);

        crearPoblacionButton = new JButton("Crear una población de bacterias y añadirla al experimento actual");
        crearPoblacionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Código para crear una población de bacterias y añadirla al experimento actual
            }
        });
        add(crearPoblacionButton);

        visualizarNombresButton = new JButton("Visualizar los nombres de todas las poblaciones de bacterias del experimento actual");
        visualizarNombresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Código para visualizar los nombres de todas las poblaciones de bacterias del experimento actual
            }
        });
        add(visualizarNombresButton);

        borrarPoblacionButton = new JButton("Borrar una población de bacterias del experimento actual");
        borrarPoblacionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Código para borrar una población de bacterias del experimento actual
            }
        });
        add(borrarPoblacionButton);

        verInfoButton = new JButton("Ver información detallada de una población de bacterias del experimento actual");
        verInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Código para ver información detallada de una población de bacterias del experimento actual
            }
        });
        add(verInfoButton);

        realizarSimulacionButton = new JButton("Realizar y visualizar la simulación correspondiente con una de las poblaciones de bacterias del experimento");
        realizarSimulacionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Código para realizar y visualizar la simulación correspondiente con una de las poblaciones de bacterias del experimento
            }
        });
        add(realizarSimulacionButton);

        guardarButton = new JButton("Guardar");
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Código para guardar
            }
        });
        add(guardarButton);

        guardarComoButton = new JButton("Guardar como");
        guardarComoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Código para guardar como
            }
        });
        add(guardarComoButton);

        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new MainFrame();
    }
}