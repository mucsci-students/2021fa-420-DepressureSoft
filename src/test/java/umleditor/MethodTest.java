package umleditor;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;


class MethodTest {
	
	Method testMethod;
	
	@BeforeEach
	void setUp()
	{
		testMethod = new Method("testMethod", "testType");
	}

	@Test
	@DisplayName("Verifying name")
	void testGetMethodName() 
	{
		assertEquals("testMethod", testMethod.getMethodName(), 
				"Name should equal 'testMethod'");
	}
	
	@Test
	@DisplayName("Renaming method")
	void testRenameMethod() 
	{
		testMethod.renameMethod("renamedMethod");
		
		assertEquals("renamedMethod", testMethod.getMethodName(), 
				"Name should equal 'renamedMethod'");
	}
	
	@Test
	@DisplayName("Adding a parameter to a method")
	void testAddParam() 
	{
		testMethod.addParameter("testParam", "testParamType");
		
		assertEquals(true, testMethod.parameterExists("testParam"), 
				"'testParam' should exist");
	}
	
	@Test
	@DisplayName("Removing a parameter from a method")
	void testRemoveParam() 
	{
		testMethod.addParameter("testParam", "testParamType");
		
		assertEquals(true, testMethod.parameterExists("testParam"), 
				"'testParam' should exist");
		
		testMethod.removeParameter("testParam");
		
		assertEquals(false, testMethod.parameterExists("testParam"), 
				"'testParam' shouldn't exist");
	}
	
	@Test
	@DisplayName("Renaming a parameter in a method")
	void testRenameParam() 
	{
		testMethod.addParameter("testParam", "testParamType");
		
		assertEquals(true, testMethod.parameterExists("testParam"), 
				"'testParam' should exist");
		
		testMethod.renameParameter("testParam", "renamedParam");
		
		assertEquals(false, testMethod.parameterExists("testParam"), 
				"'testParam' shouldn't exist");
		assertEquals(true, testMethod.parameterExists("renamedParam"),
				"'renamedParam' should exist");
	}
	
	@Test
	@DisplayName("Removing all parameters from a method")
	void testRemoveAllParam() 
	{
		testMethod.addParameter("testParam", "testParamType");
		
		assertEquals(true, testMethod.parameterExists("testParam"), 
				"'testParam' should exist");
		
		testMethod.addParameter("testParam2", "testParamType2");
		
		assertEquals(true, testMethod.parameterExists("testParam2"), 
				"'testParam2' should exist");
		
		testMethod.removeAllParameters();
		
		assertEquals(false, testMethod.parameterExists("testParam"), 
				"'testParam' shouldn't exist");
		assertEquals(false, testMethod.parameterExists("testParam2"), 
				"'testParam2' shouldn't exist");
	}
}
