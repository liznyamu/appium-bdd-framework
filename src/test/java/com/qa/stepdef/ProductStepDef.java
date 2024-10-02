package com.qa.stepdef;

import com.qa.pages.LoginPage;
import com.qa.pages.ProductDetailsPage;
import com.qa.pages.ProductsPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class ProductStepDef {
    @Given("I'm logged in")
    public void iMLoggedIn() {
        new LoginPage().login("standard_user", "secret_sauce");
    }

    @Then("the product is listed with title {string} and price {string}")
    public void theProductIsListedWithTitleAndPrice(String title, String price) {
        // TODO remove price check here - as it could match with other prices

        // TODO make the ProductsPage().getSLBTitle() and ProductsPage().getSLBPrice(). methods more
        //      generic to allow re-use on multiple products
        ProductsPage productsPage = new ProductsPage();
        Boolean titleCheck = productsPage.getSLBTitle().equalsIgnoreCase(title);
        Boolean priceCheck = productsPage.getSLBPrice().equalsIgnoreCase(price);
        Assert.assertTrue("titleCheck = " + titleCheck + ", priceCheck = " + priceCheck,
                titleCheck & priceCheck);
    }

    @When("I click product title {string}")
    public void iClickProductTitle(String title) {
        new ProductsPage().pressSLBTitle();
    }

    @Then("I should on product details page with title {string}, price {string} and description {string}")
    public void iShouldOnProductDetailsPageWithTitlePriceAndDescription(String title, String price, String description) {
        // TODO make the ProductDetailsPage().getSLBTitle() and ProductDetailsPage().getSLBPrice(). methods more
        //      generic to allow re-use on multiple products]
        ProductDetailsPage productDetailsPage = new ProductDetailsPage();
        Boolean titleCheck = productDetailsPage.getSLBTitle().equalsIgnoreCase(title);
        Boolean priceCheck = productDetailsPage.scrollToSLBPriceAndGetSLBPrice().equalsIgnoreCase(price);
        Boolean descCheck = productDetailsPage.getSLBTxt().equalsIgnoreCase(description);
        Assert.assertTrue("titleCheck = " + titleCheck + ", descCheck = " + descCheck + ", priceCheck = " + priceCheck,
                titleCheck & descCheck & priceCheck);

    }
}
