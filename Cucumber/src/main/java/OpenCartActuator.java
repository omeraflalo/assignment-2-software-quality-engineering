import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class OpenCartActuator {

    private WebDriver driver;
    private WebDriverWait wait;

    public void initSession(String webDriver, String path, String webPath) {
        // webDriver = "webdriver.chrome.driver"
        // path = "C:\\Users\\eylon\\Downloads\\chromedriver_win32\\chromedriver.exe";
        System.setProperty(webDriver, path);

        // new chrome driver object
        this.driver = new ChromeDriver();

        // new web driver wait -> waits until element are loaded (40 sec max)
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));


        // launch website -> localhost
        driver.get("http://localhost/opencartpro"+webPath);
//        driver.getWindowHandle()

        // maximize the window - some web apps look different in different sizes
        driver.manage().window().maximize();


        /*
            If we wanted to test the web application on different devices -
                1. Open the web app
                2. Right click -> click inspect
                3. Click on the phone icon at the top left corner of the inspect window (the app changes preview format at this point)
                4. Locate the dimensions drop-down list at the top of the web app and choose device
                5. Copy dimensions size (on the right side of the drop-down list)
                   -> driver.manage().window().setSize(new Dimension(width, height));
         */

        System.out.println("Driver setup finished for - " + driver.getTitle());
    }

    public void waitMiliseconds(int mili){
        try {
            TimeUnit.MILLISECONDS.sleep(mili);
        } catch (Exception e) {
        }

    }
    public void addItemToCart() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/main/div[2]/div/div/div[2]/div[1]/form/div/div[1]/a/img"))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/main/div[2]/div/div/div[1]/div[2]/div[1]/form/div/button"))).click();
        waitMiliseconds(1000);
        driver.get("http://localhost/opencartpro/index.php?route=checkout/cart&language=en-gb");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/main/div[2]/div/div/div[2]/div[1]/h2/button"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/main/div[2]/div/div/div[2]/div[1]/div/div/form/div[1]/div/input"))).sendKeys("1111");

        WebElement element = driver.findElement(By.xpath("/html/body/main/div[2]/div/div/div[2]/div[1]/div/div/form/div[2]/button"));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", element);

//        element = driver.findElement(By.xpath("/html/body/main/div[2]/div/div/div[3]/div[2]/a"));
//        executor.executeScript("arguments[0].click();", element);


    }

    public WebElement findSpecificCoupon(List<WebElement> tbody, String id){
        for(int i =0; i< tbody.size(); i++){
            WebElement tr = tbody.get(i);
            if(tr.findElements(By.xpath("./child::*")).get(2).getText().equals(id)){
                return tr;
            }
        }
        return null;
    }
    public void enterLoginInfo(String username, String password) {
        //----------------------------------------------- Start login --------------------------------------------------------
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div[2]/div/div/div/div/div[2]/form/div[1]/div/input"))).sendKeys(username);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//html/body/div/div[2]/div/div/div/div/div[2]/form/div[2]/div[1]/input"))).sendKeys(password);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div/div[2]/div/div/div/div/div[2]/form/div[3]/button"))).click();
        //----------------------------------------------- Delete specific coupon --------------------------------------------------------
        waitMiliseconds(1000);
        WebElement tbody = driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div/div[2]/form/div[1]/table/tbody"));
        List<WebElement> allCoupons = tbody.findElements(By.xpath("./child::*"));
        WebElement coupon = findSpecificCoupon(allCoupons, "1111");
        if(coupon == null){
            System.out.println("coupon not found");
            return;
        }
        coupon.findElements(By.xpath("./child::*")).get(0).findElements(By.xpath("./child::*")).get(0).click();

//        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div/div[2]/div[2]/div/div[2]/form/div[1]/table/tbody/tr[2]/td[1]/input"))).click();
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div/div[2]/div[1]/div/div/button"))).click();
//        //----------------------------------------------- Press enter on popup --------------------------------------------------------
//        waitMiliseconds(1000);
//        driver.findElement(By.id("Value")).sendKeys(Keys.RETURN);

    }
    public void managerDeleteCopun() {

    }
}