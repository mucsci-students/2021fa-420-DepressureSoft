package umleditor;

public class Controller {
	DiagramModel holder = new DiagramModel();
	UMLInterface view = new UMLInterface();
	
	/**
	 * Controls access to model based on inputs.
	 * @param input1
	 * @param input2
	 * @param input3
	 * @param input4
	 */
	public void command(String input1, String input2, String input3, String input4, String input5, String input6)
	{
		switch(input1)
		{
		case "add":
			switch(input2)
			{
			case "class":
				holder.addClass(input3);
				break;
			case "field":
				holder.addField(input3, input4);
				break;
			case "method":
				holder.addMethod(input3, input4);
				break;
			case "parameter":
				holder.addParameter(input3, input4, input5);
				break;
			case "relationship":
				holder.addRelationship(input3, input4, getRelTypeFromString(input5));
				break;
			default:
				break;
			}
			break;
			
		case "rename":
			switch(input2)
			{
			case "class":
				holder.renameUMLClass(input3, input4);
				break;
			case "field":
				holder.renameField(input3, input4, input5);
				break;
			case "method":
				holder.renameMethod(input3, input4, input5);
				break;
			case "parameter":
				holder.renameParameter(input3, input4, input5, input6);
				break;
			default:
				break;
			}
			break;
			
		case "delete":
			switch(input2)
			{
			case "class":
				holder.deleteClass(input3);
				break;
			case "field":
				holder.deleteField(input3, input4);
				break;
			case "method":
				holder.deleteMethod(input3, input4);
				break;
			case "parameter":
				switch(input5)
				{
				case "one":
					holder.deleteParameter(input3, input4, input6);
					break;
				case "all":
					holder.deleteAllParameters(input3, input4);
					break;
				}
				break;
			case "relationship":
				holder.deleteRelationship(input3, input4);
				break;
			default:
				break;
			}
			break;
		
		case "changeType":
			holder.changeRelationshipType(input2, input3, getRelTypeFromString(input4));
			break;
			
		case "display":
			switch(input2)
			{
			case "one":
				holder.listClass(input3);
				break;
			case "all":
				holder.listClasses();
				break;
			case "relationships":
				System.out.println("in rels");
				holder.listRelationships();
				break;
			default:
				break;
			}
			break;
		}
	}
	
    /**
     * Helper method that returns a value from the RelationshipType enum that matches the input string.
     * @param input The input string.
     * @return The correct RelationshipType enum value if input equals "aggregation", "composition", "inheritance", 
     *  or "realization", null if not.
     */
    private static Relationship.RelationshipType getRelTypeFromString(String input) {
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
