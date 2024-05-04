import Gestion_experimentos.Experimento;
import Gestion_experimentos.Bacteria;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        Date fechaInicio = new Date(); // fecha actual
        Date fechaFin = new Date(); // fecha actual
        List<Bacteria> bacterias = new ArrayList<>();
        bacterias.add(new Bacteria("E. coli", 1000));
        Experimento experimento = new Experimento(fechaInicio, fechaFin, bacterias);
        experimento.guardarExperimento("ruta/al/archivo.txt");
    }
}