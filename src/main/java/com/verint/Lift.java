package com.verint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lift {
    private static boolean OPEN = true;
    private static boolean CLOSED = false;
    private int destination;
    private boolean[] destinationQueue;
    private int currentFloor;
    private int min;
    private int max;
    private boolean action;
    private boolean doors;
    private List<String> history;

    public Lift(int currentFloor){
        this.currentFloor = currentFloor;
        this.destination = currentFloor;
        this.history = new ArrayList<String>();
        this.min = 0;
        this.max = 10;
        destinationQueue = new boolean[max - min+1];
    }

    public Lift(int currentFloor, int min, int max) {
        this.currentFloor = currentFloor;
        this.destination = currentFloor;
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
        if(floor >= min && floor <= max) {
            destinationQueue[floor - min] = true;
            System.out.println(Arrays.toString(destinationQueue));
            if (destination == currentFloor) {
                // this will only be reached if we got to the destination floor
                destination = floor;
                doors = CLOSED;
            }
            this.action = true;
        }
        else{
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


    private void moveUp(int destination){
        for(;currentFloor<destination;currentFloor++) {
            if (destinationQueue[currentFloor - min]) {
                destinationQueue[currentFloor - min] = false;
                history.add("Lift arrived at floor " + currentFloor);
            } else {
                history.add("Passing floor " + currentFloor);
            }
        }
    }
    private void moveDown(int destination){
        for(;currentFloor>destination;currentFloor--) {
            if (destinationQueue[currentFloor - min]) {
                destinationQueue[currentFloor - min] = false;
                history.add("Lift arrived at floor " + currentFloor);
            } else {
                history.add("Passing floor " + currentFloor);
            }        }
    }

    public void move(int destination) {
        // lift can move up
        if(currentFloor< destination){
            moveUp(destination);
        }
        // lift can move down
        if(currentFloor> destination){
            moveDown(destination);
        }

        history.add("Lift arrived at floor " + currentFloor);

        doors = OPEN;
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

    public int getDestination() {
        return this.destination;
    }

    public boolean getAction() {
        return this.action;
    }
}
