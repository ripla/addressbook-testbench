package com.vaadin.tutorial.addressbook.it.pages;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Page object for the address book list
 *
 */
public class ListPage {

    @FindBy(id = "new-contact")
    private WebElement newContactButton;

    @FindBy(id = "search-field")
    private WebElement searchField;

    @FindBy(xpath = "id('contact-list')//td[@class='v-table-cell-content'][1]")
    WebElement firstNameCell;

    @FindBy(xpath = "id('contact-list')//td[@class='v-table-cell-content'][2]")
    WebElement lastNameCell;

    @FindBy(xpath = "id('contact-list')//td[@class='v-table-cell-content'][3]")
    WebElement companyCell;

    private final WebDriver driver;

    public ListPage(WebDriver driver) {
        this.driver = driver;
    }

    public NewContactPage newContact() {
        newContactButton.click();
        return PageFactory.initElements(driver, NewContactPage.class);
    }

    public void searchFor(String param) {        
        searchField.sendKeys(param);
        // Wait for TextChangeListener timeout
        try {
            Thread.sleep(500); 
        } catch (InterruptedException ignored) {
        }
    }

    public ContactRow getOnlyResultRow() {
        return new ContactRow(firstNameCell.getText(), lastNameCell.getText(),
                companyCell.getText());
    }
}
