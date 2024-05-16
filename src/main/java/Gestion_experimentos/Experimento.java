package Gestion_experimentos;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

    public double calcularDosisDiaria(int dia) {
        double incrementoDiario = (dosisComidaMicrogramos - dosisComidaInicial) / duracion; // calculate daily increment based on duration and total food dose in micrograms

        return dosisComidaInicial + incrementoDiario * dia;
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

}

