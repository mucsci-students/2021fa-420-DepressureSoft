package umleditor;

import java.util.ArrayList;

public class Controller {
	
	private static final String ADD_HELP = "ADD: Adds a class, method, field, parameter, or relationship to the diagram.\nadd class <class>\nadd method <class> <method>\nadd field <class> <field>\nadd parameter <class> <method> <parameter>\nadd relationship <source_class> <destination_class> {aggregation|composition|inheritance|realization}";
	private static final String DELETE_HELP = "DELETE: Deletes a class, method, field, parameter, or relationship from the diagram.\ndelete class <class>\ndelete method <class> <method>\ndelete field <class> <field>\ndelete parameter <class> <method> <parameter>\ndelete relationship <source_class> <destination_class>";
	private static final String RENAME_HELP = "RENAME: Renames a class, method, field, or parameter.\nrename class <class> <new_name>\nrename method <class> <method> <new_name>\nrename field <class> <field> <new_name>\nrename parameter <class> <method> <parameter> <new_name>";
	private static final String SAVE_HELP = "SAVE: Saves the current class diagram to a json file. Directory and file name must exist. \".json\" extension is optional and will be appended automatically if not included in file name.\nsave <directory> <file_name>";
	private static final String LOAD_HELP = "LOAD: Loads a class diagram from a json file. File path must exist.\nload <file_path>";
	private static final String CHANGETYPE_HELP = "CHANGETYPE: Changes the type of a field, method, parameter, or relationship.\nchangetype field <class> <field> <new_type>\nchangetype method <class> <method> <type> <new_type>\nchangetype relationship <source_class> <destination_class> {aggregation|composition|inheritance|realization}";
	private static final String DISPLAY_HELP = "DISPLAY: Displays the class diagram in various ways.\ndisplay class <classname>\ndisplay {all|relationships}";
	private static final String UNDO_HELP = "UNDO: Reverts to the program state before the most recent change.\nundo";
	private static final String REDO_HELP = "REDO: If undo has been called, reverts to the state of the program before change was undone.\nundo";
	private static final String HELP_MENU = "----------| HELP MENU |----------\n"
			+ "Type help <command> for additional information about each command.\n"
			+ "add {class|method|field|parameter|relationship} \n"
			+ "delete {class|method|field|parameter|relationship}\n"
			+ "rename {class|method|field|parameter|relationship}\n"
			+ "save <file_path>\n"
			+ "load <file_path>\n"
			+ "changetype {field|method|relationship}\n"
			+ "display {class|all|relationships}\n"
			+ "undo\n"
			+ "redo\n";
	
