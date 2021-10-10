package umleditor;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

class RelationshipTest {

	Relationship Agg;
	Relationship Comp;
	Relationship Inhe;
	Relationship Real;
	UMLClass src;
	UMLClass dest;

	@BeforeEach
	void setUp()
	{
		Agg = new Relationship(src, dest, Relationship.RelationshipType.AGGREGATION);
		Comp = new Relationship(src, dest, Relationship.RelationshipType.COMPOSITION);
		Inhe = new Relationship(src, dest, Relationship.RelationshipType.INHERITANCE);
		Real = new Relationship(src, dest, Relationship.RelationshipType.REALIZATION);
	}
	
	@Test
	@DisplayName("Verifying from")
	void testGetFrom() 
	{
		assertEquals(src, Agg.getFrom(), 
				"From should equal 'src' UMLClass");
		
		assertEquals(src, Comp.getFrom(), 
				"From should equal 'src' UMLClass");
		
		assertEquals(src, Inhe.getFrom(), 
				"From should equal 'src' UMLClass");
		
		assertEquals(src, Real.getFrom(), 
				"From should equal 'src' UMLClass");
	}

	@Test
	@DisplayName("Verifying to")
	void testGetTo() 
	{
		assertEquals(dest, Agg.getTo(), 
				"To should equal 'dest' UMLClass");
		
		assertEquals(dest, Comp.getTo(), 
				"To should equal 'dest' UMLClass");
		
		assertEquals(dest, Inhe.getTo(), 
				"To should equal 'dest' UMLClass");
		
		assertEquals(dest, Real.getTo(), 
				"To should equal 'dest' UMLClass");
	}
	
	@Test
	@DisplayName("Verifying type")
	void testGetRelationshipType() 
	{
		assertEquals(Relationship.RelationshipType.AGGREGATION, Agg.getRelationshipType(), 
				"Type should equal 'AGGREGATION'");
		
		assertEquals(Relationship.RelationshipType.COMPOSITION, Comp.getRelationshipType(), 
				"Type should equal 'COMPOSITION'");
		
		assertEquals(Relationship.RelationshipType.INHERITANCE, Inhe.getRelationshipType(), 
				"Type should equal 'INHERITANCE'");
		
		assertEquals(Relationship.RelationshipType.REALIZATION, Real.getRelationshipType(), 
				"Type should equal 'REALIZATION'");
	}
	
	@Test
	@DisplayName("Changing type of a relationship")
	void testSetType() 
	{
		Agg.setType(Relationship.RelationshipType.COMPOSITION);
		
		assertEquals(Relationship.RelationshipType.COMPOSITION, Agg.getRelationshipType(), 
				"Type should equal 'COMPOSITION'");
		
		Comp.setType(Relationship.RelationshipType.AGGREGATION);
		
		assertEquals(Relationship.RelationshipType.AGGREGATION, Comp.getRelationshipType(), 
				"Type should equal 'AGGREGATION'");
		
		Inhe.setType(Relationship.RelationshipType.COMPOSITION);
		
		assertEquals(Relationship.RelationshipType.COMPOSITION, Inhe.getRelationshipType(), 
				"Type should equal 'COMPOSITION'");
		
		Real.setType(Relationship.RelationshipType.COMPOSITION);
		
		assertEquals(Relationship.RelationshipType.COMPOSITION, Real.getRelationshipType(), 
				"Type should equal 'COMPOSITION'");
	}
}
