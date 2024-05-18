package SimulacionMontecarlo;

import Gestion_experimentos.Experimento;
import java.util.Random;

public class SimulacionMontecarlo {
    private PlatoCultivo platoCultivo;
    private Experimento experimento;
    private int diaActual;
    private int totalBacteria;
    private int totalFood;
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
        Random random = new Random();
        return random.nextInt(limit);
    }

    private void calcularMovimientoSegunTablaProbabilidades() {
        int randomNumber = generarNumeroAleatorio(100);

        if (randomNumber < 20) {
            // move up
        } else if (randomNumber < 50) {
            // move right
        } else if (randomNumber < 75) {
            // move down
        } else {
            // move left
        }
    }
}