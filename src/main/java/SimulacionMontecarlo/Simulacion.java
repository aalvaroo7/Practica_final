package SimulacionMontecarlo;

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
}