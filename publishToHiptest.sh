#!/bin/bash

hiptest-publisher --config-file hiptest/hiptest-publisher.config --push target/failsafe-reports/TEST-com.vaadin.tutorial.calculator.it.features.RunCucumberTest.xml --token=$1 --test-run-id=$2
