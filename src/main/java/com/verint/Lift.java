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
    private int direction = 1;
    private static final int UP = 1;
    private static final int DOWN = -1;

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
            if (destination == currentFloor) {
                // this will only be reached if we got to the destination floor
                //destination = floor;
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
        for(;currentFloor<=destination;currentFloor++) {
            if (destinationQueue[currentFloor - min]) {
                destinationQueue[currentFloor - min] = false;
                history.add("Lift arrived at floor " + currentFloor);
                break;
            } else {
                history.add("Passing floor " + currentFloor);
            }
        }
    }
    private void moveDown(int destination){
        for(;currentFloor>=destination;currentFloor--) {
            if (destinationQueue[currentFloor - min]) {
                destinationQueue[currentFloor - min] = false;
                history.add("Lift arrived at floor " + currentFloor);
                break;
            } else {
                history.add("Passing floor " + currentFloor);
            }
        }
    }

    public void move() {
        //System.out.println("Moving!");
        //System.out.println(Arrays.toString(destinationQueue));
        //System.out.println("destination : "+destination);
        //System.out.println("current floor : " + currentFloor);
        // lift can move up
        if(currentFloor< destination){
            moveUp(destination);
        }
        // lift can move down
        if(currentFloor> destination){
            moveDown(destination);
        }
        //System.out.println("moving has happened!");
        //System.out.println("destination: " +destination);
        //System.out.println("current floor: " + currentFloor);
        checkInDirection(direction);

        if(destination == currentFloor)
            checkInDirection(direction *-1);

        if(destination != currentFloor) {
            //System.out.println("Got new destination! " + destination);
            move();
        }

        doors = OPEN;
    }

    private void checkInDirection(int direction) {
        for(int i = currentFloor - min + direction; i >= 0 && i <= max-min; i+=direction) {
            if (destinationQueue[i]) { // If this floor is to be visited
                destination = i + min;
                this.direction = direction;
                break;
            }
        }
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
