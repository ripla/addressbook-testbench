package com.vaadin.tutorial.addressbook.it;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

import com.vaadin.testbench.TestBench;
import com.vaadin.testbench.TestBenchTestCase;

public class EnterSvenCodedTest extends TestBenchTestCase {

    @Before
    public void setUp() {
        setDriver(TestBench.createDriver(new FirefoxDriver()));
        getDriver().get("http://localhost:8080/?restartApplication");
    }

    @After
    public void tearDown() {
        getDriver().quit();
    }

    @Test
    public void enterSven() throws InterruptedException {
        ListPage listPage = PageFactory.initElements(getDriver(),
                ListPage.class);

        // Click the "new" button
        NewContactPage newContactPage = listPage.newContact();

        // Enter the name
        newContactPage.setFirstName("Sven");
        newContactPage.setLastName("Svensson");
        newContactPage.setCompany("Vaadin Ltd");

        // Search for sven
        listPage.searchFor("Sven");

        // Wait for the text change event
        Thread.sleep(500);

        // Assert Sven
        ContactRow row = listPage.getOnlyResultRow();

        Assert.assertEquals("Sven", row.getFirstName());
        Assert.assertEquals("Svensson", row.getLastName());
        Assert.assertEquals("Vaadin Ltd", row.getCompany());
    }
}
