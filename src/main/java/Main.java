import GUI.CustomOutputStream;
import GUI.InterfazUsuario;
import Gestion_experimentos.Experimento;
import Gestion_experimentos.Bacteria;
import Gestion_poblaciones_bacterias.PoblacionBacterias;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        InterfazUsuario interfazUsuario = new InterfazUsuario();
        PrintStream printStream = new PrintStream(new CustomOutputStream(interfazUsuario.getTextArea()));
        System.setOut(printStream);
        System.setErr(printStream);
        // ...
        interfazUsuario.setVisible(true);
        // Código existente para crear y guardar un experimento
        Date fechaInicioExp = new Date(); // fecha actual
        Date fechaFinExp = new Date(); // fecha actual
        List<Bacteria> bacterias = new ArrayList<>();
        bacterias.add(new Bacteria("E. coli", 1000));
        bacterias.add(new Bacteria("Salmonella", 500)); // Added for testing
        bacterias.add(new Bacteria("Staphylococcus", 1500)); // Added for testing
        int duracion = 30; // duration of the experiment in days
        double dosisComidaMicrogramos = 1500.0; // food dose in micrograms
        Experimento experimento = new Experimento(fechaInicioExp, fechaFinExp, bacterias, 1.0, 2.0, duracion, dosisComidaMicrogramos);
        experimento.guardarExperimento("ruta/al/archivo_experimento.txt");

        // Print the list of bacteria before sorting
        System.out.println("Before sorting:");
        for (Bacteria bacteria : experimento.getBacterias()) {
            System.out.println(bacteria.getNombre() + ": " + bacteria.getCantidad());
        }

        // Sort the bacteria
        experimento.sortBacteria();

        // Print the list of bacteria after sorting
        System.out.println("After sorting:");
        for (Bacteria bacteria : experimento.getBacterias()) {
            System.out.println(bacteria.getNombre() + ": " + bacteria.getCantidad());
        }

        // Calcular la dosis diaria de comida para cada día del experimento
        for (int dia = 0; dia < 30; dia++) {
            double dosisDiaria = experimento.calcularDosisDiaria(dia, Experimento.FoodSupplyPattern.CONSTANT); // Added FoodSupplyPattern.CONSTANT as the second parameter
            System.out.println("Dosis de comida para el día " + dia + ": " + dosisDiaria);
        }

        // Nuevo código para crear y guardar una población de bacterias
        Date fechaInicioPob = new Date(); // fecha actual
        Date fechaFinPob = new Date(); // fecha actual
        PoblacionBacterias poblacion = new PoblacionBacterias("Poblacion1", fechaInicioPob, fechaFinPob, 1000, 37.0, "Luz natural", 1.5);
        poblacion.guardarPoblacion("ruta/al/archivo_poblacion.txt");

    }
}