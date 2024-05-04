package Gestion_experimentos;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Experimento {
    private Date fechaInicio;
    private Date fechaFin;
    private List<Bacteria> bacterias;

    public Experimento(Date fechaInicio, Date fechaFin, List<Bacteria> bacterias) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.bacterias = bacterias;
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
            return new Experimento(fechaInicio, fechaFin, bacterias);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

