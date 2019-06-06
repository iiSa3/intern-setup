package com.verint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lift {
    private static boolean OPEN = true;
    private static boolean CLOSED = false;
    private boolean[] destinationQueue;
    private int currentFloor;
    private int min;
    private int max;
    private boolean action;
    private boolean doors = CLOSED;
    private List<String> history;
    private int direction = 1;
    private static final int UP = 1;
    private static final int DOWN = -1;

    public Lift(int currentFloor){
        this.currentFloor = currentFloor;
        this.history = new ArrayList<String>();
        this.min = 0;
        this.max = 10;
        destinationQueue = new boolean[max - min+1];
    }

    public Lift(int currentFloor, int min, int max) {
        this.currentFloor = currentFloor;
        this.history = new ArrayList<String>();
        this.min = min;
        this.max= max;
        this.action = false;
        destinationQueue = new boolean[max - min+1];
    }

    public boolean areDoorsOpen() {
        return (doors);
    }

    private void addDestination(int floor){
        if(floor != this.getFloor() && floor >= min && floor <= max) {
            destinationQueue[floor - min] = true;
            this.doors = CLOSED;
            this.action = true;
        }else if(floor == this.getFloor()) {
            this.doors = OPEN;
            this.action = true;
        }else{
            this.action = false;
        }
    }
    public void call(int userFloor) {
        addDestination(userFloor);
    }

    public void sendTo(int newFloor) {
        addDestination(newFloor);

    }
    public void printHistory(){
        for(String x : history){
            System.out.println(x);

        }
    }
    private void moveInDirection(){
        currentFloor+=direction;
        if (destinationQueue[currentFloor - min]) {
            destinationQueue[currentFloor - min] = false;
            history.add("Lift arrived at floor " + currentFloor);
            this.doors = OPEN;
        } else {
            this.doors = CLOSED;
            history.add("Passing floor " + currentFloor);
        }
    }

    public void move() {
        // While there is still a floor to visit in the current direction, move in that direction
        while(checkInDirection(direction)) {
            moveInDirection();
        }

        // If there is something in the other direction, swap the direction
        if(checkInDirection(direction * -1)) {
            direction *= -1;
            // While there is still a floor to visit in the new direction, move in that direction
            while(checkInDirection(direction)) {
                moveInDirection();
            }
        }
    }

    private boolean checkInDirection(int direction) {
        for(int i = currentFloor - min + direction; i >= 0 && i <= max-min; i+=direction) {
            if (destinationQueue[i]) { // If this floor is to be visited
                return true;
            }
        }
        return false;
    }

    public boolean previousDoorState() {
        return false;
    }

    public int getFloor() {
        return currentFloor;
    }

    public List<String> getFloorHistory() {

        return history;
    }

    public boolean isDestination(int floor) {
        return this.destinationQueue[floor - min];
    }

    public boolean getAction() {
        return this.action;
    }
}
