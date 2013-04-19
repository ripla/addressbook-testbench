package com.vaadin.tutorial.addressbook.it;

import java.util.Properties;

import org.jbehave.core.Embeddable;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.embedder.StoryControls;
import org.jbehave.core.failures.FailingUponPendingStep;
import org.jbehave.core.i18n.LocalizedKeywords;
import org.jbehave.core.io.CasePreservingResolver;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.junit.JUnitStory;
import org.jbehave.core.model.ExamplesTableFactory;
import org.jbehave.core.parsers.RegexStoryParser;
import org.jbehave.core.reporters.CrossReference;
import org.jbehave.core.reporters.FilePrintStreamFactory.ResolveToPackagedName;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.FreemarkerViewGenerator;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.ParameterConverters;

/**
 * JBehave JUnit configuration for all stories.
 * 
 */
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
        // most of the settings have default values and could probably be
        // omitted
        Class<? extends Embeddable> embeddableClass = this.getClass();
        Properties viewResources = new FreemarkerViewGenerator()
                .defaultViewProperties();
        ParameterConverters parameterConverters = new ParameterConverters();
        ExamplesTableFactory examplesTableFactory = new ExamplesTableFactory(
                new LocalizedKeywords(),
                new LoadFromClasspath(embeddableClass), parameterConverters);

        return new MostUsefulConfiguration()
                .useStoryControls(
                        new StoryControls().doDryRun(false)
                                .doSkipScenariosAfterFailure(true))
                .useStoryLoader(new LoadFromClasspath(embeddableClass))
                .useStoryParser(new RegexStoryParser(examplesTableFactory))
                .useStoryPathResolver(new CasePreservingResolver())
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
}
