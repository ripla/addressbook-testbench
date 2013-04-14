package com.vaadin.tutorial.addressbook.it;

import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Runs all the Cucumber features as tests
 */
@RunWith(Cucumber.class)
@Cucumber.Options(format = { "json:target/report.json" })
public class RunCucumberTest {

}
