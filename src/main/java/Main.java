import Gestion_experimentos.Experimento;
import Gestion_experimentos.Bacteria;
import Gestion_poblaciones_bacterias.PoblacionBacterias;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        // Código existente para crear y guardar un experimento
        Date fechaInicioExp = new Date(); // fecha actual
        Date fechaFinExp = new Date(); // fecha actual
        List<Bacteria> bacterias = new ArrayList<>();
        bacterias.add(new Bacteria("E. coli", 1000));
        Experimento experimento = new Experimento(fechaInicioExp, fechaFinExp, bacterias);
        experimento.guardarExperimento("ruta/al/archivo_experimento.txt");

        // Nuevo código para crear y guardar una población de bacterias
        Date fechaInicioPob = new Date(); // fecha actual
        Date fechaFinPob = new Date(); // fecha actual
        PoblacionBacterias poblacion = new PoblacionBacterias("Poblacion1", fechaInicioPob, fechaFinPob, 1000, 37.0, "Luz natural", 1.5);
        poblacion.guardarPoblacion("ruta/al/archivo_poblacion.txt");
    }
}