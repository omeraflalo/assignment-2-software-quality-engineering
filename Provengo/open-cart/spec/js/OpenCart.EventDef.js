/* @Provengo summon selenium */

// Test 1
defineEvent(SeleniumSession, "userNavigateToItem", function (session, e) {
    session.click("//*[@id=\"content\"]/div[2]/div[1]/form/div/div[1]/a/img");
});

defineEvent(SeleniumSession, "addItemToCart", function (session, e) {
    session.click("/html/body/main/div[2]/div/div/div[1]/div[2]/div[1]/form/div/button");
});

defineEvent(SeleniumSession, "userNavigateToHisCart", function (session, e) {
    session.click("/html/body/nav/div/div[2]/ul/li[4]/a");
});

defineEvent(SeleniumSession, "userApplyCoupon", function (session, e) {
    session.click("/html/body/main/div[2]/div/div/div[2]/div[1]/h2/button");
    session.writeText("/html/body/main/div[2]/div/div/div[2]/div[1]/div/div/form/div[1]/div/input", e.couponId);
    session.scrollToBottom("/html/body/main/div[2]/div/div/div[2]/div[1]/div/div/form/div[2]/button");
    session.click("/html/body/main/div[2]/div/div/div[2]/div[1]/div/div/form/div[2]/button");
});

defineEvent(SeleniumSession, "couponAddedToCart", function (session, e) {
    session.assertText("//tfoot[@id='checkout-total']//strong[contains(text(), 'Coupon')]", `Coupon (${couponId})`);
});

defineEvent(SeleniumSession, "userClickOnCheckout", function (session, e) {
    session.click("/html/body/main/div[2]/div/div/div[3]/div[2]/a");
});

defineEvent(SeleniumSession, "couponNotAvailableInCheckoutPage", function (session, e) {
    session.waitForInvisibility(`//table//strong[contains(text(), 'Coupon')]`, 1)
});

// Test 2
defineEvent(SeleniumSession, "adminLoggingIn", function (session, e) {
    session.writeText("/html/body/div/div[2]/div/div/div/div/div[2]/form/div[1]/div/input", e.username);
    session.writeText("/html/body/div/div[2]/div/div/div/div/div[2]/form/div[2]/div[1]/input", e.password);
    session.click("/html/body/div/div[2]/div/div/div/div/div[2]/form/div[3]/button");
});

defineEvent(SeleniumSession, "adminSelectAndDeleteTheSpecificCoupon", function (session, e) {
    session.click(`//table/tbody/tr/td[3][text() = '${couponId}']/parent::tr/td/input`);
    session.runCode("document.querySelector('button[form=\"form-coupon\"]').setAttribute(\"onclick\", \"true\")");
    session.click("/html/body/div/div[2]/div[1]/div/div/button");
});

defineEvent(SeleniumSession, "couponDeleted", function (session, e) {
    session.waitForInvisibility(`//table/tbody/tr/td[3][text() = '${couponId}']/parent::tr/td/input`, 1)
});

defineEvent(SeleniumSession, "goToCreateCouponPage", function (session, e) {
    session.click("/html/body/div/div[2]/div[1]/div/div/a");
});

defineEvent(SeleniumSession, "fillData", function (session, e) {
    session.writeText("/html/body/div/div[2]/div[2]/div/div[2]/form/div/div[1]/div[1]/div/input", e.couponId);
    session.writeText("/html/body/div/div[2]/div[2]/div/div[2]/form/div/div[1]/div[2]/div/input", e.couponId);
    session.writeText("/html/body/div/div[2]/div[2]/div/div[2]/form/div/div[1]/div[4]/div/input", "100");
});

defineEvent(SeleniumSession, "saveCoupon", function (session, e) {
    session.click("/html/body/div/div[2]/div[1]/div/div/button");

});



