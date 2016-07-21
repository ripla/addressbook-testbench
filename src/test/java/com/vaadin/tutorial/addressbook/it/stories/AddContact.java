package com.vaadin.tutorial.addressbook.it.stories;

import com.vaadin.tutorial.addressbook.it.JBehaveConfiguration;
import com.vaadin.tutorial.addressbook.it.steps.AddContactSteps;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;

/**
 * Runs the JBehave story <code>add_contact.story</code>
 */
public class AddContact extends JBehaveConfiguration {

    @Override
    public InjectableStepsFactory stepsFactory() {
        return new InstanceStepsFactory(configuration(), new AddContactSteps());
    }
}
