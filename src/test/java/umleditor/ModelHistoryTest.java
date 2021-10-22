package umleditor;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

class ModelHistoryTest {
    DiagramModel model;


    @BeforeEach
    void setUp()
    {
        model = new DiagramModel();
    }

    @Test
    @DisplayName("Verifying diagram model copy constructor works properly")
    void testDiagramModelCopy()
    {
        model.addClass("MyClass1");
        model.addClass("MyClass2");
        model.addField("MyClass1", "MyField1");
        model.addField("MyClass1", "MyField2");
        model.addMethod("MyClass2", "MyMethod1");
        model.addParameter("MyClass2", "MyMethod1", "MyParameter");
        model.addRelationship("MyClass1", "MyClass2", Relationship.RelationshipType.AGGREGATION);
        model.addRelationship("MyClass2", "MyClass1", Relationship.RelationshipType.INHERITANCE);

        DiagramModel modelCopy = new DiagramModel(model);

        assertEquals(true, modelCopy.classExists("MyClass1"));
        assertEquals(true, modelCopy.classExists("MyClass2"));
        //ensure relationships exist in model
        //ensure class has fields and methods
        //ensure method has params

        //ensure model changes do not impact each other
    }
}