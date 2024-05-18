package SimulacionMontecarlo;

package Simulacion;

import Gestion_experimentos.Experimento;

public class Simulacion {
    private PlatoCultivo platoCultivo;
    private Experimento experimento;
    private int diaActual;

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
        // Implement logic to update statistics
    }
}