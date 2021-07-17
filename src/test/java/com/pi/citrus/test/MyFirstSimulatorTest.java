package com.pi.citrus.test;

import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.dsl.runner.TestRunner;
import com.consol.citrus.dsl.testng.TestNGCitrusTestRunner;
import com.consol.citrus.http.client.HttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * @author Jidhu Madhu on 17/07/2021.
 */
public class MyFirstSimulatorTest extends TestNGCitrusTestRunner {

    @Autowired
    private HttpClient getUser;


    @Test
    @Parameters("runner")
    @CitrusTest(name = "MyFirstTest")
    public void MyTest(@Optional @CitrusResource TestRunner runner) {
        runner.http(action -> action.client(getUser)
                .send()
                .get("login")
                .queryParam("username", "christoph")
                .queryParam("password", "secr3t"));

        runner.http(action -> action.client(getUser)
                .receive()
                .response(HttpStatus.OK));
    }
}
