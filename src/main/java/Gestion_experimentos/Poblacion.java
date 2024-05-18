package Gestion_experimentos;

import java.util.Date;
import java.util.Random;

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
        int ancho = platoCultivo.getAncho();
        int alto = platoCultivo.getAlto();
        int[][] foodGrid = new int[ancho][alto]; // Assuming foodGrid is a 2D array
        Bacteria[][] bacteriaGrid = new Bacteria[ancho][alto]; // Assuming bacteriaGrid is a 2D array
        Random rand = new Random();

        for (int i = 0; i < cantidadInicialBacterias; i++) {
            int x = rand.nextInt(ancho); // Generate random x coordinate
            int y = rand.nextInt(alto); // Generate random y coordinate
            Bacteria bacteria = new Bacteria(x, y, ancho, alto, foodGrid, bacteriaGrid);
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