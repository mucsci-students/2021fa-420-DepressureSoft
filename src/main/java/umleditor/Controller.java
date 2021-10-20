package umleditor;

import java.util.ArrayList;

public class Controller {
	public static void main(String[] args) {
		DiagramModel model = new DiagramModel();
		UMLInterface view = new UMLInterface();
		view.displayWelcome();
		boolean userInputLoop = true;
		ArrayList<String> commands;
		while(userInputLoop) {
			commands = view.getInput();
			try {
				if(checkKeyword(commands, 0, "exit")) {
					userInputLoop = false;
				} else if(checkKeyword(commands, 0, "add")) {
					if(checkKeyword(commands, 1, "class")) {
						if (commands.size() > 2) {
							model.addClass(commands.get(2));
							view.print("Class added successfully.");
						} else {
							view.print("Class name required.");
						}
					} else if(checkKeyword(commands, 1, "method")) {
						if (commands.size() > 3) {
							model.addMethod(commands.get(2), commands.get(3));
							view.print("Method added successfully.");
						} else {
							view.print("Class and method names required.");
						}
					} else {
						view.print("Unrecognized command.");
					}
				} else if(checkKeyword(commands, 0, "display")) {
					if(checkKeyword(commands, 1, "all")) {
						model.listClasses();
					} else {
						view.print("Valid commands: \"display class [classname]\", "
								+ "\"display all\", or \"display relationships\".");
					}
				} else {
					view.print("Unrecognized command.");
				}
			}
			catch (Exception error) {
				System.out.println(error.toString());
			}
		}
	}
	
	
	/**
	 * Checks that a string in a list of strings exists at the specified index.
	 * @param index The index of the list to look at.
	 * @param keyword The string to compare with the string at the index.
	 * @return If the string in the list matches "keyword", returns true, returns false otherwise.
	 */
	private static boolean checkKeyword(ArrayList<String> commandList, int index, String keyword) {
		return (commandList.size() > index) && (commandList.get(index).equalsIgnoreCase(keyword));
	}
	
	/**
	 * Controls access to model based on inputs.
	 * @param input1
	 * @param input2
	 * @param input3
	 * @param input4
	 */
	/*
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
		case "save":
			holder.save(input2, input3);
			break;
		case "load":
			holder.load(input2);
			break;
		}
	}
	*/
	
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
