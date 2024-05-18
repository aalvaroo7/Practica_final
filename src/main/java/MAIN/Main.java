package MAIN;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

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

    private Experimento experimentoActual;

    public Main() {
        setLayout(new FlowLayout());

        abrirArchivoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Use a file chooser to select the file
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    // Load the experiment from the file
                    experimento = Experimento.loadFromFile(selectedFile);
                }
            }
        });

        crearExperimentoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create a new experiment
                experimento = new Experimento();
            }
        });

        crearPoblacionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create a new population and add it to the current experiment
                Poblacion poblacion = new Poblacion();
                experimento.addPoblacion(poblacion);
            }
        });

        visualizarNombresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Display the names of all populations in the current experiment
                List<String> nombres = experimento.getNombresPoblaciones();
                for (String nombre : nombres) {
                    System.out.println(nombre);
                }
            }
        });

        borrarPoblacionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Remove a population from the current experiment
                // This could be done by asking the user for the name of the population to remove
                String nombre = JOptionPane.showInputDialog("Enter the name of the population to remove");
                experimento.removePoblacion(nombre);
            }
        });

        verInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Display detailed information about a population
                // This could be done by asking the user for the name of the population to display
                String nombre = JOptionPane.showInputDialog("Enter the name of the population to display");
                Poblacion poblacion = experimento.getPoblacion(nombre);
                System.out.println(poblacion.getDetailedInfo());
            }
        });

        realizarSimulacionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform and display the simulation for a population
                // This could be done by asking the user for the name of the population to simulate
                String nombre = JOptionPane.showInputDialog("Enter the name of the population to simulate");
                Poblacion poblacion = experimento.getPoblacion(nombre);
                Simulacion simulacion = new Simulacion(platoCultivo, poblacion);
                simulacion.simular();
                simulacion.mostrarResultados();
            }
        });

        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Save the current experiment
                experimento.save();
            }
        });

        guardarComoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Save the current experiment to a new file
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showSaveDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    experimento.saveAs(selectedFile);
                }
            }
        });

    public static void main(String[] args) {
        new Main();
    }
}