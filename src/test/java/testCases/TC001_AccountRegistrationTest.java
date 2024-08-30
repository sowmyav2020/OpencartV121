package testCases;

import org.junit.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;


public class TC001_AccountRegistrationTest extends BaseClass {
	@Test(groups={"Regression","Master"})
	public void verify_account_retistration() throws InterruptedException {
			
		logger.info("*****Starting TC001_AccountRegistrationTest ***");

		try
		{
		HomePage hp= new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clickied on MyAccount Link");
		hp.clickRegister();
		logger.info("Clicked on Register Link");
		
		AccountRegistrationPage regpage=new AccountRegistrationPage(driver);
		logger.info("Providing customer details..");
		regpage.setFirstName(randomeString().toUpperCase());
		regpage.setLastName(randomeString().toUpperCase());
		regpage.setEmail(randomeString()+"@gmail.com");
		regpage.setTelephoneNumber(randomeNumber());
		
		String password=randomeAlphaNumeric();
		regpage.setPassword(password);
		regpage.setConfirmPassword(password);
		
		regpage.setPrivacyPolicy();
		logger.info("clicked on continue");
		regpage.clickContinue();
	
		logger.info("Validating expected message..");
		String confmsg=regpage.getConfirmationMsg();
		
		Assert.assertEquals(confmsg,"Your Account Has Been Created!");
		}
		catch(Exception e)
		{
			logger.error("Test failed");
			logger.debug("Debug logs..");
			Assert.fail();
		}
		
		logger.info("Finished TC001_AccountRegistrationTest");
		}
	}

	
	
	
	
	

