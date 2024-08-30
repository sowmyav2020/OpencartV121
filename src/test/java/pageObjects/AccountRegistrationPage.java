package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage{
	
	public AccountRegistrationPage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement txtFirstName;
	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement txtLastName;
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txtemail;
	@FindBy(xpath="//input[@id='input-telephone']")
	WebElement txtTelephonenumber;
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtPassword;
	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement txtConfirmPassword;
	@FindBy(xpath="//input[@name='agree']")
	WebElement chkdPolicy;
	@FindBy(xpath="//input[@value='Continue']")
	WebElement btnContinue;
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msgConfirmation;
	
	public void setFirstName(String fname)
	{
		txtFirstName.sendKeys(fname);

     }
	public void setLastName(String lname)
	{
		txtLastName.sendKeys(lname);

     }
	public void setEmail(String email)
	{
		 txtemail.sendKeys(email);

     }
	public void setTelephoneNumber(String tel)
	{
		txtTelephonenumber.sendKeys(tel);

     }
	public void setPassword(String password)
	{
		txtPassword.sendKeys(password);

     }
	public void setConfirmPassword(String pwd)
	{
		txtConfirmPassword.sendKeys(pwd);

     }
	public void setPrivacyPolicy()
	{
      chkdPolicy.click();

     }
	public void clickContinue()
	{
		//sol1
		//btnContinue.click();
	
	
	//sol2
	//Actions act=new Actions(driver);
	//act.moveToElement(btnContinue).click();perform();
	
	//sol3
	JavascriptExecutor js=(JavascriptExecutor)driver;
	js.executeScript("arguments[0].click();", btnContinue);
	
	//sol4
	//btnContinue.sendKeys(keys.RETURN);
	
	//sol5
	//WebDriverWait mywait= new WebDriverWait(driver, Duration.ofSeconds(10));
	//mywait.until(ExpectedConditions.elementToBeClickable(btnContinue)).click();
	
	}
	public String getConfirmationMsg()
	{
		try
		{
			return(msgConfirmation.getText());
		}
		catch(Exception e)
		{
			return(e.getMessage());
		}
	}
	
}