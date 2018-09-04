package myprojects.automation.assignment3;

import myprojects.automation.assignment3.tests.CreateCategoryTest;
import myprojects.automation.assignment3.utils.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.security.SecureRandom;

/**
 * Contains main script actions that may be used in scripts.
 */
public class GeneralActions {
    private WebDriver driver;
    private WebDriverWait wait;

    public GeneralActions(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 30);
    }
    /**
     * Generates random string
     */
    private static final String ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-_";
    private static final SecureRandom RANDOM = new SecureRandom();

    public static String generate(int count) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < count; ++i) {
                sb.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
            }
            return sb.toString();
        }


    /**
     * Logs in to Admin Panel.
     * @param login
     * @param password
     */
    public void login(String login, String password) {
        // TODO implement logging in to Admin Panel
        driver.get(Properties.getBaseAdminUrl());
        driver.findElement(By.id("email")).sendKeys(login);
        driver.findElement(By.id("passwd")).sendKeys(password);
        driver.findElement(By.name("submitLogin")).click();
        if (login == null || password == null) {
            throw new UnsupportedOperationException();
        }
    }

    /**
     * Adds new category in Admin Panel.
     * @param categoryName
     */
    public void createCategory(String categoryName) {
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.className("page-title"))));
            Actions builder = new Actions(driver);
            builder.moveToElement(driver.findElement(By.id("subtab-AdminCatalog")));
            builder.moveToElement(driver.findElement(By.id("subtab-AdminProducts")));
            builder.moveToElement(driver.findElement(By.id("subtab-AdminCategories")));
            builder.click(driver.findElement(By.id("subtab-AdminCategories"))).perform();

            waitForContentLoad("Women");
            WebElement creatNew = driver.findElement(By.id("page-header-desc-category-new_category"));
            creatNew.click();
            WebElement newName = driver.findElement(By.id("name_1"));
            newName.sendKeys(categoryName);
            WebElement saveBtn = driver.findElement(By.id("category_form_submit_btn"));
            saveBtn.click();
            // TODO implement logic for new category creation
        if (categoryName == null) {
            throw new UnsupportedOperationException();
        }
    }

    /**
     * Waits until page loader disappears from the page
     * @param element
     */
    public void waitForContentLoad(String element) {
        // TODO implement generic method to wait until page content is loaded

        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(".//td[contains(., " + element + ")]"))));
        }
}
