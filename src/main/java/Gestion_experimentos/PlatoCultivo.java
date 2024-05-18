package Gestion_experimentos;

import java.util.List;

public class PlatoCultivo {
    private int ancho;
    private int alto;
    private Celda[][] matrizCeldas;
    private int[][][] bacteriaStats;
    private int[][][] foodStats;
    private int currentDay;
    private int duracion;

    public PlatoCultivo(int ancho, int alto) {
        this.ancho = ancho;
        this.alto = alto;
        this.duracion = 0; // Initialize duration to 0
        this.matrizCeldas = new Celda[ancho][alto];
        this.bacteriaStats = new int[duracion][ancho][alto];
        this.foodStats = new int[duracion][ancho][alto];
        this.currentDay = 0;
        inicializarPlato();
    }

    public void addBacteria(Bacteria bacteria) {
        // Assuming that the bacteria knows its own position
        int x = bacteria.getX();
        int y = bacteria.getY();
        this.matrizCeldas[x][y].addBacteria(bacteria);
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
        while (hayBacteriasVivas()) {
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
            actualizarDuracion(); // Update the duration of the experiment
        }
    }
    public boolean hayBacteriasVivas() {
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                Celda celda = matrizCeldas[i][j];
                if (!celda.getBacterias().isEmpty()) {
                    return true;
                }
            }
        }
        return false;
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
    public void actualizarDuracion() {
        if (hayBacteriasVivas()) {
            this.duracion++;
            // Resize the bacteriaStats and foodStats arrays
            this.bacteriaStats = new int[duracion][ancho][alto];
            this.foodStats = new int[duracion][ancho][alto];
        }
    }
}