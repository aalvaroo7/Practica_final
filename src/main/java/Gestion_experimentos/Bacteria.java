package Gestion_experimentos;

import java.util.Random;

public class Bacteria {
    private int x;
    private int y;
    private int foodConsumed;
    private boolean isAlive;
    private int maxX;
    private int maxY;
    private int[][] foodGrid;

    public Bacteria(int x, int y, int maxX, int maxY, int[][] foodGrid) {
        this.x = x;
        this.y = y;
        this.foodConsumed = 0;
        this.isAlive = true;
        this.maxX = maxX;
        this.maxY = maxY;
        this.foodGrid = foodGrid;
    }

    public void simulateDailyBehavior() {
        eat();
        move();
        reproduce();
        die();
    }

    private void eat() {
        int foodAvailable = this.foodGrid[this.x][this.y];
        this.foodConsumed += foodAvailable;
        this.foodGrid[this.x][this.y] = 0; // The bacteria consumes all the food at its position
    }
    private void move() {
        int direction = generateRandomNumber(4); // 0: up, 1: right, 2: down, 3: left
        int newX = this.x;
        int newY = this.y;

        switch (direction) {
            case 0: // up
                newY++;
                break;
            case 1: // right
                newX++;
                break;
            case 2: // down
                newY--;
                break;
            case 3: // left
                newX--;
                break;
        }
        // Check if the new position is within the boundaries of the petri dish
        if (newX >= 0 && newX < this.maxX && newY >= 0 && newY < this.maxY) {
            this.x = newX;
            this.y = newY;
        }


    private void die() {
        // Implement the logic for dying
        // If conditions are met, set isAlive to false
    }

    private int generateRandomNumber(int limit) {
        Random random = new Random();
        return random.nextInt(limit);
    }

    private void calculateMovement() {
        // Implement the logic for calculating movement
        // Use the generateRandomNumber method to decide the direction of movement
    }
}