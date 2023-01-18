import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitions {
    private String ADMIN_USERNAME = "admin";
    private String ADMIN_PASSWORD = "12345";
    private String couponId = "1111";
    private OpenCartActuator user;
    private OpenCartActuator admin;
    private String webDriver = "webdriver.chrome.driver";
    private String path = "C:\\Users\\omer aflalo\\Documents\\אוניברסיטה\\עבודה 3 בהנדסת איכות תוכנה\\assignment-2-software-quality-engineering\\Selenium\\chromedriver.exe";


    public void openCartInit() {
//        new OpenCartActuator(webDriver, path, "").managerCheckCouponExist(ADMIN_USERNAME, ADMIN_PASSWORD,couponId );
//        user = new OpenCartActuator(webDriver, path, "");
//        user.addItemToCart();
//        admin = new OpenCartActuator(webDriver, path);
//        admin.managerDeleteCoupun(ADMIN_USERNAME, ADMIN_PASSWORD, couponId);
//        user.userClickOnCheckout();
    }

    // $$*TODO* explain what this step does$$

    @Given("User in home page")
    public void userInHomePage() {
        user = new OpenCartActuator(webDriver, path);
    }

    @When("User navigate to item")
    public void userNavigateToItem() {
        user.userNavigateToItem();
    }

    @And("User add item to his cart")
    public void userAddItemToHisCart() {
        user.userAddItemToHisCart();
    }

    @And("User navigate to his cart")
    public void userNavigateToHisCart() {
        user.userNavigateToHisCart();
    }

    @And("User apply coupon")
    public void userApplyCoupon() {
        user.userApplyCoupon(couponId);
    }

    @And("User click on checkout")
    public void userClickOnCheckout() {
        user.userClickOnCheckout();
    }

    @Then("Coupon available in checkout Page")
    public void couponAvailableInCheckoutPage() {
        if(!user.couponAvailableInCheckoutPage()){
            System.out.println("error");
            assert false;
        }
    }

    @Given("Admin in home page")
    public void adminInHomePage() {
        admin = new OpenCartActuator(webDriver, path);
    }

    @When("Admin navigate to coupons managing")
    public void adminNavigateToCouponsManaging() {
        admin.adminNavigateToCouponsManaging();
    }

    @And("Admin logging in")
    public void adminLoggingIn() {
        admin.adminLoggingIn(ADMIN_USERNAME, ADMIN_PASSWORD);
    }

    @And("Admin select the specific coupon")
    public void adminSelectTheSpecificCoupon() {
        admin.adminSelectTheSpecificCoupon(couponId);
    }

    @And("Admin delete coupon")
    public void adminDeleteCoupon() {
        admin.adminDeleteCoupon();
    }

    @Then("Coupon deleted")
    public void couponDeleted() {
        admin.couponDeleted(couponId);
    }


    @Then("Coupon not available in checkout Page")
    public void couponNotAvailableInCheckoutPage() {
        if(user.couponAvailableInCheckoutPage()){
            assert false;
        }
    }

    @And("Coupon added to cart")
    public void couponAddedToCart() {
        if(!user.couponAddedToCart()){
            assert false;
        }
    }

    @And("Coupon exist")
    public void couponExist() {
        new OpenCartActuator(webDriver, path).managerCheckCouponExist(ADMIN_USERNAME, ADMIN_PASSWORD,couponId );

    }

    @And("Close user")
    public void closeUser() {
        user.closeWindow();
    }

    @And("Close admin")
    public void closeAdmin() {
        admin.closeWindow();
    }
}
