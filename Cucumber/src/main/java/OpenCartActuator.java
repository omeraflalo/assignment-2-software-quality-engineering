import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class OpenCartActuator {

    private WebDriver driver;
    private WebDriverWait wait;


    public OpenCartActuator(String webDriver, String path, String webPath) {
        System.setProperty(webDriver, path);
        this.driver = new ChromeDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        driver.get("http://localhost/opencartpro" + webPath);
        driver.manage().window().maximize();
        System.out.println("Driver setup finished for - " + driver.getTitle());
    }

    public void waitMiliseconds(int mili) {
        try {
            TimeUnit.MILLISECONDS.sleep(mili);
        } catch (Exception e) {
        }
    }

    public void addItemToCart() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"content\"]/div[2]/div[1]/form/div/div[1]/a/img"))).click();
        waitMiliseconds(300);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"button-cart\"]"))).click();
        driver.get("http://localhost/opencartpro/index.php?route=checkout/cart&language=en-gb");
        waitMiliseconds(300);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"accordion\"]/div[1]/h2/button"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"input-coupon\"]"))).sendKeys("1111");

        WebElement element = driver.findElement(By.xpath("//*[@id=\"form-coupon\"]/div[2]/button"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);


    }

    public WebElement findSpecificCoupon(List<WebElement> tbody, String id) {
        for (WebElement tr : tbody) {
            if (tr.findElements(By.xpath("./child::*")).get(2).getText().equals(id)) {
                return tr;
            }
        }
        return null;
    }

    public void managerCheckCouponExist(String username, String password, String couponId) {
        loginManager(username, password);
        WebElement tbody = driver.findElement(By.xpath("//*[@id=\"form-coupon\"]/div[1]/table/tbody"));
        List<WebElement> allCoupons = tbody.findElements(By.xpath("./child::*"));
        WebElement coupon = findSpecificCoupon(allCoupons, couponId);
        if (coupon != null) {
            driver.close();
            return;
        }
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"content\"]/div[1]/div/div/a"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"input-name\"]"))).sendKeys("TestCoupon");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"input-code\"]"))).sendKeys(couponId);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"input-discount\"]"))).sendKeys("500");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"content\"]/div[1]/div/div/button"))).click();
        driver.close();
    }

    public void loginManager(String username, String password) {
        //----------------------------------------------- Start login --------------------------------------------------------
        driver.get("http://localhost/opencartpro/admin/index.php?route=marketing/coupon");
        waitMiliseconds(300);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"input-username\"]"))).sendKeys(username);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"input-password\"]"))).sendKeys(password);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"form-login\"]/div[3]/button"))).click();
        waitMiliseconds(300);

    }

    public void managerDeleteCoupun(String username, String password, String couponId) {
        loginManager(username, password);
        //----------------------------------------------- Delete specific coupon --------------------------------------------------------
        waitMiliseconds(300);
        WebElement tbody = driver.findElement(By.xpath("//*[@id=\"form-coupon\"]/div[1]/table/tbody"));
        List<WebElement> allCoupons = tbody.findElements(By.xpath("./child::*"));
        WebElement coupon = findSpecificCoupon(allCoupons, couponId);
        if (coupon == null) {
            System.out.println("coupon not found");
            return;
        }
        coupon.findElements(By.xpath("./child::*")).get(0).findElements(By.xpath("./child::*")).get(0).click();
        //----------------------------------------------- Press delete button --------------------------------------------------------
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"content\"]/div[1]/div/div/button"))).click();
        //----------------------------------------------- Press enter on popup --------------------------------------------------------
        driver.switchTo().alert().accept();
        driver.close();

    }

    public void tryCheckOut() {
        waitMiliseconds(300);
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"content\"]/div[3]/div[2]/a"))).click();
        WebElement element = driver.findElement(By.xpath("//*[@id=\"content\"]/div[3]/div[2]/a"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }
}