package Gestion_experimentos;

import java.io.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class Experimento  {
    private String nombre;
    private Date fechaInicio;
    private int duracion;
    private int patronAlimentacion;
    private List<Bacteria> poblacionesBacterias;
    private PlatoCultivo platoCultivo;

    public Experimento(String nombre, Date fechaInicio, int duracion, int patronAlimentacion, List<Bacteria> poblacionesBacterias) {
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.duracion = duracion;
        this.patronAlimentacion = patronAlimentacion;
        this.poblacionesBacterias = poblacionesBacterias;
        this.platoCultivo = new PlatoCultivo(100, 100); // Assuming a 100x100 petri dish
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

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
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

    public void iniciarExperimento() {
        // Initialize the petri dish with bacteria
        platoCultivo.inicializarPlatoConBacterias(poblacionesBacterias);

        // Run the simulation for the specified number of days
        for (int i = 0; i < duracion; i++) {
            platoCultivo.simularDiaCompleto(patronAlimentacion);
        }

        // Display the results
        platoCultivo.mostrarResultados();
    }
    public void addPoblacion(Bacteria bacteria) {
        this.poblacionesBacterias.add(bacteria);
    }

    public void removePoblacion(Bacteria bacteria) {
        this.poblacionesBacterias.remove(bacteria);
    }

    public void ordenarPoblacionesPorNombre() {
        Collections.sort(this.poblacionesBacterias, Comparator.comparing(Bacteria::getNombre));
    }

    public void ordenarPoblacionesPorFecha() {
        // Assuming Bacteria class has a getFecha method
        Collections.sort(this.poblacionesBacterias, Comparator.comparing(Bacteria::getFecha));
    }

    public void ordenarPoblacionesPorCantidad() {
        // Assuming Bacteria class has a getCantidad method
        Collections.sort(this.poblacionesBacterias, Comparator.comparing(Bacteria::getCantidad));
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

}