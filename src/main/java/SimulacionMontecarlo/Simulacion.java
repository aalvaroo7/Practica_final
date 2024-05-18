package SimulacionMontecarlo;

import Gestion_experimentos.Bacteria;
import Gestion_experimentos.Experimento;
import Gestion_experimentos.PlatoCultivo;

import java.util.Random;

public class Simulacion {
    private PlatoCultivo platoCultivo;
    private Experimento experimento;
    private int diaActual;
    private int totalBacteria;
    private int totalFood;
    Random rand = new Random();

    public Simulacion(PlatoCultivo platoCultivo, Experimento experimento) {
        this.platoCultivo = platoCultivo;
        this.experimento = experimento;
        this.diaActual = 0;
    }

    public void simularDia() {
        repartirComida();
        simularComportamientoBacterias();
        actualizarEstadisticas();
        diaActual++;
    }

    private void repartirComida() {
        platoCultivo.repartirComidaDiaria(experimento.getPatronAlimentacion());
    }

    private void simularComportamientoBacterias() {
        platoCultivo.simularDiaCompleto(experimento.getPatronAlimentacion());
    }

    private void actualizarEstadisticas() {
        totalBacteria = platoCultivo.getTotalBacteria();
        totalFood = platoCultivo.getTotalFood();
    }

    public void iniciarSimulacion() {
        Random rand = new Random();

        int ancho = platoCultivo.getAncho();
        int alto = platoCultivo.getAlto();

        int x = rand.nextInt(ancho);
        int y = rand.nextInt(alto);
        int maxX = rand.nextInt(ancho);
        int maxY = rand.nextInt(alto);

        int[][] foodGrid = new int[ancho][alto];
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                foodGrid[i][j] = rand.nextInt(100); // Generate random food amount between 0 and 99
            }
        }

        Bacteria[][] bacteriaGrid = new Bacteria[ancho][alto];
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                if (rand.nextBoolean()) { // 50% chance to create a bacteria at this position
                    bacteriaGrid[i][j] = new Bacteria(i, j, maxX, maxY, foodGrid, bacteriaGrid);
                }
            }
        }
    }
}