package com.verint;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class CallingLiftStepdefs {

    private Lift lift;
    private int destination;
    private int initialFloor;

    private static final boolean OPEN = true;
    private static final boolean CLOSED = false;


    @Given("^the lift has not yet been called$")
    public void theLiftHasNotYetBeenCalled() {
        lift = new Lift(3);
    }

    @Then("the doors are closed")
    public void theDoorsAreClosed() {
        assertThat(lift.previousDoorState(), is(equalTo(false)));
    }

    @Given("The lift is at floor {int}")
    public void theLiftIsAtFloor(int floorNumber) {
        lift = new Lift(floorNumber);
        this.initialFloor = floorNumber;
    }

    @When("I call the lift from floor {int}")
    public void iCallTheLiftFromFloor(int userFloor) {
        this.destination = userFloor;
        lift.call(userFloor);
    }

    @Then("the doors are closed before it arrives")
    public void theDoorsAreClosedBeforeItArrives() {
        assertThat(lift.previousDoorState(), is(equalTo(CLOSED)));
    }

    @When("The lift arrives")
    public void theLiftArrives() {
        assertThat(lift.getFloor(),is(equalTo(destination)));

    }

    @Then("The doors are open")
    public void theDoorsAreOpen() {
        assertThat(lift.areDoorsOpen(),is(equalTo(true)));
    }

    @Given("I select floor {int}")
    public void iSelectFloor(int newFloor) {
        lift.sendTo(newFloor);
        destination = lift.getDestination();
    }

    @When("the lift passes floor {int}")
    public void theLiftPassesFloor(int passesFloor) {
        lift.printHistory();
        assertThat(lift.getFloorHistory().contains("Passing floor " + passesFloor), is(equalTo(true)));
    }

    @Then("The destination is floor {int}")
    public void theDestinationIsFloor(int floor) {
        assertThat(lift.getDestination(),is(equalTo(floor)));
    }

    @And("The lift starts to move")
    public void theLiftStartsToMove() {
        lift.move(lift.getDestination());
    }
}
