package com.vaadin.tutorial.addressbook.it;

import com.vaadin.testbench.TestBench;
import com.vaadin.testbench.TestBenchTestCase;
import cucumber.api.DataTable;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.util.List;
import org.junit.Assert;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Steps for the "Add contact" feature.
 * 
 */
public class AddContactSteps extends TestBenchTestCase {

    private ListPage listPage;
    private NewContactPage newContactPage;

    // note Cucumber Before and After, not jUnit
    @Before
    public void beforeScenario() {
        setDriver(TestBench.createDriver(new FirefoxDriver()));
        getDriver().get("http://localhost:8080/?restartApplication");
    }

    @After
    public void afterScenario() {
        getDriver().quit();
    }

    @Given("^the front page$")
    public void the_front_page() throws Throwable {
        listPage = PageFactory.initElements(getDriver(), ListPage.class);
    }

    @When("^the user clicks the add contact button$")
    public void the_user_clicks_the_add_contact_button() throws Throwable {
        newContactPage = listPage.newContact();
    }

    @When("^fills the contact details with these values:$")
    public void fills_the_contact_details_with_these_values(DataTable inputTable)
            throws Throwable {
        // TODO unfuglify
        List<String> row = inputTable.cells(1).get(0);

        newContactPage.setFirstName(row.get(0));
        newContactPage.setLastName(row.get(1));
        newContactPage.setCompany(row.get(2));
    }

    @When("^searches for \"([^\"]*)\"$")
    public void searches_for(String searchterm) throws Throwable {
        listPage.searchFor(searchterm);
        Thread.sleep(500);
    }

    @Then("^the only row should contain these values:$")
    public void the_only_row_should_contains_these_values(
            List<ContactRow> expected) throws Throwable {
        ContactRow expectedRow = expected.get(0);
        ContactRow resultRow = listPage.getOnlyResultRow();

        Assert.assertEquals(expectedRow.getFirstname(),
                resultRow.getFirstname());
        Assert.assertEquals(expectedRow.getLastname(), resultRow.getLastname());
        Assert.assertEquals(expectedRow.getCompany(), resultRow.getCompany());
    }
}
