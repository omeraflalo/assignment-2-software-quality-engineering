import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("hellocucumber")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty")


public class RunCucumberTest {
    private String ADMIN_USERNAME = "admin";
    private String ADMIN_PASSWORD = "12345";
    private String couponId = "1111";
    private OpenCartActuator user;
    private OpenCartActuator admin;
    private String webDriver = "webdriver.chrome.driver";
    private String path = "C:\\Users\\omer aflalo\\Documents\\אוניברסיטה\\עבודה 3 בהנדסת איכות תוכנה\\assignment-2-software-quality-engineering\\Selenium\\chromedriver.exe";

    public void addCuponAndMannagerDelete() {
//        new OpenCartActuator(webDriver, path).managerCheckCouponExist(ADMIN_USERNAME, ADMIN_PASSWORD,couponId );
//        user = new OpenCartActuator(webDriver, path);
//        user.addItemToCart();
//        admin = new OpenCartActuator(webDriver, path);
//        admin.managerDeleteCoupun(ADMIN_USERNAME, ADMIN_PASSWORD, couponId);
//        user.userClickOnCheckout();
    }
}


