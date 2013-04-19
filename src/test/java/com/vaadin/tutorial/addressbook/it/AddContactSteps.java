package com.vaadin.tutorial.addressbook.it;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import org.jbehave.core.annotations.AfterScenario;
import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.model.ExamplesTable;
import org.junit.Assert;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.vaadin.testbench.TestBench;
import com.vaadin.testbench.TestBenchTestCase;

/**
 * Steps for the "Add contact" feature.
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
    public void the_front_page() throws Throwable {
        listPage = PageFactory.initElements(getDriver(), ListPage.class);
    }

    @When("the user clicks the add contact button$")
    public void the_user_clicks_the_add_contact_button() throws Throwable {
        newContactPage = listPage.newContact();
    }

    @When("fills the contact details with these values:$values")
    public void fills_the_contact_details_with_these_values(
            ExamplesTable inputTable) throws Throwable {
        Map<String, String> row = inputTable.getRow(0);

        newContactPage.setFirstName(row.get("Firstname"));
        newContactPage.setLastName(row.get("Lastname"));
        newContactPage.setCompany(row.get("Company"));
    }

    @When("searches for \"$name\"")
    public void searches_for(String searchterm) throws Throwable {
        listPage.searchFor(searchterm);
        Thread.sleep(500);
    }

    @Then("the only row should contain these values:$expected")
    public void the_only_row_should_contains_these_values(
            ExamplesTable expectedTable) throws Throwable {
        List<ContactRow> expected = tableToBean(expectedTable, ContactRow.class);
        ContactRow expectedRow = expected.get(0);
        ContactRow resultRow = listPage.getOnlyResultRow();

        Assert.assertEquals(expectedRow.getFirstname(),
                resultRow.getFirstname());
        Assert.assertEquals(expectedRow.getLastname(), resultRow.getLastname());
        Assert.assertEquals(expectedRow.getCompany(), resultRow.getCompany());
    }

    private <T> List<T> tableToBean(ExamplesTable table, Class<T> clazz) {
        List<T> list = Lists.newLinkedList();
        for (Map<String, String> row : table.getRows()) {
            list.add(mapBean(row, clazz));
        }
        return list;
    }

    private <T> T mapBean(Map<String, String> row, Class<T> clazz) {
        try {
            T newInstance = clazz.newInstance();
            BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
            Map<String, PropertyDescriptor> fields = Maps.newHashMap();

            for (PropertyDescriptor descriptor : beanInfo
                    .getPropertyDescriptors()) {
                if (clazz
                        .equals(descriptor.getReadMethod().getDeclaringClass())) {
                    fields.put(descriptor.getName().toLowerCase(), descriptor);
                }
            }

            for (String column : row.keySet()) {
                PropertyDescriptor field = fields.get(column.toLowerCase());
                if (field != null) {
                    String value = row.get(column);
                    field.getWriteMethod().invoke(newInstance, value);
                }
            }
            return newInstance;
        } catch (IntrospectionException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            return null;
        } catch (InstantiationException e) {
            e.printStackTrace();
            return null;
        }
    }
}
