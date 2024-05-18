package Gestion_poblaciones_bacterias;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getNumBacteriasIniciales() {
        return numBacteriasIniciales;
    }

    public void setNumBacteriasIniciales(int numBacteriasIniciales) {
        this.numBacteriasIniciales = numBacteriasIniciales;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    public String getCondicionesLuminosidad() {
        return condicionesLuminosidad;
    }

    public void setCondicionesLuminosidad(String condicionesLuminosidad) {
        this.condicionesLuminosidad = condicionesLuminosidad;
    }

    public double getDosisComida() {
        return dosisComida;
    }

    public void setDosisComida(double dosisComida) {
        this.dosisComida = dosisComida;
    }
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
    public void mostrarInfoPoblacion() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("Nombre: " + nombre);
        System.out.println("Fecha de inicio: " + sdf.format(fechaInicio));
        System.out.println("Fecha de fin: " + sdf.format(fechaFin));
        System.out.println("Número de bacterias iniciales: " + numBacteriasIniciales);
        System.out.println("Temperatura: " + temperatura);
        System.out.println("Condiciones de luminosidad: " + condicionesLuminosidad);
        System.out.println("Dosis de comida: " + dosisComida);
    }

    public static void sortByStartDate(List<PoblacionBacterias> poblaciones) {
        Collections.sort(poblaciones, new Comparator<PoblacionBacterias>() {
            @Override
            public int compare(PoblacionBacterias p1, PoblacionBacterias p2) {
                return p1.getFechaInicio().compareTo(p2.getFechaInicio());
            }
        });
    }

    public static void sortByName(List<PoblacionBacterias> poblaciones) {
        Collections.sort(poblaciones, new Comparator<PoblacionBacterias>() {
            @Override
            public int compare(PoblacionBacterias p1, PoblacionBacterias p2) {
                return p1.getNombre().compareTo(p2.getNombre());
            }
        });
    }

    public static void sortByNumBacterias(List<PoblacionBacterias> poblaciones) {
        Collections.sort(poblaciones, new Comparator<PoblacionBacterias>() {
            @Override
            public int compare(PoblacionBacterias p1, PoblacionBacterias p2) {
                return Integer.compare(p1.getNumBacteriasIniciales(), p2.getNumBacteriasIniciales());
            }
        });
    }
    public static PoblacionBacterias abrirPoblacion(String rutaArchivo, String nombrePoblacion) {
        try (BufferedReader in = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = in.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes[0].equals(nombrePoblacion)) {
                    PoblacionBacterias poblacion = new PoblacionBacterias(partes[0], new SimpleDateFormat("yyyy-MM-dd").parse(partes[1]), new SimpleDateFormat("yyyy-MM-dd").parse(partes[2]), Integer.parseInt(partes[3]), Double.parseDouble(partes[4]), partes[5], Double.parseDouble(partes[6]));
                    return poblacion;
                }
            }
        } catch (IOException | ParseException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
