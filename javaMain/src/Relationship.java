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
}
