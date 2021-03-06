package com.verint;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class CallingLiftStepdefs {

    private Lift lift;
    private int destination;

    private static final boolean OPEN = true;
    private static final boolean CLOSED = false;


    @Given("^the lift has not yet been called$")
    public void theLiftHasNotYetBeenCalled() {
        lift = new Lift(3);
    }

    @Then("the doors are closed")
    public void theDoorsAreClosed() {
        assertThat(lift.areDoorsOpen(), is(equalTo(false)));
    }

    @Given("The lift is at floor {int}")
    public void theLiftIsAtFloor(int floorNumber) {
        lift = new Lift(floorNumber);
    }

    @When("I call the lift from floor {int}")
    public void iCallTheLiftFromFloor(int userFloor) {
        this.destination = userFloor;
        lift.call(userFloor);
    }

    @Then("the doors are closed before it arrives")
    public void theDoorsAreClosedBeforeItArrives() {
        assertThat(lift.areDoorsOpen(), is(equalTo(CLOSED)));
    }

    @When("The lift arrives")
    public void theLiftArrives() {
        assertThat(lift.getFloor(),is(equalTo(destination)));

    }

    @Then("The doors are open")
    public void theDoorsAreOpen() {
        //lift.printHistory();
        assertThat(lift.areDoorsOpen(),is(equalTo(true)));
    }

    @Given("I select floor (-?\\d+)$")
    public void iSelectFloor(int newFloor) {
        lift.sendTo(newFloor);
        destination = newFloor;
    }

    @When("the lift passes floor {int}")
    public void theLiftPassesFloor(int passesFloor) {

        assertThat(lift.getFloorHistory().contains("Passing floor " + passesFloor), is(equalTo(true)));
    }

    @Then("The destination is floor {int}")
    public void theDestinationIsFloor(int floor) {
        assertThat(lift.isDestination(floor),is(equalTo(true)));
    }

    @And("The lift starts to move")
    public void theLiftStartsToMove() {
        lift.move();
    }



    @Then("The lift should {string}")
    public void theLiftShould(String action) {
        assertThat(action.equals("move"), is(equalTo(lift.getAction())));
    }


    @Given("The lift can go between floors (-?\\d+) and (-?\\d+)")
    public void theLiftCanGoBetweenFloorsMinAndMax(int min, int max) {
        lift = new Lift(0, min, max);
    }


    @Then("The lift stopped at floor {int}")
    public void theLiftStoppedAtFloor(int floor) {
        assertThat(lift.getFloorHistory().contains("Lift arrived at floor " + floor),is(equalTo(true)));
    }

    @Then("The lift order of arrival is {string}")
    public void theLiftOrderOfArrivalIs(String StrOrder) {
        String[] temp = StrOrder.split(",");
        int[] order = Arrays.stream(temp).mapToInt(Integer::parseInt).toArray();
        int currIndex = 0;
        List<String> history = lift.getFloorHistory();
        System.out.println(history.toString());
        for(String element: history){
            if(element.contains("arrived")) {
                assertThat(element.equals("Lift arrived at floor " + order[currIndex]), is(equalTo(true)));
                currIndex++;

                if (currIndex == order.length)
                    break;
            }
        }
        assertThat(currIndex,is(equalTo(order.length)));
    }

    @And("I call the lift from floor {int} in direction {string}")
    public void iCallTheLiftFromFloorInDirection(int floor, String direction) {
        lift.call(floor,(direction == "up") ? 1 : -1);
    }
}
