/* @provengo summon selenium */
var Admin_Username = "admin"
var Admin_Password = "12345"
var couponId = "11111"

story('Add coupon', function () {
    let s = new SeleniumSession().start('http://localhost/opencartpro/admin/index.php?route=marketing/coupon');
    s.adminLoggingIn({username: Admin_Username, password: Admin_Password});
    s.goToCreateCouponPage();
    s.fillData({couponId: couponId});
    s.saveCoupon();
});

story('User use coupon in cart', function () {
    let s = new SeleniumSession().start('http://localhost/opencartpro');
    on(Any("EndOfAction").and(Any({eventName: "saveCoupon"})), function () {
        s.userNavigateToItem();
        s.addItemToCart();
        s.userNavigateToHisCart();
        s.userApplyCoupon({couponId: couponId});
        s.couponAddedToCart();
    });
    on(Any("EndOfAction").and(Any({eventName: "couponDeleted"})), function () {
        s.userClickOnCheckout();
        s.couponNotAvailableInCheckoutPage();
    });
});

story('Admin delete coupon', function () {
    let s = new SeleniumSession().start('http://localhost/opencartpro/admin/index.php?route=marketing/coupon');
    on(Any("EndOfAction").and(Any({eventName: "saveCoupon"})), function () {
        s.adminLoggingIn({username: Admin_Username, password: Admin_Password});
    });
    on(Any("EndOfAction").and(Any({eventName: "userApplyCoupon"})), function () {
        s.adminSelectAndDeleteTheSpecificCoupon({couponId: couponId});
        s.couponDeleted({couponId: couponId});
    });
});

// @provengo summon constraints

// Constraints.block(Any("userNavigateToItem")).until(Any("saveCoupon"));
// Constraints.block(Any("adminLoggingIn")).until(Any("saveCoupon"));
// Constraints.block(Any("userNavigateToItem")).until(Any("saveCoupon"));