package umleditor;
/**
 * Represents a relationship in the UML class diagram.
 */
public class Relationship {

    /**
     * An enum holding the four relationship types.
     */
    public enum RelationshipType {
        AGGREGATION,
        COMPOSITION,
        INHERITANCE,
        REALIZATION
    }

    /**
     * The "parent" of the relationship.
     */
    UMLClass from; 
    /**
     * The "child" of the relationship.
     */
    UMLClass to; 
    /**
     * The type of the relationship.
     */
    RelationshipType type;

    /**
     * Creates a new relationship.
     * @param from The "parent" of the relationship
     * @param to The "child" of the relationship
     * @param type 
     */
    public Relationship(UMLClass from, UMLClass to, RelationshipType type) {
        this.from = from;
        this.to = to;
        this.type = type;
    }

    /**
     * Returns the "from" class.
     * @return Rhe "from" class.
     */
    public UMLClass getFrom() {
        return this.from;
    }

    /**
     * Returns the "to" class.
     * @return The "to" class.
     */
    public UMLClass getTo() {
        return this.to;
    }

    /**
     * Returns the type of the relationship.
     * @return The type of the relationship.
     */
    public RelationshipType getRelationshipType() {
        return this.type;
    }
    
    /**
     * Returns a String of the current relationship's type.
     * @return A string containing one of {Aggregation|Composition|Inheritance|Realization}, or null if this relationship
     * 	type is null.
     */
    public String getRelationshipTypeString() {
    	if (this.type.equals(RelationshipType.AGGREGATION)) {
    		return "aggregation";
    	} else if (this.type.equals(RelationshipType.COMPOSITION)) {
    		return "composition";
    	} else if (this.type.equals(RelationshipType.INHERITANCE)) {
    		return "inheritance";
    	} else if (this.type.equals(RelationshipType.REALIZATION)) {
    		return "realization";
    	} else {
    		return null;
    	}
    }

    /**
     * Changes the type of the relationship.
     * @param newType The new type of the relationship.
     */
    public void setType(RelationshipType newType) {
        this.type = newType;
    }

    // temporary
    public void printRelationship() {
        System.out.println(from.getName());
        System.out.println(to.getName());
        System.out.println(type);
    }
    
    /**
     * Helper method that returns a value from the RelationshipType enum that matches the input string.
     * @param input The input string.
     * @return The correct RelationshipType enum value if input equals "aggregation", "composition", "inheritance", 
     *  or "realization", null if not.
     */
    public static Relationship.RelationshipType getRelTypeFromString(String input) {
        if (input.equalsIgnoreCase("aggregation")) {
            return Relationship.RelationshipType.AGGREGATION;
        } else if (input.equalsIgnoreCase("composition")) {
            return Relationship.RelationshipType.COMPOSITION;
        } else if (input.equalsIgnoreCase("inheritance")) {
            return Relationship.RelationshipType.INHERITANCE;
        } else if (input.equalsIgnoreCase("realization")) {
            return Relationship.RelationshipType.REALIZATION;
        } else {
            return null;
        }
    }
}
