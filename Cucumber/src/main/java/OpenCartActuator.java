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

    private int waitingMil = 200;


    public OpenCartActuator(String webDriver, String path) {
        System.setProperty(webDriver, path);
        this.driver = new ChromeDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        driver.get("http://localhost/opencartpro");
        driver.manage().window().maximize();
        System.out.println("Driver setup finished for - " + driver.getTitle());
    }

    public void waitMilliseconds(int mili) {
        try {
            TimeUnit.MILLISECONDS.sleep(mili);
        } catch (Exception e) {
        }
    }

    public void userNavigateToItem() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"content\"]/div[2]/div[1]/form/div/div[1]/a/img"))).click();
        waitMilliseconds(waitingMil);
    }

    public void userAddItemToHisCart() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"button-cart\"]"))).click();
        waitMilliseconds(waitingMil);
    }

    public void userNavigateToHisCart() {
        driver.get("http://localhost/opencartpro/index.php?route=checkout/cart&language=en-gb");
    }

    public void userApplyCoupon(String couponId) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"accordion\"]/div[1]/h2/button"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"input-coupon\"]"))).sendKeys(couponId);

        WebElement element = driver.findElement(By.xpath("//*[@id=\"form-coupon\"]/div[2]/button"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);

        executor.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//*[@id=\"shopping-cart\"]/div/table/thead/tr/td[1]")));

    }

    public boolean couponAddedToCart(){
        waitMilliseconds(waitingMil);
        WebElement descriptionsTable = driver.findElement(By.xpath("//*[@id=\"checkout-total\"]"));
        List<WebElement> allDescriptions = descriptionsTable.findElements(By.xpath("./child::*"));
        for (WebElement webElement : allDescriptions) {
            if (webElement.findElements(By.xpath("./child::*")).get(0).getText().contains("Coupon")) {
                return true;
            }
        }
        return false;
    }

    public void addItemToCart() {


    }

    public WebElement findSpecificCoupon(String id) {
        waitMilliseconds(waitingMil);
        WebElement tbody = driver.findElement(By.xpath("//*[@id=\"form-coupon\"]/div[1]/table/tbody"));
        List<WebElement> allCoupons = tbody.findElements(By.xpath("./child::*"));
        for (WebElement tr : allCoupons) {
            if (tr.findElements(By.xpath("./child::*")).get(2).getText().equals(id)) {
                return tr;
            }
        }
        return null;
    }

    public void managerCheckCouponExist(String username, String password, String couponId) {
        adminNavigateToCouponsManaging();
        adminLoggingIn(username, password);
        WebElement coupon = findSpecificCoupon(couponId);
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

    public void adminLoggingIn(String username, String password) {
        //----------------------------------------------- Start login --------------------------------------------------------
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"input-username\"]"))).sendKeys(username);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"input-password\"]"))).sendKeys(password);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"form-login\"]/div[3]/button"))).click();
        waitMilliseconds(waitingMil);

    }

    public void adminNavigateToCouponsManaging() {
        driver.get("http://localhost/opencartpro/admin/index.php?route=marketing/coupon");
        waitMilliseconds(waitingMil);
    }

    public void adminSelectTheSpecificCoupon(String couponId) {
        //----------------------------------------------- Delete specific coupon --------------------------------------------------------
        waitMilliseconds(waitingMil);

        WebElement coupon = findSpecificCoupon(couponId);
        if (coupon == null) {
            System.out.println("coupon not found");
            return;
        }
        coupon.findElements(By.xpath("./child::*")).get(0).findElements(By.xpath("./child::*")).get(0).click();
    }

    public void adminDeleteCoupon() {
        //----------------------------------------------- Press delete button --------------------------------------------------------
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"content\"]/div[1]/div/div/button"))).click();
        //----------------------------------------------- Press enter on popup --------------------------------------------------------
        driver.switchTo().alert().accept();
    }

    public void managerDeleteCoupun(String username, String password) {


    }

    public boolean couponAvailableInCheckoutPage() {
        WebElement descriptionsTable = driver.findElement(By.xpath("//*[@id=\"checkout-confirm\"]/div[1]/table/tfoot"));
        List<WebElement> allDescriptions = descriptionsTable.findElements(By.xpath("./child::*"));
        for (WebElement webElement : allDescriptions) {
            if (webElement.findElements(By.xpath("./child::*")).get(0).getText().contains("Coupon")) {
                return true;
            }
        }
        return false;
    }

    public void userClickOnCheckout() {
        waitMilliseconds(waitingMil);
        WebElement element = driver.findElement(By.xpath("//*[@id=\"content\"]/div[3]/div[2]/a"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
        executor.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//*[@id=\"form-register\"]/fieldset[1]/legend")));
    }

    public void couponDeleted(String couponId) {
        if (findSpecificCoupon(couponId)!=null) {
            assert false;
        }
    }

    public void closeWindow(){
        driver.close();
    }
}