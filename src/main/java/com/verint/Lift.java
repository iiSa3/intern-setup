package com.verint;

import java.util.ArrayList;
import java.util.List;

public class Lift {
    private static boolean OPEN = true;
    private static boolean CLOSED = false;
    private int destination;
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
    }

    public Lift(int currentFloor, int min, int max) {
        this.currentFloor = currentFloor;
        this.destination = currentFloor;
        this.history = new ArrayList<String>();
        this.min = min;
        this.max= max;
        this.action = false;
    }

    public boolean areDoorsOpen() {
        return (doors);
    }


    public void call(int userFloor) {
        destination = userFloor;
        doors = CLOSED;
        //move(destination);
    }

    public void sendTo(int newFloor) {
        if(destination == currentFloor) {
            // this will only be reached if we get to the destination floor
            destination = newFloor;
            doors = CLOSED;
            //move(destination);
        }

    }
    public void printHistory(){
        for(String x : history){
            System.out.println(x);

        }
    }


    private void moveUp(int destination){
        for(;currentFloor<destination;currentFloor++) {
            history.add("Passing floor " + currentFloor);
        }
    }
    private void moveDown(int destination){
        for(;currentFloor>destination;currentFloor--) {
            history.add("Passing floor " + currentFloor);
        }
    }

    public void move(int destination) {
        if (destination <min || destination>max){
            this.action = false;
            return;
        }
        this.action = true;
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
