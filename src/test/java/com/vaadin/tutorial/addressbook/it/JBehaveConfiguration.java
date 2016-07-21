package com.vaadin.tutorial.addressbook.it;

import com.vaadin.tutorial.addressbook.AddressBookApplication;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.junit.JUnitStory;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * JBehave JUnit configuration for all stories.
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AddressBookApplication.class)
@WebIntegrationTest(value = "server.port=9000")
public abstract class JBehaveConfiguration extends JUnitStory {

    public JBehaveConfiguration() {
        configuredEmbedder().embedderControls()
                .doGenerateViewAfterStories(true)
                .doIgnoreFailureInStories(false).doIgnoreFailureInView(false);
    }

    @Override
    public Configuration configuration() {
        return new MostUsefulConfiguration();
    }
}
