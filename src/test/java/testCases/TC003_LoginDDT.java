package testCases;

import org.junit.Assert;
import org.testng.annotations.Test;

import Utilities.DataProviders;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

/*Data is valid  -Login succes  -test pass  -logout
data is valid  -login failed - testfail
Data is invalid  -login succes  -test fial -logout
Data is invalid   -login failed -test pass*/

public class TC003_LoginDDT extends BaseClass{

	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class, groups="Datadriven") //getting data provider from different class
	public void verify_loginDDT(String email, String pwd, String exp)
	{
		logger.info("****Starting TC003_LoginDDT******");
		try
		{
		//HomePage
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		//LoginPage
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(pwd);
		lp.clickLogin();
		
		//MyAccount
		
		MyAccountPage macc=new MyAccountPage(driver);
		boolean targetPage=macc.isMyAccountPageExists();
		
		if(exp.equalsIgnoreCase("valid"))
		{
			if(targetPage==true)
			{
				macc.clickLogout();
				Assert.assertTrue(true);
				
			}
			else
			{
				Assert.assertTrue(false);
			}
		}
		if(exp.equalsIgnoreCase("invalid"))
		{
			if(targetPage==true)
			{
				macc.clickLogout();
				Assert.assertTrue(false);
			}
			else
			{
				Assert.assertTrue(true);
			}
		}
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("****finished TC003_LoginDDT****");
	
		
	}
}
