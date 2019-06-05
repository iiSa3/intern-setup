package com.verint;

import java.util.ArrayList;
import java.util.List;

public class Lift {
    private int destination;
    private int currentFloor;
    private List<String> history;

    public Lift(int currentFloor){
        this.currentFloor = currentFloor;
        this.history = new ArrayList<String>();
    }
    public boolean areDoorsOpen() {
        return (destination == currentFloor);
    }


    public void call(int userFloor) {
        destination = userFloor;
        move(destination);
    }

    public void sendTo(int newFloor) {
        destination = newFloor;
        move(destination);
    }

    private void move(int destination) {
        // lift can move up
        if(currentFloor< destination){
            for(;currentFloor<destination;currentFloor++) {
                history.add("Passing floor " + currentFloor);
            }
        }
        // lift can move down
        if(currentFloor> destination){
            for(;currentFloor>destination;currentFloor--) {
                history.add("Passing floor " + currentFloor);
            }
        }

        history.add("Lift arrived at floor " + currentFloor);
    }

    public boolean previousDoorState() {
        return false;
    }

    public int getFloor() {
        return currentFloor;
    }

}
