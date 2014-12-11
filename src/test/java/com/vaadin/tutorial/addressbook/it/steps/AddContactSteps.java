package com.vaadin.tutorial.addressbook.it.steps;

import static com.vaadin.tutorial.addressbook.it.JBehaveTableMapper.tableToBean;
import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import org.jbehave.core.annotations.AfterScenario;
import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.model.ExamplesTable;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

import com.vaadin.testbench.TestBench;
import com.vaadin.testbench.TestBenchTestCase;
import com.vaadin.tutorial.addressbook.it.pages.ContactRow;
import com.vaadin.tutorial.addressbook.it.pages.ListPage;
import com.vaadin.tutorial.addressbook.it.pages.NewContactPage;

/**
 * Steps for the "Add contact" story.
 * 
 */
public class AddContactSteps extends TestBenchTestCase {

    private ListPage listPage;
    private NewContactPage newContactPage;

    @BeforeScenario
    public void beforeScenario() {
        setDriver(TestBench.createDriver(new FirefoxDriver()));
        getDriver().get("http://localhost:8080/?restartApplication");
    }

    @AfterScenario
    public void afterScenario() {
        getDriver().quit();
    }

    @Given("the front page")
    public void theFrontPage() throws Throwable {
        listPage = PageFactory.initElements(getDriver(), ListPage.class);
    }

    @When("the user clicks the add contact button$")
    public void theUserClicksTheAddContactButton() throws Throwable {
        newContactPage = listPage.newContact();
    }

    @When("fills the contact details with these values:$values")
    public void fillsTheContactDetailsWithTheseValues(ExamplesTable inputTable)
            throws Throwable {
        Map<String, String> row = inputTable.getRow(0);

        newContactPage.setFirstName(row.get("Firstname"));
        newContactPage.setLastName(row.get("Lastname"));
        newContactPage.setCompany(row.get("Company"));
    }

    @When("searches for \"$name\"")
    public void searchesFor(String searchterm) throws Throwable {
        listPage.searchFor(searchterm);
    }

    @Then("the only row should contain these values:$expected")
    public void theOnlyRowShouldContainTheseValues(ExamplesTable expectedTable)
            throws Throwable {
        List<ContactRow> expected = tableToBean(expectedTable, ContactRow.class);
        ContactRow expectedRow = expected.get(0);
        ContactRow resultRow = listPage.getOnlyResultRow();

        assertEquals(expectedRow.getFirstname(), resultRow.getFirstname());
        assertEquals(expectedRow.getLastname(), resultRow.getLastname());
        assertEquals(expectedRow.getCompany(), resultRow.getCompany());
    }

}
