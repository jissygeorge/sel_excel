package com.testproject.testRunner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/features/",
plugin = {"pretty","html:target/cucumber-report/smoke-htmltest",
		"json:target/cucumber-report/SmokeTestReport/smoketestresults.json"}, 
glue="com.testprojects.stepDefinitions"
)
public class RunFunctionalTest {

}
