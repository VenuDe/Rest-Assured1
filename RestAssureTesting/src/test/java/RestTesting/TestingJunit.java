package RestTesting;

import static org.testng.Assert.assertEquals;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;


import org.testng.annotations.Test;

import com.github.fge.jsonschema.core.keyword.syntax.checkers.helpers.SchemaOrSchemaArraySyntaxChecker;

public class TestingJunit {

	@Before
	public void BT()
	{
		String Name = "Venu";
		String Bname = "Vasu";
		
		Assert.assertEquals(Name, Name);
	}
	
	@Test
	public void testcase1()
	{
		System.out.println("This is my first method");
			
	}
	
	@After
	public void AT()
	{
		System.out.println("This is the After method");
	}
}
