import Gestion_experimentos.Experimento;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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
            // Código para abrir un archivo de experimento
        }
    }

    private class NewExperimentListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Código para crear un nuevo experimento
        }
    }

    private class CreatePopulationListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Código para crear una población de bacterias y añadirla al experimento actual
        }
    }

    private class VisualizePopulationsListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Código para visualizar los nombres de todas las poblaciones de bacterias del experimento actual
        }
    }

    private class DeletePopulationListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Código para borrar una población de bacterias del experimento actual
        }
    }

    private class DetailsListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Código para ver información detallada de una población de bacterias del experimento actual
        }
    }

    private class SimulateListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Código para realizar y visualizar la simulación correspondiente con una de las poblaciones de bacterias del experimento
        }
    }

    private class SaveListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Código para guardar el experimento actual
        }
    }

    private class SaveAsListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Código para guardar el experimento actual como un nuevo archivo
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