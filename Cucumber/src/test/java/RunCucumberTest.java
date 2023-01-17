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
    private String TEACHER_USERNAME = "teacher";
    private String TEACHER_PASSWORD = "Teacher1995$";
    private String COURSE_NAME = "Demo course";
    private String ADMIN_USERNAME = "admin";
    private String ADMIN_PASSWORD = "12345";
    private int DEFAULT_LENGTH = 5;
    private OpenCartActuator user;
    private OpenCartActuator admin;
    private String webDriver = "webdriver.chrome.driver";
    private String path = "C:\\Users\\omer aflalo\\Documents\\אוניברסיטה\\עבודה 3 בהנדסת איכות תוכנה\\assignment-2-software-quality-engineering\\Selenium\\chromedriver.exe";

    public RunCucumberTest() {
//        user = new OpenCartActuator();
//        user.initSession(webDriver, path,"");
        admin = new OpenCartActuator();
        admin.initSession(webDriver, path,"/admin");
    }

    public void addCuponAndMannagerDelete() {
//        user.addItemToCart();
        admin.enterLoginInfo(ADMIN_USERNAME,ADMIN_PASSWORD);
    }
}


