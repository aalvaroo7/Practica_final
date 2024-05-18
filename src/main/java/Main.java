import Gestion_experimentos.Bacteria;
import Gestion_experimentos.Experimento;
import Gestion_experimentos.PlatoCultivo;
import Gestion_experimentos.Poblacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Date;

public class Main {
    private JFrame frame;
    private JButton openButton, newExperimentButton, createPopulationButton, visualizePopulationsButton, deletePopulationButton, detailsButton, simulateButton, saveButton, saveAsButton;
    private Experimento currentExperiment;

    public Main() {
        frame = new JFrame("Bacteria Simulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        openButton = new JButton("Abrir archivo de experimento");
        openButton.addActionListener(new OpenFileListener());
        frame.add(openButton);

        newExperimentButton = new JButton("Crear nuevo experimento");
        newExperimentButton.addActionListener(new NewExperimentListener());
        frame.add(newExperimentButton);

        createPopulationButton = new JButton("Crear población de bacterias");
        createPopulationButton.addActionListener(new CreatePopulationListener());
        frame.add(createPopulationButton);

        visualizePopulationsButton = new JButton("Visualizar poblaciones");
        visualizePopulationsButton.addActionListener(new VisualizePopulationsListener());
        frame.add(visualizePopulationsButton);

        deletePopulationButton = new JButton("Borrar población");
        deletePopulationButton.addActionListener(new DeletePopulationListener());
        frame.add(deletePopulationButton);

        detailsButton = new JButton("Ver detalles de población");
        detailsButton.addActionListener(new DetailsListener());
        frame.add(detailsButton);

        simulateButton = new JButton("Simular población");
        simulateButton.addActionListener(new SimulateListener());
        frame.add(simulateButton);

        saveButton = new JButton("Guardar");
        saveButton.addActionListener(new SaveListener());
        frame.add(saveButton);

        saveAsButton = new JButton("Guardar como");
        saveAsButton.addActionListener(new SaveAsListener());
        frame.add(saveAsButton);

        frame.pack();
        frame.setVisible(true);
    }

    private class OpenFileListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Show a dialog to select a file
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                try {
                    // Load the experiment from the selected file
                    currentExperiment = Experimento.cargarExperimento(selectedFile.getAbsolutePath());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Error loading the experiment: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private class NewExperimentListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Show a dialog to input the experiment details
            String experimentName = JOptionPane.showInputDialog(frame, "Enter the experiment name:", "New Experiment", JOptionPane.QUESTION_MESSAGE);
            if (experimentName != null) {
                // Create a new experiment
                // Assuming the Experimento constructor takes a name and initializes other fields with default values
                currentExperiment = new Experimento(experimentName);
            }
        }
    }
    private class CreatePopulationListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Show a dialog to input the population details
            String populationName = JOptionPane.showInputDialog(frame, "Enter the population name:", "New Population", JOptionPane.QUESTION_MESSAGE);
            if (populationName != null) {
                // Create a new population
                // Assuming the Poblacion constructor takes a name, a start date, an initial quantity of bacteria, and a PlatoCultivo object
                Poblacion newPopulation = new Poblacion(populationName, new Date(), 0,platoCultivo);
                newPopulation.inicializarPoblacion();
                // Add the new population to the current experiment
                if (currentExperiment != null) {
                    currentExperiment.addPoblacion(newPopulation);
                }
            }
        }
    }
    private class VisualizePopulationsListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (currentExperiment != null) {
                // Ask the user how they want to sort the populations
                String[] options = {"Nombre", "Fecha", "Cantidad de bacterias"};
                int response = JOptionPane.showOptionDialog(frame, "¿Cómo desea ordenar las poblaciones?", "Ordenar poblaciones", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

                // Sort the populations based on the user's choice
                switch (response) {
                    case 0: // Nombre
                        currentExperiment.ordenarPoblacionesPorNombre();
                        break;
                    case 1: // Fecha
                        currentExperiment.ordenarPoblacionesPorFecha();
                        break;
                    case 2: // Cantidad de bacterias
                        currentExperiment.ordenarPoblacionesPorCantidad();
                        break;
                }

                // Display the names of all bacteria populations in the current experiment
                StringBuilder populations = new StringBuilder();
                for (Bacteria bacteria : currentExperiment.getPoblacionesBacterias()) {
                    populations.append(bacteria.getNombre()).append("\n");
                }
                JOptionPane.showMessageDialog(frame, populations.toString(), "Poblaciones de bacterias", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
    private class DeletePopulationListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Show a dialog to input the population name to delete
            String populationName = JOptionPane.showInputDialog(frame, "Enter the name of the population to delete:", "Delete Population", JOptionPane.QUESTION_MESSAGE);
            if (populationName != null) {
                // Find the population with the entered name and delete it
                Bacteria populationToDelete = null;
                for (Bacteria bacteria : currentExperiment.getPoblacionesBacterias()) {
                    if (bacteria.getNombre().equals(populationName)) {
                        populationToDelete = bacteria;
                        break;
                    }
                }
                if (populationToDelete != null) {
                    currentExperiment.removePoblacion(populationToDelete);
                    JOptionPane.showMessageDialog(frame, "Population deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(frame, "No population found with the entered name.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private class DetailsListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Show a dialog to input the population name to view details
            String populationName = JOptionPane.showInputDialog(frame, "Enter the name of the population to view details:", "Population Details", JOptionPane.QUESTION_MESSAGE);
            if (populationName != null) {
                // Find the population with the entered name and display its details
                Bacteria populationToView = null;
                for (Bacteria bacteria : currentExperiment.getPoblacionesBacterias()) {
                    if (bacteria.getNombre().equals(populationName)) {
                        populationToView = bacteria;
                        break;
                    }
                }
                if (populationToView != null) {
                    String details = "Name: " + populationToView.getNombre() + "\n" +
                            "Date: " + populationToView.getFecha() + "\n" +
                            "Quantity: " + populationToView.getCantidad();
                    JOptionPane.showMessageDialog(frame, details, "Population Details", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(frame, "No population found with the entered name.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private class SimulateListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Show a dialog to input the population name to simulate
            String populationName = JOptionPane.showInputDialog(frame, "Enter the name of the population to simulate:", "Simulate Population", JOptionPane.QUESTION_MESSAGE);
            if (populationName != null) {
                // Find the population with the entered name and simulate it
                Bacteria populationToSimulate = null;
                for (Bacteria bacteria : currentExperiment.getPoblacionesBacterias()) {
                    if (bacteria.getNombre().equals(populationName)) {
                        populationToSimulate = bacteria;
                        break;
                    }
                }
                if (populationToSimulate != null) {
                    // Assuming that the Bacteria class has a simulate method
                    populationToSimulate.simulateDailyBehavior();
                    JOptionPane.showMessageDialog(frame, "Simulation completed successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(frame, "No population found with the entered name.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private class SaveListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (currentExperiment != null) {
                // Show a dialog to input the file path to save the experiment
                String filePath = JOptionPane.showInputDialog(frame, "Enter the file path to save the experiment:", "Save Experiment", JOptionPane.QUESTION_MESSAGE);
                if (filePath != null) {
                    // Save the current experiment to the entered file path
                    currentExperiment.guardarExperimento(filePath);
                    JOptionPane.showMessageDialog(frame, "Experiment saved successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(frame, "No current experiment to save.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class SaveAsListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (currentExperiment != null) {
                // Show a dialog to input the new file path to save the experiment
                String newFilePath = JOptionPane.showInputDialog(frame, "Enter the new file path to save the experiment:", "Save Experiment As", JOptionPane.QUESTION_MESSAGE);
                if (newFilePath != null) {
                    // Save the current experiment to the entered new file path
                    currentExperiment.guardarExperimento(newFilePath);
                    JOptionPane.showMessageDialog(frame, "Experiment saved successfully to the new file.", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(frame, "No current experiment to save.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Main();
            }
        });
    }
}