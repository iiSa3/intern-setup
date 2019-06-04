package com.verint;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class CallingLiftStepdefs {

    private Lift lift;

    private static final boolean OPEN = true;
    private static final boolean CLOSED = false;
    @Given("^the lift has not yet been called$")
    public void theLiftHasNotYetBeenCalled() {
        lift = new Lift();
    }

    @Then("the doors are closed")
    public void theDoorsAreClosed() {
        assertThat(lift.areDoorsOpen(), is(equalTo(false)));
    }

    @Given("the lift is at floor {int}")
    public void theLiftIsAtFloor(int floorNumber) {
        lift.setFloorNumber(floorNumber);
    }

    @When("i call the lift from floor {int}")
    public void iCallTheLiftFromFloor(int userFloor) {
        lift.call(userFloor);
    }

    @Then("the doors are closed before it arrives")
    public void theDoorsAreClosedBeforeItArrives() {
        assertThat(lift.previousDoorState(), is(equalTo(CLOSED)));
    }
}
