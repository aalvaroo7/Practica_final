package Simulacion;
import java.util.Date;
import java.util.List;

public class Poblacion {
    private String nombre;
    private Date fechaInicio;
    private int cantidadInicialBacterias;
    private PlatoCultivo platoCultivo;

    public Poblacion(String nombre, Date fechaInicio, int cantidadInicialBacterias, PlatoCultivo platoCultivo) {
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.cantidadInicialBacterias = cantidadInicialBacterias;
        this.platoCultivo = platoCultivo;
    }

    public void inicializarPoblacion() {
        // Initialize the petri dish with bacteria
        for (int i = 0; i < cantidadInicialBacterias; i++) {
            Bacteria bacteria = new Bacteria(/* parameters */);
            platoCultivo.addBacteria(bacteria);
        }
    }

    public int[][] obtenerEstadisticasDiarias() {
        return platoCultivo.obtenerEstadisticas();
    }

    public void visualizarSimulacion() {
        // Implement logic to visualize the simulation
    }

    // ... other methods ...
}