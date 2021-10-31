package umleditor;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DiagramModelTest {
	
	private final PrintStream standardOut = System.out;
	private final ByteArrayOutputStream out = new ByteArrayOutputStream();
	
	@BeforeEach
	public void setStreams() 
	{
		System.setOut(new PrintStream(out));
	}
	
	@AfterEach
	public void restoreStreams()
	{
		System.setOut(standardOut);
	}
	
	DiagramModel testDiagram;

	@BeforeEach
	void setUp()
	{
		testDiagram = new DiagramModel();
	}

	@Test
	@DisplayName("Adding class to empty diagram")
	void testaddClassToEmpty() {
		testDiagram.addClass("trial");
		
		assertEquals(1, testDiagram.numberOfClasses(), 
				"Diagram should include only added 'trial' class");
	}
	
	@Test
	@DisplayName("Deleting class from diagram")
	void testDeleteClass() {
		testDiagram.addClass("trial");
		
		assertEquals(1, testDiagram.numberOfClasses(), 
				"Diagram should include only added 'trial' class");
		
		testDiagram.deleteClass("trial");
		
		assertEquals(0, testDiagram.numberOfClasses(),
				"Diagram should be empty");
	}
	
	/**
	@Test
	@DisplayName("listClass output")
	void testListClass() {
		testDiagram.addClass("trial");
		
		assertEquals(1, testDiagram.numberOfClasses(), 
				"Diagram should include only added 'trial' class");
		
		testDiagram.listClass("trial");	
		
		assertEquals("Name: trial\n"
				+ "Fields: \n"
				+ "There are no fields in this class.\n"
				+ "Methods: \n"
				+ "There are no methods in this class.\n", out.toString(),
				"Diagram not listing class properly");
	}
	*/
	
	@Test
	@DisplayName("Adding a method to a class")
	void testAddMethod() {
		testDiagram.addClass("trial");
		
		assertEquals(1, testDiagram.numberOfClasses(), 
				"Diagram should include only added 'trial' class");
		
		testDiagram.addMethod("trial", "testMethod", "testType");
		
		assertEquals(true, testDiagram.methodExists("trial", "testMethod"),
				"testMethod should exist");
	}
	
	@Test
	@DisplayName("Deleting a method from a class")
	void testDeleteMethod() {
		testDiagram.addClass("trial");
		
		assertEquals(1, testDiagram.numberOfClasses(), 
				"Diagram should include only added 'trial' class");
		
		testDiagram.addMethod("trial", "testMethod", "testType");
		
		assertEquals(true, testDiagram.methodExists("trial", "testMethod"),
				"testMethod should exist");
		
		testDiagram.deleteMethod("trial", "testMethod");
		
		assertEquals(false, testDiagram.methodExists("trial", "testMethod"),
				"testMethod shouldn't exist");
	}
	
	@Test
	@DisplayName("Renaming a method in a class")
	void testRenameMethod() {
		testDiagram.addClass("trial");
		
		assertEquals(1, testDiagram.numberOfClasses(), 
				"Diagram should include only added 'trial' class");
		
		testDiagram.addMethod("trial", "testMethod", "testType");
		
		assertEquals(true, testDiagram.methodExists("trial", "testMethod"),
				"testMethod should exist");
		
		testDiagram.renameMethod("trial", "testMethod", "renamedMethod");
		
		assertEquals(false, testDiagram.methodExists("trial", "testMethod"),
				"testMethod shouldn't exist");
		
		assertEquals(true, testDiagram.methodExists("trial", "renamedMethod"),
				"renamedMethod should exist");
	}
	
	@Test
	@DisplayName("Adding a parameter to a method")
	void testAddParameter() {
		testDiagram.addClass("trial");
		
		assertEquals(1, testDiagram.numberOfClasses(), 
				"Diagram should include only added 'trial' class");
		
		testDiagram.addMethod("trial", "testMethod", "testType");
		
		assertEquals(true, testDiagram.methodExists("trial", "testMethod"),
				"testMethod should exist");
		
		testDiagram.addParameter("trial", "testMethod", "testParam", "typeTest");
		UMLClass testClass = testDiagram.getUML("trial");
		Method dummyMethod = testClass.getMethod("testMethod");
		
		assertEquals(true, dummyMethod.parameterExists("testParam"),
				"testParam should exist");
	}
	
	@Test
	@DisplayName("Deleting a parameter from a method")
	void testDeleteParameter() {
		testDiagram.addClass("trial");
		
		assertEquals(1, testDiagram.numberOfClasses(), 
				"Diagram should include only added 'trial' class");
		
		testDiagram.addMethod("trial", "testMethod", "testType");
		
		assertEquals(true, testDiagram.methodExists("trial", "testMethod"),
				"testMethod should exist");
		
		testDiagram.addParameter("trial", "testMethod", "testParam", "typeTest");
		UMLClass testClass = testDiagram.getUML("trial");
		Method dummyMethod = testClass.getMethod("testMethod");
		
		assertEquals(true, dummyMethod.parameterExists("testParam"),
				"testParam should exist");
		
		testDiagram.deleteParameter("trial", "testMethod", "testParam");
		
		assertEquals(false, dummyMethod.parameterExists("testParam"),
				"testParam shouldn't exist");
	}
	
	@Test
	@DisplayName("Renaming a parameter within a method")
	void testRenameParameter() {
		testDiagram.addClass("trial");
		
		assertEquals(1, testDiagram.numberOfClasses(), 
				"Diagram should include only added 'trial' class");
		
		testDiagram.addMethod("trial", "testMethod", "testType");
		
		assertEquals(true, testDiagram.methodExists("trial", "testMethod"),
				"testMethod should exist");
		
		testDiagram.addParameter("trial", "testMethod", "testParam", "typeTest");
		UMLClass testClass = testDiagram.getUML("trial");
		Method dummyMethod = testClass.getMethod("testMethod");
		
		assertEquals(true, dummyMethod.parameterExists("testParam"),
				"testParam should exist");
		
		testDiagram.renameParameter("trial", "testMethod", "testParam", "renamedParam");
		
		assertEquals(false, dummyMethod.parameterExists("testParam"),
				"testParam shouldn't exist");
		
		assertEquals(true, dummyMethod.parameterExists("renamedParam"),
				"renamedParam should exist");
	}
	
	@Test
	@DisplayName("Deleting all parameters from a method")
	void testDeleteAllParameters() {
		testDiagram.addClass("trial");
		
		assertEquals(1, testDiagram.numberOfClasses(), 
				"Diagram should include only added 'trial' class");
		
		testDiagram.addMethod("trial", "testMethod", "testType");
		
		assertEquals(true, testDiagram.methodExists("trial", "testMethod"),
				"testMethod should exist");
		
		testDiagram.addParameter("trial", "testMethod", "testParam", "typeTest");
		UMLClass testClass = testDiagram.getUML("trial");
		Method dummyMethod = testClass.getMethod("testMethod");
		
		assertEquals(true, dummyMethod.parameterExists("testParam"),
				"testParam should exist");
		
		testDiagram.addParameter("trial", "testMethod", "param2", "typeTest");
		
		assertEquals(true, dummyMethod.parameterExists("param2"),
				"param2 should exist");
		
		testDiagram.deleteAllParameters("trial", "testMethod");
		
		assertEquals(false, dummyMethod.parameterExists("testParam"),
				"testParam shouldn't exist");
		
		assertEquals(false, dummyMethod.parameterExists("param2"),
				"param2 shouldn't exist");
	}
	
	@Test
	@DisplayName("Renaming a class")
	void testRenamedClass() {
		testDiagram.addClass("trial");
		
		assertEquals(1, testDiagram.numberOfClasses(), 
				"Diagram should include only added 'trial' class");
		
		testDiagram.renameUMLClass("trial", "renamedClass");
		
		assertEquals(false, testDiagram.classExists("trial"),
				"'trial' should not exist");
		
		assertEquals(true, testDiagram.classExists("renamedClass"),
				"'renamedClass' should exist");
	}
	
	@Test
	@DisplayName("Adding a field")
	void testAddField() {
		testDiagram.addClass("trial");
		
		assertEquals(1, testDiagram.numberOfClasses(), 
				"Diagram should include only added 'trial' class");
		
		testDiagram.addField("trial", "testField", "testType");
		UMLClass testClass = testDiagram.getUML("trial");
		ArrayList<String> fieldTest = new ArrayList<String>();
		fieldTest.add("testField");
		
		assertEquals(fieldTest, testClass.getStringFields(), 
				"Added fields should be in fields arraylist");
	}
	
	@Test
	@DisplayName("Deleting a field")
	void testDeleteField() {
		testDiagram.addClass("trial");
		
		assertEquals(1, testDiagram.numberOfClasses(), 
				"Diagram should include only added 'trial' class");
		
		testDiagram.addField("trial", "testField", "testType");
		UMLClass testClass = testDiagram.getUML("trial");
		ArrayList<String> fieldTest = new ArrayList<String>();
		fieldTest.add("testField");
		
		assertEquals(fieldTest, testClass.getStringFields(), 
				"Added fields should be in fields arraylist");
		
		testDiagram.deleteField("trial", "testField");
		
		fieldTest.remove("testField");
		
		assertEquals(fieldTest, testClass.getStringFields(), 
				"Removed fields shouldn't be in fields arraylist");
	}
	
	@Test
	@DisplayName("Renaming a field")
	void testRenameField() {
		testDiagram.addClass("trial");
		
		assertEquals(1, testDiagram.numberOfClasses(), 
				"Diagram should include only added 'trial' class");
		
		testDiagram.addField("trial", "testField", "testType");
		UMLClass testClass = testDiagram.getUML("trial");
		ArrayList<String> fieldTest = new ArrayList<String>();
		fieldTest.add("testField");
		
		assertEquals(fieldTest, testClass.getStringFields(), 
				"Added fields should be in fields arraylist");
		
		testDiagram.renameField("trial", "testField", "renamedField");
		
		fieldTest.remove("testField");
		fieldTest.add("renamedField");
		
		assertEquals(fieldTest, testClass.getStringFields(), 
				"Renamed fields should be in fields arraylist");
	}
}
