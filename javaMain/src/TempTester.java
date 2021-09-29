public class TempTester {
    public static void main(String[] args) {
        DiagramModel diagram = new DiagramModel();
        diagram.addClass("Class1");
        diagram.addClass("Class2");
        diagram.addRelationship("Class1", "Class2", Relationship.RelationshipType.AGGREGATION);
        diagram.ListRelationships();
    }
}
