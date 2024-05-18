package Gestion_experimentos;

import java.io.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class Experimento implements Serializable {
    private String nombre;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private List<Bacteria> poblacionesBacterias;
    private int patronAlimentacion;
    private int duracion; // New field for variable duration
    private int[][][] bacteriaStats; // New field for storing daily bacteria stats
    private int[][][] foodStats; // New field for storing daily food stats

    public Experimento(String nombre, LocalDate fechaInicio, LocalDate fechaFin, List<Bacteria> poblacionesBacterias, int patronAlimentacion, int duracion) {
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.poblacionesBacterias = poblacionesBacterias;
        this.patronAlimentacion = patronAlimentacion;
        this.duracion = duracion;
        this.bacteriaStats = new int[duracion][20][20]; // Initialize the 3D arrays
        this.foodStats = new int[duracion][20][20];
    }
    public long getDuracion() {
        return ChronoUnit.DAYS.between(fechaInicio, fechaFin);
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
    public void setDuracion(int duracion) {
        this.duracion = Duration.ofDays(duracion);
    }

    public int getPatronAlimentacion() {
        return patronAlimentacion;
    }

    public void setPatronAlimentacion(int patronAlimentacion) {
        this.patronAlimentacion = patronAlimentacion;
    }

    public List<Bacteria> getPoblacionesBacterias() {
        return poblacionesBacterias;
    }

    public void setPoblacionesBacterias(List<Bacteria> poblacionesBacterias) {
        this.poblacionesBacterias = poblacionesBacterias;
    }

    public void addPoblacion(Bacteria bacteria) {
        this.poblacionesBacterias.add(bacteria);
    }

    public void removePoblacion(Bacteria bacteria) {
        this.poblacionesBacterias.remove(bacteria);
    }


    // New methods for sorting
    public void ordenarPoblacionesPorFecha() {
        Collections.sort(this.poblacionesBacterias, Comparator.comparing(Bacteria::getFecha));
    }

    public void ordenarPoblacionesPorNombre() {
        Collections.sort(this.poblacionesBacterias, Comparator.comparing(Bacteria::getNombre));
    }

    public void ordenarPoblacionesPorCantidad() {
        Collections.sort(this.poblacionesBacterias, Comparator.comparing(Bacteria::getCantidad));
    }

    // New method for running the Monte Carlo simulation
    public void simularExperimento() {
        // Implementation of the Monte Carlo simulation...
    }
    public static Experimento cargarExperimento(String filePath) {
        Experimento e = null;
        try {
            FileInputStream fileIn = new FileInputStream(filePath);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            e = (Experimento) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            return null;
        } catch (ClassNotFoundException c) {
            System.out.println("Experimento class not found");
            c.printStackTrace();
            return null;
        }
        return e;
    }
    public void guardarExperimento(String filePath) {
        try {
            FileOutputStream fileOut = new FileOutputStream(filePath);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
    public void addPoblacion(Poblacion poblacion) {
        this.poblacionesBacterias.addAll(poblacion.getBacterias());
    }
}