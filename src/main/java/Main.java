import GUI.InterfazUsuario;
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
        int duracion = 30; // duration of the experiment in days
        double dosisComidaMicrogramos = 1500.0; // food dose in micrograms
        Experimento experimento = new Experimento(fechaInicioExp, fechaFinExp, bacterias, 1.0, 2.0, duracion, dosisComidaMicrogramos);
        experimento.guardarExperimento("ruta/al/archivo_experimento.txt");

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

        // Crear la interfaz de usuario y hacerla visible
        InterfazUsuario interfazUsuario = new InterfazUsuario();
        interfazUsuario.setVisible(true);
    }
}