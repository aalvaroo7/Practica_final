package Gestion_experimentos;

import java.util.List;

public class PlatoCultivo {
    private int ancho;
    private int alto;
    private Celda[][] matrizCeldas;
    private int[][][] bacteriaStats;
    private int[][][] foodStats;
    private int currentDay;
    private int duracion; // Added this line


    public PlatoCultivo(int ancho, int alto, int duracion) { // Modified this line
        this.ancho = ancho;
        this.alto = alto;
        this.duracion = duracion; // Added this line
        this.matrizCeldas = new Celda[ancho][alto];
        this.bacteriaStats = new int[duracion][ancho][alto];
        this.foodStats = new int[duracion][ancho][alto];
        this.currentDay = 0;
        inicializarPlato();
    }
    private void inicializarPlato() {
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                matrizCeldas[i][j] = new Celda(i, j, 0);
            }
        }
    }

    public void repartirComidaDiaria(int cantidadComida) {
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                matrizCeldas[i][j].addFood(cantidadComida);
            }
        }
    }

    public void simularDiaCompleto(int cantidadComida) {
        repartirComidaDiaria(cantidadComida);
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                Celda celda = matrizCeldas[i][j];
                celda.repartirComida();
                List<Bacteria> bacterias = celda.getBacterias();
                for (Bacteria bacteria : bacterias) {
                    bacteria.simulateDailyBehavior();
                    List<Bacteria> newBacterias = bacteria.reproduce();
                    for (Bacteria newBacteria : newBacterias) {
                        celda.addBacteria(newBacteria);
                    }
                }
                bacteriaStats[currentDay][i][j] = celda.getBacterias().size();
                foodStats[currentDay][i][j] = celda.getFoodAmount();
            }
        }
        currentDay++;
    }

    public int[][] obtenerEstadisticas() {
        int[][] estadisticas = new int[ancho][alto];
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                Celda celda = matrizCeldas[i][j];
                estadisticas[i][j] = celda.getBacterias().size();
            }
        }
        return estadisticas;
    }
    public void inicializarPlatoConBacterias(List<Bacteria> bacterias) {
        int index = 0;
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                if (index < bacterias.size()) {
                    matrizCeldas[i][j].addBacteria(bacterias.get(index));
                    index++;
                } else {
                    break;
                }
            }
        }
    }

    public void mostrarResultados() {
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                Celda celda = matrizCeldas[i][j];
                System.out.println("Cell at (" + i + ", " + j + ") has " + celda.getBacterias().size() + " bacteria.");
            }
        }
    }
    public void simularDiaCompletoConPatron(int patronAlimentacion) {
        repartirComidaDiaria(patronAlimentacion);
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                Celda celda = matrizCeldas[i][j];
                List<Bacteria> bacterias = celda.getBacterias();
                for (Bacteria bacteria : bacterias) {
                    bacteria.simulateDailyBehavior();
                }
            }
        }
    }
}