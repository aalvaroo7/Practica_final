package GUI;

import Gestion_experimentos.Bacteria;
import Gestion_experimentos.Experimento;
import Gestion_poblaciones_bacterias.PoblacionBacterias;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InterfazUsuario extends JFrame {
    private JButton abrirArchivoButton;
    private JButton crearExperimentoButton;
    private JButton crearPoblacionButton;
    private JButton visualizarPoblacionesButton;
    private JButton borrarPoblacionButton;
    private JButton verInfoPoblacionButton;
    private JButton guardarButton;
    private JButton guardarComoButton;
    private JButton mostrarExperimentosButton; // Declare the button
    private Experimento experimentoActual;
    private String rutaArchivo; // Añade esta línea
    private JButton sortBacteriaButton;
    private JTextArea textArea;
    private JScrollPane scrollPane;

    public InterfazUsuario() {
        setTitle("Interfaz de Usuario");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        // Crear un CustomOutputStream y redirigir la salida estándar a él
        CustomOutputStream customOut = new CustomOutputStream(textArea);
        PrintStream printStream = new PrintStream(customOut);
        System.setOut(printStream);
        System.setErr(printStream);

        abrirArchivoButton = new JButton("Abrir Archivo");
        crearExperimentoButton = new JButton("Crear Experimento");
        crearPoblacionButton = new JButton("Crear Población");
        visualizarPoblacionesButton = new JButton("Visualizar Poblaciones");
        borrarPoblacionButton = new JButton("Borrar Población");
        verInfoPoblacionButton = new JButton("Ver Info Población");
        guardarButton = new JButton("Guardar");
        guardarComoButton = new JButton("Guardar Como");
        mostrarExperimentosButton = new JButton("Mostrar Experimentos"); // Initialize the button
        textArea = new JTextArea(20, 50);
        textArea.setEditable(false);
        scrollPane = new JScrollPane(textArea);
        add(scrollPane);
        sortBacteriaButton = new JButton("Sort Bacteria");

        sortBacteriaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (experimentoActual != null) {
                    experimentoActual.sortBacteria();
                }
            }
        });

        add(sortBacteriaButton);
        abrirArchivoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crear un JFileChooser
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);

                // Si el usuario selecciona un archivo
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    // Obtener la ruta al archivo seleccionado
                    rutaArchivo = fileChooser.getSelectedFile().getPath(); // Modifica esta línea

                    // Abrir el experimento
                    Experimento experimentoCargado = Experimento.abrirExperimento(rutaArchivo);

                    // Ahora puedes usar experimentoCargado para mostrar la información del experimento o hacer otras operaciones
                }
            }
        });

        crearExperimentoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Pedir al usuario los detalles del experimento
                String nombre = JOptionPane.showInputDialog("Introduce el nombre del experimento");
                String strTemperatura = JOptionPane.showInputDialog("Introduce la temperatura del experimento");
                double temperatura = Double.parseDouble(strTemperatura);
                String condicionesLuminosidad = JOptionPane.showInputDialog("Introduce las condiciones de luminosidad del experimento");
                String strDosisComida = JOptionPane.showInputDialog("Introduce la dosis de comida del experimento");
                double dosisComida = Double.parseDouble(strDosisComida);

                // Crear el experimento
                Date fechaInicioExp = new Date(); // fecha actual
                Date fechaFinExp = new Date(); // fecha actual
                List<Bacteria> bacterias = new ArrayList<>();
                bacterias.add(new Bacteria("E. coli", 1000));
                int duracion = 30; // duration of the experiment in days
                experimentoActual = new Experimento(nombre, fechaInicioExp, fechaFinExp, bacterias, dosisComida, dosisComida, duracion, dosisComida);

                // Guardar el experimento
                experimentoActual.guardarExperimento("ruta/al/archivo_experimento.txt");
            }
        });

        mostrarExperimentosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implementar lógica para mostrar experimentos
                Experimento.abrirExperimento("ruta/al/archivo_experimento.txt").mostrarInfoExperimento();
            }
        });

        add(mostrarExperimentosButton);

        visualizarPoblacionesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implementar lógica para visualizar poblaciones
                PoblacionBacterias.visualizarPoblaciones("ruta/al/archivo_poblacion.txt");
            }
        });

        borrarPoblacionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Pedir al usuario el nombre de la población a borrar
                String nombrePoblacion = JOptionPane.showInputDialog("Introduce el nombre de la población a borrar");

                // Borrar la población
                PoblacionBacterias.eliminarPoblacion("ruta/al/archivo_poblacion.txt", nombrePoblacion);
            }
        });

        verInfoPoblacionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Pedir al usuario el nombre de la población a visualizar
                String nombrePoblacion = JOptionPane.showInputDialog("Introduce el nombre de la población a visualizar");

                // Buscar la población en el archivo y mostrar su información
                try (BufferedReader in = new BufferedReader(new FileReader("ruta/al/archivo_poblacion.txt"))) {
                    String linea;
                    while ((linea = in.readLine()) != null) {
                        String[] partes = linea.split(",");
                        if (partes[0].equals(nombrePoblacion)) {
                            PoblacionBacterias poblacion = new PoblacionBacterias(partes[0], new SimpleDateFormat("yyyy-MM-dd").parse(partes[1]), new SimpleDateFormat("yyyy-MM-dd").parse(partes[2]), Integer.parseInt(partes[3]), Double.parseDouble(partes[4]), partes[5], Double.parseDouble(partes[6]));
                            poblacion.mostrarInfoPoblacion();
                            break;
                        }
                    }
                } catch (IOException | ParseException ex) {
                    ex.printStackTrace();
                }
            }
        });

        guardarComoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crear un JFileChooser
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showSaveDialog(null);

                // Si el usuario selecciona un archivo
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    // Obtener la ruta al archivo seleccionado
                    rutaArchivo = fileChooser.getSelectedFile().getPath(); // Modifica esta línea

                    // Guardar el experimento
                    experimentoActual.guardarExperimento(rutaArchivo);
                }
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
    public JTextArea getTextArea() {
        return textArea;
    }
    public static void main(String[] args) {
        InterfazUsuario interfazUsuario = new InterfazUsuario();
        interfazUsuario.setVisible(true);
    }
}