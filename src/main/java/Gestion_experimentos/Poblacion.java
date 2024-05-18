package Gestion_experimentos;

import java.util.Date;

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
        int[][] estadisticasDiarias = obtenerEstadisticasDiarias();
        for (int i = 0; i < estadisticasDiarias.length; i++) {
            for (int j = 0; j < estadisticasDiarias[i].length; j++) {
                System.out.print(estadisticasDiarias[i][j] + " ");
            }
            System.out.println();
        }
    }

    // ... other methods ...
}