package com.vaadin.tutorial.calculator.it.features;

import io.cucumber.datatable.DataTable;
import cucumber.api.java.en.*;

public class StepDefinitions {

    private Actionwords actionwords;

    public StepDefinitions(DriverHolder driverHolder) {
        this.actionwords = new Actionwords(driverHolder);
    }

    @Given("^a calculator with the value \"(.*)\"$")
    public void aCalculatorWithTheValueVal(String val) {
        actionwords.aCalculatorWithTheValueVal(val);
    }

    @When("^the user adds$")
    public void theUserAdds() {
        actionwords.theUserAdds();
    }

    @Then("^the calculator value is \"(.*)\"$")
    public void theCalculatorValueIsValue(String value) {
        actionwords.theCalculatorValueIsValue(value);
    }
}