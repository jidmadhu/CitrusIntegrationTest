package com.pi.citrus.steps;

import com.consol.citrus.config.CitrusSpringConfig;
import com.consol.citrus.dsl.runner.TestRunner;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Jidhu Madhu on 14/07/2021.
 */

@CucumberContextConfiguration
@ContextConfiguration(classes = CitrusSpringConfig.class)
public class BasePage {
    protected TestRunner runner;
}
