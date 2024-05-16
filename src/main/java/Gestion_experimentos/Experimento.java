package Gestion_experimentos;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
public class Experimento {
    private Date fechaInicio;
    private Date fechaFin;
    private List<Bacteria> bacterias;
    private double dosisComidaInicial;
    private double dosisComidaFinal;
    private int duracion; // new instance variable
    private double dosisComidaMicrogramos; // new instance variable

    public Experimento(Date fechaInicio, Date fechaFin, List<Bacteria> bacterias, double dosisComidaInicial, double dosisComidaFinal, int duracion, double dosisComidaMicrogramos) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.bacterias = bacterias;
        this.dosisComidaInicial = dosisComidaInicial;
        this.dosisComidaFinal = dosisComidaFinal;
        this.duracion = duracion; // initialize new instance variable
        this.dosisComidaMicrogramos = dosisComidaMicrogramos; // initialize new instance variable
    }

    public double calcularDosisDiaria(int dia, FoodSupplyPattern pattern) {
        switch (pattern) {
            case CONSTANT:
                return dosisComidaMicrogramos;
            case LINEAR_INCREASE:
                double incrementoDiario = (dosisComidaMicrogramos - dosisComidaInicial) / duracion;
                return dosisComidaInicial + incrementoDiario * dia;
            case ALTERNATING:
                if (dia % 2 == 0) {
                    return dosisComidaMicrogramos;
                } else {
                    return 0;
                }
            default:
                throw new IllegalArgumentException("Invalid food supply pattern: " + pattern);
        }
    }


    // getters y setters
    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public List<Bacteria> getBacterias() {
        return bacterias;
    }

    public void setBacterias(List<Bacteria> bacterias) {
        this.bacterias = bacterias;
    }

    public void guardarExperimento(String rutaArchivo) {
        try (PrintWriter out = new PrintWriter(new FileWriter(rutaArchivo))) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            out.println(sdf.format(fechaInicio));
            out.println(sdf.format(fechaFin));
            for (Bacteria bacteria : bacterias) {
                out.println(bacteria.getNombre() + "," + bacteria.getCantidad());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Experimento abrirExperimento(String rutaArchivo) {
        try (BufferedReader in = new BufferedReader(new FileReader(rutaArchivo))) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaInicio = sdf.parse(in.readLine());
            Date fechaFin = sdf.parse(in.readLine());
            String linea;
            List<Bacteria> bacterias = new ArrayList<>();
            while ((linea = in.readLine()) != null) {
                String[] partes = linea.split(",");
                bacterias.add(new Bacteria(partes[0], Integer.parseInt(partes[1])));
            }
            // Provide default values for dosisComidaInicial, dosisComidaFinal, duracion and dosisComidaMicrogramos
            double dosisComidaInicial = 1.0;
            double dosisComidaFinal = 2.0;
            int duracion = 30; // default duration of the experiment in days
            double dosisComidaMicrogramos = 1500.0; // default food dose in micrograms
            return new Experimento(fechaInicio, fechaFin, bacterias, dosisComidaInicial, dosisComidaFinal, duracion, dosisComidaMicrogramos);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void mostrarInfoExperimento() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("Fecha de inicio: " + sdf.format(fechaInicio));
        System.out.println("Fecha de fin: " + sdf.format(fechaFin));
        System.out.println("Dosis de comida inicial: " + dosisComidaInicial);
        System.out.println("Dosis de comida final: " + dosisComidaFinal);
        System.out.println("Bacterias:");
        for (Bacteria bacteria : bacterias) {
            bacteria.mostrarInfo();
        }
    }

    public void visualizarNombresBacterias() {
        for (Bacteria bacteria : bacterias) {
            System.out.println(bacteria.getNombre());
        }
    }

    public void borrarBacteria(String nombreBacteria) {
        bacterias.removeIf(bacteria -> bacteria.getNombre().equals(nombreBacteria));
    }

    public enum FoodSupplyPattern {
        CONSTANT,
        LINEAR_INCREASE,
        ALTERNATING
    }

    public int[][] simulateExperiment() {
        int size = 20;
        int[][] petriDish = new int[size][size];

        // Place the bacteria in the center of the petri dish
        int initialBacteria = getBacterias().get(0).getCantidad(); // Assuming there's only one type of bacteria
        petriDish[size / 2][size / 2] = initialBacteria;

        // Simulate each day of the experiment
        for (int day = 0; day < duracion; day++) {
            // Create a copy of the petri dish to store the state for the next day
            int[][] nextDayPetriDish = new int[size][size];

            // Simulate the growth and movement of the bacteria
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    // TODO: Update nextDayPetriDish[i][j] based on the rules of the experiment
                }
            }

            // Update the petri dish to the state of the next day
            petriDish = nextDayPetriDish;
        }

        return petriDish;
    }

    public void sortBacteria() {
        Collections.sort(this.bacterias, new Comparator<Bacteria>() {
            @Override
            public int compare(Bacteria b1, Bacteria b2) {
                return Integer.compare(b1.getCantidad(), b2.getCantidad());
            }
        });

    }
}

