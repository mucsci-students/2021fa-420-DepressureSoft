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
		testDiagram.addClass("trial");
	}

	@Test
	@DisplayName("Add class to diagram")
	void addClassToEmpty() {
		String output = testDiagram.addClass("class2");
		assertEquals(2, testDiagram.numberOfClasses());
		assertTrue(output == null);
	}

	@Test
	@DisplayName("Undo/redo addClass")
	void addClassUndo() {
		testDiagram.addClass("class2");
		testDiagram.undo();
		assertEquals(1, testDiagram.numberOfClasses());
		testDiagram.redo();
		assertEquals(2, testDiagram.numberOfClasses());
	}

	@Test
	@DisplayName("Adding class with name containing special characters (!@#$%^&*)")
	void addClassSpecialChars() {
		String output = testDiagram.addClass("!@#$%^&*");
		assertTrue(output != null);
		assertEquals(1, testDiagram.numberOfClasses());
	}

	@Test
	@DisplayName("Adding class with name containing spaces")
	void addClassSpaces() {
		String output = testDiagram.addClass("word word");
		assertTrue(output != null);
		assertEquals(1, testDiagram.numberOfClasses());
	}

	@Test
	@DisplayName("Adding class with a duplicate name")
	void addClassDuplicates() {
		String output = testDiagram.addClass("trial");
		assertTrue(output != null);
		assertEquals(1, testDiagram.numberOfClasses());
	}

	@Test
	@DisplayName("Deleting class from diagram")
	void deleteClass() {
		String output = testDiagram.deleteClass("trial");
		assertEquals(0, testDiagram.numberOfClasses());
		assertTrue(output == null);
	}

	@Test
	@DisplayName("Undo/redo delete class")
	void deleteClassUndo() {
		testDiagram.addClass("trial2");
		assertEquals(2, testDiagram.numberOfClasses());
		testDiagram.undo();
		assertEquals(1, testDiagram.numberOfClasses());
		testDiagram.redo();
		assertEquals(2, testDiagram.numberOfClasses());
	}

	@Test
	@DisplayName("Attempting to delete class that doesn't exist")
	void deleteClassNonexistentClass() {
		String output = testDiagram.deleteClass("fizzbuzz");
		assertTrue(output != null);
		assertEquals(1, testDiagram.numberOfClasses());
	}

	@Test
	@DisplayName("Adding a method to a class")
	void addMethod() {

		assertEquals(1, testDiagram.numberOfClasses(),
				"Diagram should include only added 'trial' class");

		String output = testDiagram.addMethod("trial", "testMethod", "testType");

		assertTrue(output == null);

		assertEquals(true, testDiagram.methodExists("trial", "testMethod"),
				"testMethod should exist");
	}

	@Test
	@DisplayName("Undo/redo add method")
	void addMethodUndo() {
		testDiagram.addMethod("trial", "method", "String");
		testDiagram.undo();
		assertFalse(testDiagram.methodExists("trial", "method"));
		testDiagram.redo();
		assertTrue(testDiagram.methodExists("trial", "method"));
	}

	@Test
	@DisplayName("Add method with duplicate name")
	void addMethodDuplicate() {
		testDiagram.addMethod("trial", "method", "void");
		String output = testDiagram.addMethod("trial", "method", "void");
		assertTrue(output != null);
	}

	@Test
	@DisplayName("Add method with invalid name")
	void addMethodBadName() {
		String output = testDiagram.addMethod("trial", "!@#$% ^&", "void");
		assertTrue(output != null);
		assertFalse(testDiagram.methodExists("className", "!@#$% ^&"));
	}

	@Test
	@DisplayName("Add method to nonexistent class")
	void addMethodNoClass() {
			String output = testDiagram.addMethod("nonexistent", "methodName", "void");
			assertTrue(output != null);
	}

	@Test
	@DisplayName("Deleting a method from a class")
	void deleteMethod() {

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
	@DisplayName("Undo/redo delete method")
	void deleteMethodUndo() {
		testDiagram.addMethod("trial", "testMethod", "testType");
		assertTrue(testDiagram.methodExists("trial", "testMethod"));
		testDiagram.undo();
		assertFalse(testDiagram.methodExists("trial", "testMethod"));
		testDiagram.redo();
		assertTrue(testDiagram.methodExists("trial", "testMethod"));
	}

	@Test
	@DisplayName("Delete method from nonexistent class")
	void deleteMethodNoClass() {
		testDiagram.addMethod("trial", "testMethod", "testType");
		String output = testDiagram.deleteMethod("nonexistent", "testMethod:");
		assertTrue(output != null);
	}

	@Test
	@DisplayName("Delete method that doesn't exist")
	void deleteMethodNonexistent() {
		assertFalse(testDiagram.methodExists("trial", "nonexistent"));
		String output = testDiagram.deleteMethod("trial", "nonexistent");
		assertTrue(output != null);
	}

	@Test
	@DisplayName("Renaming a method in a class")
	void renameMethod() {

		assertEquals(1, testDiagram.numberOfClasses(),
				"Diagram should include only added 'trial' class");

		testDiagram.addMethod("trial", "testMethod", "testType");

		assertEquals(true, testDiagram.methodExists("trial", "testMethod"),
				"testMethod should exist");

		String output = testDiagram.renameMethod("trial", "testMethod", "renamedMethod");

		assertTrue(output == null);

		assertEquals(false, testDiagram.methodExists("trial", "testMethod"),
				"testMethod shouldn't exist");

		assertEquals(true, testDiagram.methodExists("trial", "renamedMethod"),
				"renamedMethod should exist");
	}

	@Test
	@DisplayName("Undo/redo rename method")
	void renameMethodUndoRedo() {
		testDiagram.addMethod("trial", "fizz", "void");
		assertTrue(testDiagram.methodExists("trial", "fizz"));
		String output = testDiagram.renameMethod("trial", "fizz", "buzz");
		assertTrue(testDiagram.methodExists("trial", "buzz"));
		assertTrue(output == null);
		testDiagram.undo();
		assertTrue(testDiagram.methodExists("trial", "fizz"));
		testDiagram.redo();
		assertTrue(testDiagram.methodExists("trial", "buzz"));
	}

	@Test
	@DisplayName("Rename method to same name")
	void renameMethodSameName() {
		testDiagram.addMethod("trial", "fizzbuzz", "void");
		assertTrue(testDiagram.methodExists("trial", "fizzbuzz"));
		String output = testDiagram.renameMethod("trial", "fizzbuzz", "fizzbuzz");
		assertTrue(output == null);
		assertTrue(testDiagram.methodExists("trial", "fizzbuzz"));
	}

	@Test
	@DisplayName("Rename method to name that already exists")
	void renameMethodDuplicateName() {
		testDiagram.addMethod("trial", "fizz", "void");
		testDiagram.addMethod("trial", "buzz", "int");
		assertTrue(testDiagram.methodExists("trial", "fizz")
			&& testDiagram.methodExists("trial", "buzz"));
		String output = testDiagram.renameMethod("trial", "fizz", "buzz");
		assertTrue(testDiagram.methodExists("trial", "fizz"));
		assertTrue(output != null);
	}

	@Test
	@DisplayName("Rename method to invalid name")
	void renameMethodInvalidName() {
		testDiagram.addMethod("trial", "fizzbuzz", "void");
		String output = testDiagram.renameMethod("trial", "fizzbuzz", "&^%$%^&*&^%$");
		assertTrue(output != null);
		assertTrue(testDiagram.methodExists("trial", "fizzbuzz"));
		assertFalse(testDiagram.methodExists("trial", "&^%$%^&*&^%$"));
	}

	@Test
	@DisplayName("Rename nonexistent method")
	void renameMethodNonexistent() {
		String output = testDiagram.renameMethod("trial", "bleh", "bleh2");
		assertTrue(output != null);
	}

	@Test
	@DisplayName("Rename method from class that doesn't exist")
	void renameMethodNoClass() {
		testDiagram.addMethod("trial", "methodName", "void");
		String output = testDiagram.renameMethod("bleh", "methodName", "methodName2");
		assertTrue(output != null);
	}

	@Test
	@DisplayName("Adding a parameter to a method")
	void addParameter() {
		testDiagram.addMethod("trial", "testMethod", "testType");

		assertEquals(true, testDiagram.methodExists("trial", "testMethod"),
				"testMethod should exist");

		String output = testDiagram.addParameter("trial", "testMethod", "testParam", "typeTest");
		assertTrue(testDiagram.parameterExists("trial", "testMethod", "testParam"));
		assertTrue(output == null);
	}

	@Test
	@DisplayName("Undo/redo add parameter")
	void addParameterUndo() {
		testDiagram.addMethod("trial", "fizzbuzz", "void");
		String output = testDiagram.addParameter("trial", "fizzbuzz", "param", "int");
		assertTrue(output == null);
		assertTrue(testDiagram.parameterExists("trial", "fizzbuzz", "param"));
		testDiagram.undo();
		assertFalse(testDiagram.parameterExists("trial", "fizzbuzz", "param"));
		testDiagram.redo();
		assertTrue(testDiagram.parameterExists("trial", "fizzbuzz", "param"));
	}

	@Test
	@DisplayName("Add parameter with duplicate name")
	void addParameterDuplicate() {
		testDiagram.addMethod("trial", "fizzbuzz", "void");
	  testDiagram.addParameter("trial", "fizzbuzz", "num", "int");
    String output = testDiagram.addParameter("trial", "fizzbuzz", "num", "String");
		assertTrue(output != null);
	}

	@Test
	@DisplayName("Add parameter to nonexistent method")
	void addParameterNoMethod() {
		String output = testDiagram.addParameter("trial", "nonexistent", "paramName", "String");
		assertTrue(output != null);
	}

	@Test
	@DisplayName("Add parameter within nonexistent class")
	void addParameterNoClass() {
		testDiagram.addMethod("trial", "methodName", "void");
		String output = testDiagram.addParameter("nonexistent", "methodName", "paramName", "String");
		assertTrue(output != null);
	}

	@Test
	@DisplayName("Deleting a parameter from a method")
	void deleteParameter() {
		testDiagram.addMethod("trial", "testMethod", "testType");
		testDiagram.addParameter("trial", "testMethod", "testParam", "typeTest");
		String output = testDiagram.deleteParameter("trial", "testMethod", "testParam");
		assertTrue(output == null);
		assertFalse(testDiagram.parameterExists("trial", "testMethod", "testParam"));
	}

	@Test
	@DisplayName("Undo/redo delete parameter")
	void deleteParameterUndo() {
		testDiagram.addMethod("trial", "fizzbuzz", "string[]");
		testDiagram.addParameter("trial", "fizzbuzz", "num", "int");
		testDiagram.deleteParameter("trial", "fizzbuzz", "num");
		assertFalse(testDiagram.parameterExists("trial", "fizzbuzz", "num"));
		testDiagram.undo();
		assertTrue(testDiagram.parameterExists("trial", "fizzbuzz", "num"));
		testDiagram.redo();
		assertFalse(testDiagram.parameterExists("trial", "fizzbuzz", "num"));
	}

	@Test
	@DisplayName("Delete nonexistent parameter")
	void deleteParameterNonexistent() {
		testDiagram.addMethod("trial", "bruh", "void");
		String output = testDiagram.deleteParameter("trial", "bruh", "nonexistent");
		assertTrue(output != null);
	}

	@Test
	@DisplayName("Renaming a parameter within a method")
	void renameParameter() {

		testDiagram.addMethod("trial", "testMethod", "testType");

		assertEquals(true, testDiagram.methodExists("trial", "testMethod"),
				"testMethod should exist");

		testDiagram.addParameter("trial", "testMethod", "testParam", "typeTest");

		testDiagram.renameParameter("trial", "testMethod", "testParam", "renamedParam");

		assertTrue(testDiagram.parameterExists("trial", "testMethod", "renamedParam"));

		assertFalse(testDiagram.parameterExists("trial", "testMethod", "testParam"));

	}

	@Disabled
	@Test
	@DisplayName("Undo/redo rename parameter")
	void renameParameterUndo() {
		testDiagram.addMethod("trial", "cubeRoot", "double");
		testDiagram.addParameter("trial", "cubeRoot", "num", "double");
		testDiagram.renameParameter("trial", "cubeRoot", "num", "n");
		assertTrue(testDiagram.parameterExists("trial", "cubeRoot", "n"));
		testDiagram.undo();
		assertTrue(testDiagram.parameterExists("trial", "cubeRoot", "num"));
		testDiagram.redo();
		assertTrue(testDiagram.parameterExists("trial", "cubeRoot", "n"));
	}

	@Test
	@DisplayName("Deleting all parameters from a method")
	void deleteAllParameters() {
		testDiagram.addMethod("trial", "sum", "int");
		testDiagram.addParameter("trial", "sum", "n1", "int");
		testDiagram.addParameter("trial", "sum", "n2", "int");
		assertTrue(testDiagram.parameterExists("trial", "sum", "n1")
			&& testDiagram.parameterExists("trial", "sum", "n2"));
		testDiagram.deleteAllParameters("trial", "sum");
		assertFalse(testDiagram.parameterExists("trial", "sum", "n1")
			|| testDiagram.parameterExists("trial", "sum", "n2"));
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
