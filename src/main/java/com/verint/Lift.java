package com.verint;

public class Lift {
    private int destination;
    private int currentFloor;

    public Lift(int currentFloor){
        this.currentFloor = currentFloor;
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
        currentFloor = destination;
    }

    public boolean previousDoorState() {
        return false;
    }

    public int getFloor() {
        return currentFloor;
    }
}
