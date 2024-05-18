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
    public void simulateDailyBehavior() {
        if (!isNewborn) {
            for (int i = 0; i < 10; i++) {
                eat();
                move();
            }
            reproduce();
            die();
        }
        isNewborn = false; // After the first day, the bacteria is no longer a newborn
    }
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
        if (!isNewborn) {
            for (int i = 0; i < 10; i++) {
                eat();
                move();
            }
            reproduce();
            die();
        }
        isNewborn = false; // After the first day, the bacteria is no longer a newborn
    }

    private void eat() {
        int foodAvailable = this.foodGrid[this.x][this.y];
        if (foodAvailable >= 100) {
            this.foodConsumed += 20;
            this.foodGrid[this.x][this.y] -= 20; // The bacteria consumes 20 micrograms of food if there are 100 or more
            generateRandomNumberAndAct(3, 60, 100);
        } else if (foodAvailable > 9) {
            this.foodConsumed += 10;
            this.foodGrid[this.x][this.y] -= 10; // The bacteria consumes 10 micrograms of food if there are between 10 and 99 micrograms
            generateRandomNumberAndAct(6, 20, 100);
        } else {
            this.foodConsumed += foodAvailable;
            this.foodGrid[this.x][this.y] = 0; // The bacteria consumes all the food at its position if there are less than 10 micrograms
            generateRandomNumberAndAct(20, 60, 100);
        }
    }

    private void generateRandomNumberAndAct(int deathThreshold, int stayThreshold, int moveThreshold) {
        // Generate a random number between 0 and 100
        int randomNumber = new Random().nextInt(100);

        if (randomNumber < deathThreshold) {
            die();
        } else if (randomNumber >= stayThreshold && randomNumber < moveThreshold) {
            move(randomNumber);
        }
    }

    private void move(int randomNumber) {
        int newX = this.x;
        int newY = this.y;

        if (randomNumber < 65) {
            newY++; // move up
        } else if (randomNumber < 70) {
            newX++; // move right
        } else if (randomNumber < 75) {
            newY--; // move down
        } else if (randomNumber < 80) {
            newX--; // move left
        } else if (randomNumber < 85) {
            newX++; newY++; // move up right
        } else if (randomNumber < 90) {
            newX++; newY--; // move down right
        } else if (randomNumber < 95) {
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
        int numberOfChildren = 0;

        if (this.foodConsumed >= 150) {
            numberOfChildren = 3;
        } else if (this.foodConsumed >= 100) {
            numberOfChildren = 2;
        } else if (this.foodConsumed >= 50) {
            numberOfChildren = 1;
        }

        for (int i = 0; i < numberOfChildren; i++) {
            Bacteria newBacteria = new Bacteria(this.x, this.y, this.maxX, this.maxY, this.foodGrid, this.bacteriaGrid);
            newBacterias.add(newBacteria);
            this.bacteriaGrid[this.x][this.y] = newBacteria; // Update the bacteria grid
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