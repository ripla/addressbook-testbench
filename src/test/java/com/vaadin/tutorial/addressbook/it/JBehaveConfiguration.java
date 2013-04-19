package com.vaadin.tutorial.addressbook.it;

import java.util.Properties;

import org.jbehave.core.Embeddable;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.embedder.StoryControls;
import org.jbehave.core.failures.FailingUponPendingStep;
import org.jbehave.core.i18n.LocalizedKeywords;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.UnderscoredCamelCaseResolver;
import org.jbehave.core.junit.JUnitStory;
import org.jbehave.core.model.ExamplesTableFactory;
import org.jbehave.core.parsers.RegexStoryParser;
import org.jbehave.core.reporters.CrossReference;
import org.jbehave.core.reporters.FilePrintStreamFactory.ResolveToPackagedName;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.FreemarkerViewGenerator;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.jbehave.core.steps.ParameterConverters;

public abstract class JBehaveConfiguration extends JUnitStory {

    private final CrossReference xref = new CrossReference();

    public JBehaveConfiguration() {
        configuredEmbedder().embedderControls()
                .doGenerateViewAfterStories(true)
                .doIgnoreFailureInStories(false).doIgnoreFailureInView(false)
                .useThreads(10).useStoryTimeoutInSecs(60);
    }

    @Override
    public Configuration configuration() {
        Class<? extends Embeddable> embeddableClass = this.getClass();
        Properties viewResources = new FreemarkerViewGenerator()
                .defaultViewProperties();
        // viewResources.put("decorateNonHtml", "true");
        // Start from default ParameterConverters instance
        ParameterConverters parameterConverters = new ParameterConverters();
        // factory to allow parameter conversion and loading from external
        // resources (used by StoryParser too)
        ExamplesTableFactory examplesTableFactory = new ExamplesTableFactory(
                new LocalizedKeywords(),
                new LoadFromClasspath(embeddableClass), parameterConverters);

        return new MostUsefulConfiguration()
                .useStoryControls(
                        new StoryControls().doDryRun(false)
                                .doSkipScenariosAfterFailure(true))
                .useStoryLoader(new LoadFromClasspath(embeddableClass))
                .useStoryParser(new RegexStoryParser(examplesTableFactory))
                .useStoryPathResolver(new UnderscoredCamelCaseResolver())
                .useStoryReporterBuilder(
                        new StoryReporterBuilder()
                                .withCodeLocation(
                                        CodeLocations
                                                .codeLocationFromClass(embeddableClass))
                                .withDefaultFormats()
                                .withPathResolver(new ResolveToPackagedName())
                                .withViewResources(viewResources)
                                .withFormats(Format.CONSOLE)
                                .withFailureTrace(true)
                                .withFailureTraceCompression(true)
                                .withCrossReference(xref))
                .useParameterConverters(parameterConverters)
                .useStepMonitor(xref.getStepMonitor())
                .usePendingStepStrategy(new FailingUponPendingStep());
    }

    @Override
    public InjectableStepsFactory stepsFactory() {
        return new InstanceStepsFactory(configuration(), new AddContactSteps());
    }
}
