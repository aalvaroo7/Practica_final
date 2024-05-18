package GUI;

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
import java.util.Date;

public class InterfazUsuario extends JFrame {
    private JButton abrirArchivoButton;
    private JButton crearExperimentoButton;
    private JButton crearPoblacionButton;
    private JButton visualizarPoblacionesButton;
    private JButton borrarPoblacionButton;
    private JButton verInfoPoblacionButton;
    private JButton guardarButton;
    private JButton guardarComoButton;
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
                // Implementar lógica para crear experimento
                Date fechaInicioPob = new Date(); // fecha actual
                Date fechaFinPob = new Date(); // fecha actual
                String nombre = "Poblacion1"; // nombre de la población
                int numBacteriasIniciales = 1000; // número inicial de bacterias
                double temperatura = 37.0; // temperatura en grados Celsius
                String condicionesLuminosidad = "Luz natural"; // condiciones de luminosidad
                double dosisComida = 1.5; // dosis de comida

                PoblacionBacterias poblacion = new PoblacionBacterias(nombre, fechaInicioPob, fechaFinPob, numBacteriasIniciales, temperatura, condicionesLuminosidad, dosisComida);
                poblacion.guardarPoblacion("ruta/al/archivo_poblacion.txt");
            }
        });

        crearPoblacionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Pedir al usuario los detalles de la población
                String nombre = JOptionPane.showInputDialog("Introduce el nombre de la población");
                String strTemperatura = JOptionPane.showInputDialog("Introduce la temperatura de la población");
                double temperatura = Double.parseDouble(strTemperatura);
                String condicionesLuminosidad = JOptionPane.showInputDialog("Introduce las condiciones de luminosidad de la población");
                String strDosisComida = JOptionPane.showInputDialog("Introduce la dosis de comida de la población");
                double dosisComida = Double.parseDouble(strDosisComida);

                // Crear la población
                Date fechaInicioPob = new Date(); // fecha actual
                Date fechaFinPob = new Date(); // fecha actual
                int numBacteriasIniciales = 1000; // número inicial de bacterias

                PoblacionBacterias poblacion = new PoblacionBacterias(nombre, fechaInicioPob, fechaFinPob, numBacteriasIniciales, temperatura, condicionesLuminosidad, dosisComida);

                // Guardar la población
                poblacion.guardarPoblacion("ruta/al/archivo_poblacion.txt");
            }
        });

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
