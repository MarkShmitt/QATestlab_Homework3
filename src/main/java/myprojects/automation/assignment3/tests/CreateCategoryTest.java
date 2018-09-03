package myprojects.automation.assignment3.tests;

import myprojects.automation.assignment3.BaseScript;
import myprojects.automation.assignment3.GeneralActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CreateCategoryTest extends BaseScript {
    public static void main(String[] args) throws UnsupportedOperationException {
        // TODO prepare driver object
        String login = "webinar.test@gmail.com";
        String password = "Xcg7299bnSmMuRLp9ITw";
        String categoryName = "shoes";
        WebDriver driver = getConfiguredDriver();
        GeneralActions newBrows = new GeneralActions(driver);
        //login
        newBrows.login(login, password);

        // create category
        newBrows.createCategory(categoryName);

        // check that new category appears in Categories table
        WebElement checkNewCategory = driver.findElement(By.name("categoryFilter_name"));
        checkNewCategory.sendKeys(categoryName);
        checkNewCategory.submit();

        // finish script
        newBrows.waitForContentLoad(categoryName);

        driver.quit();
    }
}
