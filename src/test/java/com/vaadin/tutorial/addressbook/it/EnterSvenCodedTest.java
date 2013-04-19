package com.vaadin.tutorial.addressbook.it;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

import com.vaadin.testbench.TestBench;
import com.vaadin.testbench.TestBenchTestCase;
import com.vaadin.tutorial.addressbook.it.pages.ContactRow;
import com.vaadin.tutorial.addressbook.it.pages.ListPage;
import com.vaadin.tutorial.addressbook.it.pages.NewContactPage;
import com.vaadin.tutorial.addressbook.it.stories.AddContact;

/**
 * This is exactly the same test as in {@link AddContact}
 */
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
    @Ignore
    // only run the JBehave stories for now
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

        Assert.assertEquals("Sven", row.getFirstname());
        Assert.assertEquals("Svensson", row.getLastname());
        Assert.assertEquals("Vaadin Ltd", row.getCompany());
    }
}
