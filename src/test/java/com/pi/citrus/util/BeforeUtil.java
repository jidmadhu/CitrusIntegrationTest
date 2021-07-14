package com.pi.citrus.util;

import com.consol.citrus.Citrus;
import com.consol.citrus.dsl.runner.DefaultTestRunner;
import com.consol.citrus.dsl.runner.TestRunner;
import org.springframework.stereotype.Component;

/**
 * @author Jidhu Madhu on 14/07/2021.
 */
@Component
public class BeforeUtil {

    private Citrus citrus;
    private TestRunner runner;

    public TestRunner getRunner() {
        citrus = Citrus.newInstance();
        runner = new DefaultTestRunner(citrus.getApplicationContext(), citrus.createTestContext());
        return runner;
    }

}
