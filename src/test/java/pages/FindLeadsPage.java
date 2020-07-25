package pages;

import org.openqa.selenium.By;
import hooks.TestNgHooks;

public class FindLeadsPage extends TestNgHooks{
	
	public FindLeadsPage typeFirstName(String data) {
		getDriver().findElement(By.xpath("//span[text()='Name and ID']/following::input[@name='firstName']")).sendKeys(data);
		return this;
	}
	
	public FindLeadsPage clickFindLeadsButton() {
		getDriver().findElement(By.xpath("//button[text()='Find Leads']")).click();
		return this;
	}
}
