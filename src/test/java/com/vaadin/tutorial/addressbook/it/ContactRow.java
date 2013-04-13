package com.vaadin.tutorial.addressbook.it;

/**
 * A single (immutable) contact row.
 * 
 */
public class ContactRow {

    private final String firstName;
    private final String lastName;
    private final String company;

    public ContactRow(String firstName, String lastName, String company) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.company = company;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCompany() {
        return company;
    }

    public String getFirstName() {
        return firstName;
    }
}
