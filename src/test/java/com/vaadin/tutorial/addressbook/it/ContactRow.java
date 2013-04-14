package com.vaadin.tutorial.addressbook.it;

/**
 * A single contact row.
 * 
 */
public class ContactRow {

    private String firstname;
    private String lastname;
    private String company;

    public ContactRow(String firstname, String lastname, String company) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.company = company;
    }

    public String getLastname() {
        return lastname;
    }

    public String getCompany() {
        return company;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
