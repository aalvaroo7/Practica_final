package Aplicacion;
import Gestion_experimentos.Experimento;
import Gestion_experimentos.Bacteria;

import java.util.Date;
import java.util.List;

public class Aplicacion {
    private Experimento experimentoActual;

    public Aplicacion() {
        this.experimentoActual = null;
    }

    public void abrirExperimento(String filePath) {
        this.experimentoActual = Experimento.cargarExperimento(filePath);
    }

    public void crearExperimento(String nombre, Date fechaInicio, int duracion, int patronAlimentacion, List<Bacteria> poblacionesBacterias) {
        this.experimentoActual = new Experimento(nombre, fechaInicio, duracion, patronAlimentacion, poblacionesBacterias);
    }

    public void crearPoblacion(Bacteria bacteria) {
        if (this.experimentoActual != null) {
            this.experimentoActual.addPoblacion(bacteria);
        }
    }

    public List<Bacteria> visualizarPoblaciones() {
        if (this.experimentoActual != null) {
            return this.experimentoActual.getPoblacionesBacterias();
        }
        return null;
    }

    public void borrarPoblacion(Bacteria bacteria) {
        if (this.experimentoActual != null) {
            this.experimentoActual.removePoblacion(bacteria);
        }
    }

    public Bacteria verDetallesPoblacion(String nombre) {
        if (this.experimentoActual != null) {
            for (Bacteria bacteria : this.experimentoActual.getPoblacionesBacterias()) {
                if (bacteria.getNombre().equals(nombre)) {
                    return bacteria;
                }
            }
        }
        return null;
    }

    public void simularPoblacion() {
        if (this.experimentoActual != null) {
            this.experimentoActual.iniciarExperimento();
        }
    }

    public void guardarExperimento(String filePath) {
        if (this.experimentoActual != null) {
            this.experimentoActual.guardarExperimento(filePath);
        }
    }
}