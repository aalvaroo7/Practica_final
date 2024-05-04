package Gestion_poblaciones_bacterias;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PoblacionBacterias {
    private String nombre;
    private Date fechaInicio;
    private Date fechaFin;
    private int numBacteriasIniciales;
    private double temperatura;
    private String condicionesLuminosidad;
    private double dosisComida;

    public PoblacionBacterias(String nombre, Date fechaInicio, Date fechaFin, int numBacteriasIniciales, double temperatura, String condicionesLuminosidad, double dosisComida) {
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.numBacteriasIniciales = numBacteriasIniciales;
        this.temperatura = temperatura;
        this.condicionesLuminosidad = condicionesLuminosidad;
        this.dosisComida = dosisComida;
    }

    // getters y setters
    // ...

    public void guardarPoblacion(String rutaArchivo) {
        try (PrintWriter out = new PrintWriter(new FileWriter(rutaArchivo, true))) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            out.println(nombre + "," + sdf.format(fechaInicio) + "," + sdf.format(fechaFin) + "," + numBacteriasIniciales + "," + temperatura + "," + condicionesLuminosidad + "," + dosisComida);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void eliminarPoblacion(String rutaArchivo, String nombrePoblacion) {
        try {
            File inputFile = new File(rutaArchivo);
            File tempFile = new File("tempFile.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String currentLine;

            while((currentLine = reader.readLine()) != null) {
                if(currentLine.split(",")[0].equals(nombrePoblacion)) continue;
                writer.write(currentLine + System.getProperty("line.separator"));
            }
            writer.close();
            reader.close();
            boolean successful = tempFile.renameTo(inputFile);
            System.out.println(successful ? "Eliminación exitosa." : "Eliminación fallida.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void visualizarPoblaciones(String rutaArchivo) {
        try (BufferedReader in = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = in.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
