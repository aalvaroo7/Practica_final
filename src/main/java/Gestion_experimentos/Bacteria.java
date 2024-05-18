package Gestion_experimentos;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bacteria {
    private int x;
    private int y;
    private int foodConsumed;
    private boolean isAlive;
    private int maxX;
    private int maxY;
    private int[][] foodGrid;
    private Bacteria[][] bacteriaGrid;
    private static int totalBacteria;

    public Bacteria(int x, int y, int maxX, int maxY, int[][] foodGrid, Bacteria[][] bacteriaGrid) {
        this.x = x;
        this.y = y;
        this.foodConsumed = 0;
        this.isAlive = true;
        this.maxX = maxX;
        this.maxY = maxY;
        this.foodGrid = foodGrid;
        this.bacteriaGrid = bacteriaGrid;
        totalBacteria++;
    }

    public void simulateDailyBehavior() {
        eat();
        move();
        reproduce();
        die();
    }

    private void eat() {
        int foodAvailable = this.foodGrid[this.x][this.y];
        if (foodAvailable >= 100) {
            this.foodConsumed += 20;
            this.foodGrid[this.x][this.y] -= 20; // The bacteria consumes 20 micrograms of food if there are 100 or more
            generateRandomNumberAndAct();
        } else if (foodAvailable > 9) {
            this.foodConsumed += 10;
            this.foodGrid[this.x][this.y] -= 10; // The bacteria consumes 10 micrograms of food if there are between 10 and 99 micrograms
            generateRandomNumberAndAct();
        } else {
            this.foodConsumed += foodAvailable;
            this.foodGrid[this.x][this.y] = 0; // The bacteria consumes all the food at its position if there are less than 10 micrograms
        }
    }

    private void generateRandomNumberAndAct() {
        // Generate a random number between 0 and 100
        int randomNumber = new Random().nextInt(100);

        if (randomNumber < 6) {
            die();
        } else if (randomNumber >= 20) {
            move(randomNumber);
        }
    }

    private void move(int randomNumber) {
        int newX = this.x;
        int newY = this.y;

        if (randomNumber < 30) {
            newY++; // move up
        } else if (randomNumber < 40) {
            newX++; // move right
        } else if (randomNumber < 50) {
            newY--; // move down
        } else if (randomNumber < 60) {
            newX--; // move left
        } else if (randomNumber < 70) {
            newX++; newY++; // move up right
        } else if (randomNumber < 80) {
            newX++; newY--; // move down right
        } else if (randomNumber < 90) {
            newX--; newY--; // move down left
        } else {
            newX--; newY++; // move up left
        }

        // Check if the new position is within the boundaries of the petri dish
        if (newX >= 0 && newX < this.maxX && newY >= 0 && newY < this.maxY) {
            this.x = newX;
            this.y = newY;
        }
    }
    private List<Bacteria> reproduce() {
        List<Bacteria> newBacterias = new ArrayList<>();
        // Assuming a bacteria can reproduce if it has consumed more than 10 units of food
        if (this.foodConsumed > 10 && totalBacteria < this.maxX * this.maxY) { // Check if the petri dish has reached its carrying capacity
            // Generate a random position nearby for the new bacteria
            int newX = this.x + generateRandomNumber(3) - 1; // -1, 0, or 1
            int newY = this.y + generateRandomNumber(3) - 1; // -1, 0, or 1

            // Check if the new position is within the boundaries of the petri dish and if it's not occupied by another bacteria
            if (newX >= 0 && newX < this.maxX && newY >= 0 && newY < this.maxY && this.bacteriaGrid[newX][newY] == null) {
                // Create a new bacteria at the new position
                Bacteria newBacteria = new Bacteria(newX, newY, this.maxX, this.maxY, this.foodGrid, this.bacteriaGrid);
                newBacterias.add(newBacteria);
                this.bacteriaGrid[newX][newY] = newBacteria; // Update the bacteria grid
            }
        }
        return newBacterias;
    }

    private void die() {
        // Assuming a bacteria dies if it has not consumed any food
        if (this.foodConsumed == 0) {
            this.isAlive = false;
        }
    }

    private int generateRandomNumber(int limit) {
        Random random = new Random();
        return random.nextInt(limit);
    }

    private void calculateMovement() {
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
    }
}