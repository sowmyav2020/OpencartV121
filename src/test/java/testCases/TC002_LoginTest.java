package testCases;

import org.junit.Assert;
import org.testng.annotations.Test;


import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass{

	@Test(groups={"Sanity", "Master"})
	public void verity_Login()
	{
		logger.info("*****Starting TC002_LoginTest****");
		try
		{
		//Homepage
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		//LoginPage
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		lp.clickLogin();
		
		Thread.sleep(3000);
		//MyAccount
		
		MyAccountPage macc=new MyAccountPage(driver);
		boolean targetPage=macc.isMyAccountPageExists();
	    Assert.assertTrue(targetPage);
	}
	catch(Exception e)
	{
		Assert.fail();
	    	
	}
		logger.info("******FInished TC002_LoginTest****");
	}
}

