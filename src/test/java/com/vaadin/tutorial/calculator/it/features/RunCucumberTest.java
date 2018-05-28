package com.vaadin.tutorial.calculator.it.features;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"html:target/cucumber-reports/site",
                           "json:target/cucumber-reports/cucumber.json"})
public class RunCucumberTest {

}