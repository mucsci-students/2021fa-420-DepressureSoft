package umleditor;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UMLClassTestv3 {
	
	UMLClass testClass;

	@BeforeEach
	void setUp()
	{
		testClass = new UMLClass("testClass");
	}
	
	@Test
	@DisplayName("Verifying name")
	void testName() 
	{
		assertEquals("testClass", testClass.getName(), 
				"Name should equal 'testClass'");
	}
	
	@Test
	@DisplayName("Checking rename")
	void testRename() 
	{
		testClass.renameClass("changedClassName");
		
		assertEquals("changedClassName", testClass.getName(), 
				"Name should equal 'changedClassName'");
	}
	
	@Test
	@DisplayName("No fields when constructed")
	void testGetFields() 
	{
		ArrayList<String> fieldTest = new ArrayList<String>();
		
		assertEquals(fieldTest, testClass.getFields(), 
				"Fields arraylist should be empty");
	}
	
	@Test
	@DisplayName("Added fields should appear in class")
	void testAddField() 
	{		
		testClass.addField("fieldA", "typeA");
		testClass.addField("fieldB", "typeB");
		
		ArrayList<String> fieldTest = new ArrayList<String>();
		fieldTest.add("fieldA", "typeA");
		fieldTest.add("fieldB", "typeB");
		
		assertEquals(fieldTest, testClass.getFields(), 
				"Added fields should be in fields arraylist");
	}
	
	@Test
	@DisplayName("Removed fields should disappear from class")
	void testRemoveField() 
	{		
		testClass.addField("fieldA", "typeA");
		testClass.addField("fieldB", "typeB");
		testClass.removeField("fieldA");
		
		ArrayList<String> fieldTest = new ArrayList<String>();
		fieldTest.add("fieldB");
		
		assertEquals(fieldTest, testClass.getFields(), 
				"Removed fields should not be in fields arraylist");
	}
	
	@Test
	@DisplayName("Renamed fields should be renamed in the class")
	void testRenameField() 
	{		
		testClass.addField("fieldA", "typeA");
		testClass.addField("fieldB", "typeB");
		testClass.renameField("fieldA", "renamedField");
		
		ArrayList<String> fieldTest = new ArrayList<String>();
		fieldTest.add("renamedField");
		fieldTest.add("fieldB");
		
		assertEquals(fieldTest, testClass.getFields(), 
				"Renamed fields should be updated in fields arraylist");
	}

}