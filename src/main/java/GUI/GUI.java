package GUI;

import Gestion_experimentos.Bacteria;
import Gestion_experimentos.Experimento;
import Gestion_experimentos.PlatoCultivo;
import Gestion_experimentos.Poblacion;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {
    private JFrame frame;
    private JButton openButton, newExperimentButton, createPopulationButton, visualizePopulationsButton, deletePopulationButton, detailsButton, simulateButton, saveButton, saveAsButton;
    private Experimento currentExperiment;

    public GUI() {
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
            // Logic for OpenFileListener
        }
    }

    private class NewExperimentListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Logic for NewExperimentListener
        }
    }

    private class CreatePopulationListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Logic for CreatePopulationListener
        }
    }

    private class VisualizePopulationsListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Logic for VisualizePopulationsListener
        }
    }

    private class DeletePopulationListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Logic for DeletePopulationListener
        }
    }

    private class DetailsListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Logic for DetailsListener
        }
    }

    private class SimulateListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Logic for SimulateListener
        }
    }

    private class SaveListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Logic for SaveListener
        }
    }

    private class SaveAsListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Logic for SaveAsListener
        }
    }
}