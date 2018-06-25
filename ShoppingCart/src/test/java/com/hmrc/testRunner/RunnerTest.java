package com.hmrc.testRunner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;


@RunWith(Cucumber.class)
@CucumberOptions(features="features",glue={"com.hmrc.stepDefinition"})
public class RunnerTest {

}
