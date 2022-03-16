package RestTesting;
import org.junit.Assert;
import org.junit.Test;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class Junit {
	
	@BeforeTest
	public void BT()
	{
		String Name = "Venu";
		String BName = "Vasu";
		Assert.assertEquals(BName, Name);
	}
	
	@Test
	public void testcase1()
	{
		System.out.println("This is my first teestcase1");
	}
	
	@AfterTest
	public void AT()
	{
		System.out.println("This is After test");
	}

}
