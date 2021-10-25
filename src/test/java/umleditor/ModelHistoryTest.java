package umleditor;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

class ModelHistoryTest {
    DiagramModel model;


    @BeforeEach
    void setUp()
    {
        model = new DiagramModel();
        // Need to clear history between tests, otherwise singleton will persist
        ModelHistory.getInstance().clearHistory();
    }

    @Test
    @DisplayName("Verifying diagram model copy constructor works properly")
    void testDiagramModelCopy()
    {
        model.addClass("MyClass1");
        model.addClass("MyClass2");
        model.addClass("MyClass3");
        model.addField("MyClass1", "MyField1");
        model.addField("MyClass1", "MyField2");
        model.addMethod("MyClass2", "MyMethod1");
        model.addParameter("MyClass2", "MyMethod1", "MyParameter");
        model.addRelationship("MyClass1", "MyClass2", Relationship.RelationshipType.AGGREGATION);
        model.addRelationship("MyClass2", "MyClass1", Relationship.RelationshipType.INHERITANCE);

        DiagramModel modelCopy = new DiagramModel(model);

        //ensure model copy has classes
        assertEquals(true, modelCopy.classExists("MyClass1"));
        assertEquals(true, modelCopy.classExists("MyClass2"));

        //ensure relationships exist in model

        //ensure classes in model copy have copied fields and methods
        //assertEquals(true, modelCopy.fieldExists("MyClass1", "MyField1"));
        //assertEquals(true, modelCopy.fieldExists("MyClass1", "MyField2"));
        assertEquals(true, modelCopy.methodExists("MyClass2", "MyMethod1"));

       //ensure method copy has param

        //ensure model changes do not impact each other
        model.renameUMLClass("MyClass3", "newClassName");
        assertEquals(true, modelCopy.classExists("MyClass3"));
        assertEquals(false, modelCopy.classExists("newClassName"));
 
    }

    @Test
    @DisplayName("Verifying diagram model undo works properly")
    void testDiagramModelUndo()
    {
        model.addClass("MyClass1");
        model.addClass("MyClass2");
        model.addRelationship("MyClass1", "MyClass2", Relationship.RelationshipType.AGGREGATION);

        //ensure model copy has classes
        assertEquals(true, model.classExists("MyClass1"));
        assertEquals(true, model.classExists("MyClass2"));

        //add test to ensure relationship exists
        
        model.undo();

        //ensure relationships does NOT exist

        //ensure MyClass2 was undone and MyClass1 was not
        model.undo();
        assertEquals(false, model.classExists("MyClass2"));
        assertEquals(true, model.classExists("MyClass1"));

        //ensure MyClass1 was undone
        model.undo();
        assertEquals(false, model.classExists("MyClass2"));
        assertEquals(false, model.classExists("MyClass1"));

        //ensure no further undos can be made
        assertEquals(false, model.undo());

    }


    @Test
    @DisplayName("Verifying diagram model redo works properly")
    void testDiagramModelRedo()
    {
        model.addClass("MyClass1");
        model.addClass("MyClass2");
        model.addClass("MyClass3");

        //ensure model copy has classes
        assertEquals(true, model.classExists("MyClass1"));
        assertEquals(true, model.classExists("MyClass2"));
        assertEquals(true, model.classExists("MyClass3"));

        //ensure MyClass3 undone
        model.undo();
        assertEquals(false, model.classExists("MyClass3"));
        assertEquals(true, model.classExists("MyClass2"));
        assertEquals(true, model.classExists("MyClass1"));

        //ensure MyClass3 redone
        model.redo();
        assertEquals(true, model.classExists("MyClass3"));

        //Model should be undone to no classes
        //ensure no classes exist
        model.undo();
        model.undo();
        model.undo();
        assertEquals(false, model.classExists("MyClass3"));
        assertEquals(false, model.classExists("MyClass2"));
        assertEquals(false, model.classExists("MyClass1"));

        //ensure MyClass1 redone
        model.redo();
        assertEquals(true, model.classExists("MyClass1"));

        //ensure MyClass2 redone and MyClass1 remains
        model.redo();
        assertEquals(true, model.classExists("MyClass1"));
        assertEquals(true, model.classExists("MyClass2"));

        //ensure MyClass3 redone and MyClass1 and MyClass2 remain
        model.redo();
        assertEquals(true, model.classExists("MyClass3"));
        assertEquals(true, model.classExists("MyClass2"));
        assertEquals(true, model.classExists("MyClass1"));

        //ensure redo cannot be done after changes made to model
        model.undo();
        model.undo();
        model.addClass("MyClass4");
        assertEquals(false, model.redo());
    }
}