	public static void main(String[] args) {
		DiagramModel model = new DiagramModel();
		UMLInterface view = new UMLInterface();
		view.displayWelcome();
		boolean userInputLoop = true;
		ArrayList<String> commands;
		while(userInputLoop) {
			commands = view.getInput("Command > ");
			try {
				String m = "";
				if(checkKeyword(commands, 0, "exit")) {
					userInputLoop = false;
				} else if(checkKeyword(commands, 0, "add")) {
					if(checkKeyword(commands, 1, "class")) {
						if (commands.size() > 2) {
							m = model.addClass(commands.get(2));
							if (m == null) m = "Added class.";
						} else {
							view.print("Class name required.");
						}
					} else if(checkKeyword(commands, 1, "method")) {
						if (commands.size() > 3) {
							m = model.addMethod(commands.get(2), commands.get(3));
							if (m == null) m = "Added method.";
						} else {
							view.print("Class and method names required.");
						}
					} else if(checkKeyword(commands, 1, "field")) {
						if (commands.size() > 3) {
							m = model.addField(commands.get(2), commands.get(3));
							if (m == null) m = "Added field.";
						} else {
							view.print("Class and field names required.");
						}
					} else if(checkKeyword(commands, 1, "parameter")) {
						if(commands.size() > 4) {
							m = model.addParameter(commands.get(2), commands.get(3), commands.get(4));
							if (m == null) m = "Added parameter.";
						} else {
							view.print("Class, method, and parameter names required.");
						}
					} else if(checkKeyword(commands, 1, "relationship")) {
						if(commands.size() == 4) {
							view.print("Please specify a relationship type. {aggregation|composition|inheritance|realization}");
							ArrayList<String> typeInput;
							typeInput = view.getInput("Relationship Type > ");
							m = model.addRelationship(commands.get(2), commands.get(3), getRelTypeFromString(typeInput.get(0)));
							if (m == null) m = "Added relationship.";
						} else if(commands.size() > 4) {
							m = model.addRelationship(commands.get(2), commands.get(3), getRelTypeFromString(commands.get(4)));
							if (m == null) m = "Added relationship.";
						} else {
							view.print("Source class and destination class names required.");
						}
					} else {
						view.print("Unrecognized command. Type \"help add\" for more info.");
					}	
				} else if(checkKeyword(commands, 0, "delete")) {
					if(checkKeyword(commands, 1, "class")) {
						if(commands.size() > 2) {
							m = model.deleteClass(commands.get(2));
							if (m == null) m = "Deleted class.";
						} else {
							view.print("Class name required.");
						}
					} else if(checkKeyword(commands, 1, "field")) {
						if(commands.size() > 3) {
							m = model.deleteField(commands.get(2), commands.get(3));
							if (m == null) m = "Deleted field.";
						} else {
							view.print("Class and field name required.");
						}
					} else if(checkKeyword(commands, 1, "method")) {
						if(commands.size() > 3) {
							m = model.deleteMethod(commands.get(2), commands.get(3));
							if (m == null) m = "Deleted method.";
						} else {
							view.print("Class and method name required.");
						}
					} else if(checkKeyword(commands, 1, "parameter")) {
						if(commands.size() > 4) {
							m = model.deleteParameter(commands.get(2), commands.get(3), commands.get(4));
							if (m == null) m = "Deleted parameter.";
						} else {
							view.print("Class, method, and parameter names required.");
						}
					} else if(checkKeyword(commands, 1, "relationship")) {
						if(commands.size() > 3) {
							m = model.deleteRelationship(commands.get(2), commands.get(3));
							if (m == null) m = "Deleted relationship.";
						} else {
							view.print("Source and destination class names required.");
						}
					} else {
						view.print("Unrecognized command. Type \"help delete\" for more info.");
					}
				} else if(checkKeyword(commands, 0, "rename")) {
					if(checkKeyword(commands, 1, "class")) {
						if (commands.size() > 3) {
							m = model.renameUMLClass(commands.get(2), commands.get(3));
							if (m == null) m = "Renamed class.";
						} else {
							view.print("Class name and new name required.");
						}
					} else if(checkKeyword(commands, 1, "method")) {
						if (commands.size() > 4) {
							m = model.renameMethod(commands.get(2), commands.get(3), commands.get(4));
							if (m == null) m = "Renamed method.";
						} else {
							view.print("Class name, method name, and new method name required.");
						}
					} else if(checkKeyword(commands, 1, "field")) {
						if (commands.size() > 4) {
							m = model.renameField(commands.get(2), commands.get(3), commands.get(4));
							if (m == null) m = "Renamed field.";
						} else {
							view.print("Class name, field name, and new field name required.");
						}
					} else if(checkKeyword(commands, 1, "parameter")) {
						if(commands.size() > 5) {
							m = model.renameParameter(commands.get(2), commands.get(3), commands.get(4), commands.get(5));
							if (m == null) m = "Renamed parameter.";
						} else {
							view.print("Class name, method name, parameter name, and new name required.");
						}
					} else {
						view.print("Unrecognized command. Type \"help rename\" for more info.");
					}
				} else if(checkKeyword(commands, 0, "save")) {
					if(commands.size() > 2) {
						m = model.save(commands.get(1) + commands.get(2));
						if (m == null) m = ("Saved successfully to " + commands.get(1) + commands.get(2));
					} else {
						view.print("Save requires a directory and file name. Type \"help save\" for more info.");
					}
				} else if (checkKeyword(commands, 0, "load")) {
					if(commands.size() > 1) {
						if(UMLInterface.yesNoDialog("Are you sure you want to load? All unsaved changes will be lost.")) {
							m = model.load(commands.get(1));
							if (m == null) m = "File loaded successfully.";
							view.print(model.listClasses());
						}
					} else {
						view.print("Load requires a file path to load the file from. Type \"help load\" for more info.");
					}
				} else if (checkKeyword(commands, 0, "changetype")) {
					view.print("Types are not yet implemented. Check back later!");
				} else if(checkKeyword(commands, 0, "display")) {
					if(checkKeyword(commands, 1, "all")) {
						view.print(model.listClasses());
					} else if(checkKeyword(commands, 1, "class")) {
						if(commands.size() > 2) {
							view.print(model.listClass(commands.get(2)));
						} else {
							view.print("Class name required.");
						}
					} else if(checkKeyword(commands, 1, "relationships")) {
						view.print(model.listRelationships());
					} else {
						view.print("Unrecognized command. Type \"help display\" for more info.");
					}

				} else if(checkKeyword(commands, 0, "undo")) {
					if (model.undo()){
						view.print("Most recent change was undone.");
					} else {
						view.print("Undo could not be completed.");
					}
				} else if (checkKeyword(commands, 0, "redo")) {
					if (model.redo()){
						view.print("Most recent change was redone.");
					} else {
						view.print("Redo could not be completed.");
					}

				} else if(checkKeyword(commands, 0, "help")) {
					if(checkKeyword(commands, 1, "add")) {
						view.print(ADD_HELP);
					} else if(checkKeyword(commands, 1, "delete")) {
						view.print(DELETE_HELP);
					} else if(checkKeyword(commands, 1, "rename")) {
						view.print(RENAME_HELP);
					} else if(checkKeyword(commands, 1, "save")) {
						view.print(SAVE_HELP);
					} else if(checkKeyword(commands, 1, "load")) {
						view.print(LOAD_HELP);
					} else if(checkKeyword(commands, 1, "changetype")) {
						view.print(CHANGETYPE_HELP);
					} else if(checkKeyword(commands, 1, "display")) {
						view.print(DISPLAY_HELP);
					} else if(checkKeyword(commands, 1, "undo")){
						view.print(UNDO_HELP);
					} else if (checkKeyword(commands, 1, "redo")){
						view.print(REDO_HELP);
					} else {
						view.print(HELP_MENU);
					}
					
				} else {
					view.print("Unrecognized command. Type \"help\" to view commands.");
				}
				
				if (!m.equals("")) view.print(m);
				
			}
			catch (Exception error) {
				System.out.println(error.getMessage());
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
     * Helper method that returns a value from the RelationshipType enum that matches the input string.
     * @param input The input string.
     * @return The correct RelationshipType enum value if input equals "aggregation", "composition", "inheritance", 
     *  or "realization", null if not.
     */
    private static Relationship.RelationshipType getRelTypeFromString(String input) throws Exception {
        if (input.equalsIgnoreCase("aggregation")) {
            return Relationship.RelationshipType.AGGREGATION;
        } else if (input.equalsIgnoreCase("composition")) {
            return Relationship.RelationshipType.COMPOSITION;
        } else if (input.equalsIgnoreCase("inheritance")) {
            return Relationship.RelationshipType.INHERITANCE;
        } else if (input.equalsIgnoreCase("realization")) {
            return Relationship.RelationshipType.REALIZATION;
        } else {
            throw new Exception(input + " is not a valid relationship type.");
        }
    }
}
