package Simulacion;

import Gestion_experimentos.Bacteria;

import java.util.ArrayList;
import java.util.List;

public class Celda {
    private int x;
    private int y;
    private int foodAmount;
    private List<Bacteria> bacterias;

    public Celda(int x, int y, int foodAmount) {
        this.x = x;
        this.y = y;
        this.foodAmount = foodAmount;
        this.bacterias = new ArrayList<>();
    }

    public void repartirComida() {
        if (!bacterias.isEmpty() && foodAmount >= 100) {
            int totalFoodConsumed = 0;
            for (Bacteria bacteria : bacterias) {
                int foodConsumed = bacteria.eat(20); // Each bacteria eats 20 micrograms of food
                totalFoodConsumed += foodConsumed;
            }
            foodAmount -= totalFoodConsumed; // Subtract the total food consumed from the food amount in the cell
        }
    }

    public void addBacteria(Bacteria bacteria) {
        bacterias.add(bacteria);
    }

    public void removeBacteria(Bacteria bacteria) {
        bacterias.remove(bacteria);
    }
}