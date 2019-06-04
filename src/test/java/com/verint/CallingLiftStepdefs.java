package com.verint;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class CallingLiftStepdefs {

    private Lift lift;

    @Given("^the lift has not yet been called$")
    public void theLiftHasNotYetBeenCalled() {
        lift = new Lift();
    }

    @Then("the doors are closed")
    public void theDoorsAreClosed() {
        assertThat(lift.areDoorsOpen(), is(equalTo(false)));
    }
}
