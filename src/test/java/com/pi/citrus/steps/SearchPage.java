package com.pi.citrus.steps;

import com.consol.citrus.http.client.HttpClient;
import com.pi.citrus.util.BeforeUtil;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Jidhu Madhu on 14/07/2021.
 */
public class SearchPage extends BasePage {

    @Autowired
    private HttpClient navigateToGoogle;

    @Autowired
    private HttpClient navigateToYahoo;

    @Autowired
    private BeforeUtil beforeUtil;

    @Before
    public void setUp(Scenario scenario) {
        runner = beforeUtil.getRunner();
    }

    @Given("The User is navigate to {string} Home page")
    public void requestToSearchEndPoint(String searchOption) throws Exception {
        switch (searchOption) {
            case "google":
                requestGoogleEndPoint();
                break;
            case "yahoo":
                requestYahooEndPoint();
                break;
            default:
                throw new Exception("Please give a valid request");
        }
    }

    private void requestGoogleEndPoint() {
        runner.http(httpAction -> httpAction.client(navigateToGoogle)
                .send()
                .get());
    }

    private void requestYahooEndPoint() {
        runner.http(action -> action.client(navigateToYahoo)
                .send()
                .get());
    }
}
