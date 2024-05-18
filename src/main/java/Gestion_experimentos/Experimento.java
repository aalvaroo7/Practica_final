package Simulacion;

import Gestion_experimentos.Bacteria;

import java.util.Date;
import java.util.List;

public class Experimento {
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

    // ... other methods ...
}