package com.vaadin.tutorial.calculator.it.features;

import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;

import com.vaadin.flow.component.button.testbench.ButtonElement;
import com.vaadin.flow.component.textfield.testbench.TextFieldElement;
import com.vaadin.testbench.IPAddress;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Actionwords {

    private final DriverHolder driverHolder;

    public Actionwords(DriverHolder driverHolder) {
        this.driverHolder = driverHolder;
    }

    @Before
    public void beforeScenario() {
        driverHolder.setDriver(new ChromeDriver());
        driverHolder.getDriver()
                .get("http://" + IPAddress.findSiteLocalAddress() + ":8080");
    }

    @After
    public void afterScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] screenshotBytes = ((TakesScreenshot) driverHolder
                    .getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshotBytes, "image/png");
        }
        driverHolder.getDriver().quit();
    }

    public void aCalculatorWithTheValueVal(String inputNumber) {
        TextFieldElement numberInput = driverHolder.$(TextFieldElement.class)
                .id("numberInput");
        numberInput.setValue(inputNumber);
    }

    public void theUserAdds() {
        ButtonElement numberInput = driverHolder.$(ButtonElement.class)
                .id("addButton");
        numberInput.click();
    }

    public void theCalculatorValueIsValue(String value) {
        TextFieldElement numberInput = driverHolder.$(TextFieldElement.class)
                .id("numberInput");
        String result = numberInput.getValue();
        Assert.assertEquals(value, result);
    }
}