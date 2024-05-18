import Gestion_experimentos.Experimento;
import Gestion_experimentos.Poblacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
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
            // Mostrar un diálogo para seleccionar un archivo
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                try {
                    // Cargar el experimento desde el archivo seleccionado
                    currentExperiment = Experimento.loadFromFile(selectedFile);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame, "Error al cargar el experimento: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private class NewExperimentListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Mostrar un diálogo para obtener el nombre del nuevo experimento
            String name = JOptionPane.showInputDialog(frame, "Ingrese el nombre del nuevo experimento:");
            if (name != null && !name.isEmpty()) {
                // Crear un nuevo experimento con la fecha actual
                currentExperiment = new Experimento(name, new Date());
            }
        }
    }

    private class CreatePopulationListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Mostrar un diálogo para obtener los detalles de la nueva población
            String name = JOptionPane.showInputDialog(frame, "Ingrese el nombre de la población:");
            int initialBacteria = Integer.parseInt(JOptionPane.showInputDialog(frame, "Ingrese la cantidad inicial de bacterias:"));
            if (name != null && !name.isEmpty()) {
                // Crear una nueva población y añadirla al experimento actual
                Poblacion population = new Poblacion(name, new Date(), initialBacteria);
                currentExperiment.addPoblacion(population);
            }
        }
    }

    private class VisualizePopulationsListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Preguntar al usuario cómo desea ordenar las poblaciones
            String[] options = {"Nombre", "Fecha", "Cantidad de bacterias"};
            int choice = JOptionPane.showOptionDialog(frame, "Ordenar poblaciones por:", "Ordenar poblaciones",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

            // Ordenar las poblaciones según la opción seleccionada
            List<Population> populations = currentExperiment.getPopulations();
            switch (choice) {
                case 0: // Ordenar por nombre
                    populations.sort(Comparator.comparing(Population::getName));
                    break;
                case 1: // Ordenar por fecha
                    populations.sort(Comparator.comparing(Population::getStartDate));
                    break;
                case 2: // Ordenar por cantidad de bacterias
                    populations.sort(Comparator.comparingInt(Population::getInitialBacteria));
                    break;
            }

            // Mostrar las poblaciones ordenadas
            StringBuilder sb = new StringBuilder();
            for (Population population : populations) {
                sb.append(population.getName()).append("\n");
            }
            JOptionPane.showMessageDialog(frame, sb.toString(), "Poblaciones", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private class DeletePopulationListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Mostrar un diálogo para seleccionar la población a eliminar
            List<Population> populations = currentExperiment.getPopulations();
            String[] populationNames = new String[populations.size()];
            for (int i = 0; i < populations.size(); i++) {
                populationNames[i] = populations.get(i).getName();
            }
            String selectedName = (String) JOptionPane.showInputDialog(frame, "Selecciona la población a eliminar:", "Eliminar población",
                    JOptionPane.QUESTION_MESSAGE, null, populationNames, populationNames[0]);

            // Eliminar la población seleccionada del experimento actual
            if (selectedName != null) {
                Population populationToDelete = null;
                for (Population population : populations) {
                    if (population.getName().equals(selectedName)) {
                        populationToDelete = population;
                        break;
                    }
                }
                if (populationToDelete != null) {
                    currentExperiment.removePopulation(populationToDelete);
                }
            }
        }
    }

    private class DetailsListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Mostrar un diálogo para seleccionar la población cuyos detalles se quieren ver
            List<Population> populations = currentExperiment.getPopulations();
            String[] populationNames = new String[populations.size()];
            for (int i = 0; i < populations.size(); i++) {
                populationNames[i] = populations.get(i).getName();
            }
            String selectedName = (String) JOptionPane.showInputDialog(frame, "Selecciona la población:", "Ver detalles de población",
                    JOptionPane.QUESTION_MESSAGE, null, populationNames, populationNames[0]);

            // Mostrar los detalles de la población seleccionada
            if (selectedName != null) {
                Population selectedPopulation = null;
                for (Population population : populations) {
                    if (population.getName().equals(selectedName)) {
                        selectedPopulation = population;
                        break;
                    }
                }
                if (selectedPopulation != null) {
                    JOptionPane.showMessageDialog(frame, selectedPopulation.getDetails(), "Detalles de población",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }

    private class SimulateListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Mostrar un diálogo para seleccionar la población a simular
            List<Population> populations = currentExperiment.getPopulations();
            String[] populationNames = new String[populations.size()];
            for (int i = 0; i < populations.size(); i++) {
                populationNames[i] = populations.get(i).getName();
            }
            String selectedName = (String) JOptionPane.showInputDialog(frame, "Selecciona la población a simular:", "Simular población",
                    JOptionPane.QUESTION_MESSAGE, null, populationNames, populationNames[0]);

            // Realizar y visualizar la simulación de la población seleccionada
            if (selectedName != null) {
                Population selectedPopulation = null;
                for (Population population : populations) {
                    if (population.getName().equals(selectedName)) {
                        selectedPopulation = population;
                        break;
                    }
                }
                if (selectedPopulation != null) {
                    selectedPopulation.simulate();
                    selectedPopulation.visualizeSimulation();
                }
            }
        }
    }

    private class SaveListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                // Guardar el experimento actual en el archivo original
                currentExperiment.saveToFile(currentExperiment.getFile());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(frame, "Error al guardar el experimento: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class SaveAsListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Mostrar un diálogo para seleccionar la ubicación y el nombre del archivo
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showSaveDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                try {
                    // Guardar el experimento actual en el archivo seleccionado
                    currentExperiment.saveToFile(selectedFile);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame, "Error al guardar el experimento: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}