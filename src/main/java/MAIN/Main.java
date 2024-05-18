package MAIN;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;

import Gestion_experimentos.*;
import SimulacionMontecarlo.Simulacion;

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

    private Experimento experimento;
    private PlatoCultivo platoCultivo;

    public Main() {
        setLayout(new FlowLayout());

        abrirArchivoButton = new JButton("Abrir un archivo que contenga un experimento");
        abrirArchivoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Use a file chooser to select the file
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    // Load the experiment from the file
                    experimento = Experimento.cargarExperimento(selectedFile.getAbsolutePath());
                }
            }
        });
        add(abrirArchivoButton);

        crearExperimentoButton = new JButton("Crear un nuevo experimento");
        crearExperimentoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create a new experiment
                experimento = new Experimento("Experimento 1", new Date(), 10, 10, new ArrayList<Bacteria>());
            }
        });
        add(crearExperimentoButton);

        crearPoblacionButton = new JButton("Crear una población de bacterias y añadirla al experimento actual");
        crearPoblacionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create a new population and add it to the current experiment
                Poblacion poblacion = new Poblacion("Poblacion 1", new Date(), 10, platoCultivo);
                experimento.addPoblacion(poblacion);
            }
        });
        add(crearPoblacionButton);

        visualizarNombresButton = new JButton("Visualizar los nombres de todas las poblaciones de bacterias del experimento actual");
        visualizarNombresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Display the names of all populations in the current experiment
                ArrayList<Bacteria> bacterias = experimento.getPoblacionesBacterias();
                for (Bacteria bacteria : bacterias) {
                    System.out.println(bacteria.getNombre());
                }
            }
        });
        add(visualizarNombresButton);

        borrarPoblacionButton = new JButton("Borrar una población de bacterias del experimento actual");
        borrarPoblacionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Remove a population from the current experiment
                // This could be done by asking the user for the name of the population to remove
                String nombre = JOptionPane.showInputDialog("Enter the name of the population to remove");
                Bacteria bacteriaToRemove = null;
                for (Bacteria bacteria : experimento.getPoblacionesBacterias()) {
                    if (bacteria.getNombre().equals(nombre)) {
                        bacteriaToRemove = bacteria;
                        break;
                    }
                }
                if (bacteriaToRemove != null) {
                    experimento.removePoblacion(bacteriaToRemove);
                }
            }
        });
        add(borrarPoblacionButton);

        verInfoButton = new JButton("Ver información detallada de una población de bacterias del experimento actual");
        verInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Display detailed information about a population
                // This could be done by asking the user for the name of the population to display
                String nombre = JOptionPane.showInputDialog("Enter the name of the population to display");
                for (Bacteria bacteria : experimento.getPoblacionesBacterias()) {
                    if (bacteria.getNombre().equals(nombre)) {
                        System.out.println("Nombre: " + bacteria.getNombre());
                        System.out.println("Fecha: " + bacteria.getFecha());
                        System.out.println("Cantidad: " + bacteria.getCantidad());
                        break;
                    }
                }
            }
        });
        add(verInfoButton);

        realizarSimulacionButton = new JButton("Realizar y visualizar la simulación correspondiente con una de las poblaciones de bacterias del experimento");
        realizarSimulacionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform and display the simulation for a population
                // This could be done by asking the user for the name of the population to simulate
                String nombre = JOptionPane.showInputDialog("Enter the name of the population to simulate");
                for (Bacteria bacteria : experimento.getPoblacionesBacterias()) {
                    if (bacteria.getNombre().equals(nombre)) {
                        Simulacion simulacion = new Simulacion(platoCultivo, experimento);
                        simulacion.simularDia();
                        break;
                    }
                }
            }
        });
        add(realizarSimulacionButton);

        guardarButton = new JButton("Guardar");
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Save the current experiment
                experimento.guardarExperimento("experimento.ser");
            }
        });
        add(guardarButton);

        guardarComoButton = new JButton("Guardar como");
        guardarComoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Save the current experiment to a new file
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showSaveDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    experimento.guardarExperimento(selectedFile.getAbsolutePath());
                }
            }
        });
        add(guardarComoButton);

        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }
}