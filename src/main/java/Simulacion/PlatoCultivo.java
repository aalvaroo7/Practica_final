package Simulacion;
import Gestion_experimentos.Bacteria;

import java.util.ArrayList;
import java.util.List;

public class PlatoCultivo {
    private int ancho;
    private int alto;
    private Celda[][] matrizCeldas;

    public PlatoCultivo(int ancho, int alto) {
        this.ancho = ancho;
        this.alto = alto;
        this.matrizCeldas = new Celda[ancho][alto];
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

    public void simularDiaCompleto() {
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                Celda celda = matrizCeldas[i][j];
                celda.repartirComida();
                List<Bacteria> bacterias = celda.getBacterias();
                for (Bacteria bacteria : bacterias) {
                    bacteria.simulateDailyBehavior();
                }
            }
        }
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
}