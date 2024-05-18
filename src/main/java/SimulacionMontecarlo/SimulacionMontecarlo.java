package SimulacionMontecarlo;

import Gestion_experimentos.Bacteria;
import Gestion_experimentos.Experimento;
import Gestion_experimentos.PlatoCultivo;
import java.util.Random;

public class SimulacionMontecarlo {
    private PlatoCultivo platoCultivo;
    private Experimento experimento;
    private int diaActual;
    private int totalBacteria;
    private int totalFood;
    Random rand = new Random();

    public SimulacionMontecarlo(PlatoCultivo platoCultivo, Experimento experimento) {
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

    private int generarNumeroAleatorio(int limit) {
        return rand.nextInt(limit);
    }

    private void calcularMovimientoSegunTablaProbabilidades(Bacteria bacteria) {
        int randomNumber = generarNumeroAleatorio(100);

        if (randomNumber < 20) {
            bacteria.move(0); // move up
        } else if (randomNumber < 50) {
            bacteria.move(1); // move right
        } else if (randomNumber < 75) {
            bacteria.move(2); // move down
        } else {
            bacteria.move(3); // move left
        }
    }
    public void iniciarSimulacion() {
        Random rand = new Random();

        int ancho = platoCultivo.getAncho();
        int alto = platoCultivo.getAlto();

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