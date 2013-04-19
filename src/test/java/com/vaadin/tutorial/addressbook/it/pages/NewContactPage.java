package com.vaadin.tutorial.addressbook.it.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Page object for the new contact form
 * 
 */
public class NewContactPage {

    @FindBy(id = "First-Name")
    WebElement firstName;

    @FindBy(id = "Last-Name")
    WebElement lastName;

    @FindBy(id = "Company")
    WebElement company;

    public void setFirstName(String name) {
        firstName.clear();
        firstName.sendKeys(name);
    }

    public void setLastName(String name) {
        lastName.clear();
        lastName.sendKeys(name);
    }

    public void setCompany(String newCompany) {
        company.clear();
        company.sendKeys(newCompany);
    }
}
