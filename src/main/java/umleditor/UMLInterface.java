package umleditor;
import java.util.Scanner;

/**
 * Public Interface responsible for connecting all programmed methods to the Terminal. 
 * Allows for user to modify diagrams as they please. 
 */
public class UMLInterface {
    public static void main(String[] args){
        DiagramModel holder = new DiagramModel();
        Scanner sc = new Scanner(System.in);
        String userEntry = "";
        String userEntry2 = "";
        String userEntry3 = "";
        String userEntry4 = "";

        System.out.println("Welcome to the text UML Diagram creator. To begin please select the action you would like to perform.");
        System.out.println("If you're new please use the 'help' command to see the options avaliable.");
        while(!userEntry.equals("Exit"))
        {
            System.out.println("Please input your command.");
            userEntry = sc.nextLine();
            userEntry = userEntry.trim();
            if(userEntry.equalsIgnoreCase("Add"))
            {
                System.out.println("Would you like to add a class, method, field, parameter, or relationship?");
                userEntry = sc.nextLine();
                userEntry = userEntry.trim();
                if(userEntry.equalsIgnoreCase("Class"))
                {
                    System.out.println("Please enter the name of the class you would like to add.");
                    userEntry = sc.nextLine();
                    userEntry = userEntry.trim();
                    holder.addClass(userEntry);
                }
                else if(userEntry.equalsIgnoreCase("Field"))
                {
                    System.out.println("Please enter the name of the class you would like to add the field to.");
                    userEntry = sc.nextLine();
                    userEntry = userEntry.trim();
                    boolean moreFields = true;
                    while(moreFields)
                    {
                        System.out.println("Please enter the name of the field you would like to add.");
                        userEntry2 = sc.nextLine();
                        userEntry2 = userEntry2.trim();
                        holder.addField(userEntry, userEntry2);
                        System.out.println("Would you like to add another field to the class? 'yes' or 'no'");
                        userEntry3 = sc.nextLine();
                        userEntry3 = userEntry3.trim();
                        if(userEntry3.equalsIgnoreCase("No"))
                        {
                            moreFields = false;
                        }
                    }
                }
                else if(userEntry.equalsIgnoreCase("Method"))
                {
                    System.out.println("Please enter the name of the class you would like to add the method to.");
                    userEntry = sc.nextLine();
                    userEntry = userEntry.trim();
                  
                    System.out.println("Please enter the name of the method you would like to add.");
                    userEntry2 = sc.nextLine();
                    userEntry2 = userEntry2.trim();
                     //   holder.addMethod(userEntry, userEntry2);
                    
                }
                else if(userEntry.equalsIgnoreCase("Parameter"))
                {
                    System.out.println("Please enter the name of the class you would like to add the parameter to.");
                    userEntry = sc.nextLine();
                    userEntry = userEntry.trim();
                    
                    System.out.println("Please enter the name of the method you would like to add the parameter to.");
                    userEntry2 = sc.nextLine();
                    userEntry2 = userEntry2.trim();

                    System.out.println("Please enter the name of the parameter you would like to add.");
                    userEntry3 = sc.nextLine();
                    userEntry3 = userEntry3.trim();
                     //   holder.addParameter(userEntry, userEntry2,userEntry3);
                    
                }
                else if(userEntry.equalsIgnoreCase("Relationship"))
                {
                    System.out.println("Please enter the first class within the relationship you would like to add.");
                    userEntry = sc.nextLine();
                    userEntry = userEntry.trim();
                    System.out.println("Please enter the second class within the relationship you would like to add.");
                    userEntry2 = sc.nextLine();
                    userEntry2 = userEntry2.trim();
                    System.out.println("Please enter the type of relationship between the two classes."
                        + "\nCan be either \"aggregation\", \"composition\", \"inheritance\", or \"realization\".");
                    userEntry3 = sc.nextLine();
                    userEntry3 = userEntry3.trim();
                    if (getRelTypeFromString(userEntry3) != null) {
                        holder.addRelationship(userEntry,userEntry2,getRelTypeFromString(userEntry3));
                    } else {
                        System.out.println("\"" + userEntry3 + "\" is not a valid relationship type.");
                    }
                }
                else if(userEntry.equalsIgnoreCase("Exit"))
                {
                    System.out.println("Are you sure you would like to Exit? Y/N");
                    userEntry = sc.nextLine();
                    if(userEntry.equalsIgnoreCase("Y"))
                    break;
                }
                else{
                    System.out.println("Sorry, we don't recognize that command, please try again.");
                }
            }
            else if(userEntry.equalsIgnoreCase("Rename"))
            {
                System.out.println("Would you like to rename a class, method, field, or parameter?");
                userEntry = sc.nextLine();
                userEntry = userEntry.trim();
                if(userEntry.equalsIgnoreCase("Class"))
                {
                    System.out.println("Please enter the name of the class you would like to rename.");
                    userEntry = sc.nextLine();
                    userEntry = userEntry.trim();
                    System.out.println("Please enter what you want to rename it to.");
                    userEntry2 = sc.nextLine();
                    userEntry2 = userEntry2.trim();
                    holder.renameUMLClass(userEntry,userEntry2);
                }
                else if(userEntry.equalsIgnoreCase("Field"))
                {
                    System.out.println("Please enter the name of the class where the field is located.");
                    userEntry = sc.nextLine();
                    userEntry = userEntry.trim();
                    System.out.println("Please enter the name of the field you would like to rename.");
                    userEntry2 = sc.nextLine();
                    userEntry2 = userEntry2.trim();
                    System.out.println("Please enter what you want to rename it to.");
                    userEntry3 = sc.nextLine();
                    userEntry3 = userEntry3.trim();
                    holder.renameField(userEntry, userEntry2, userEntry3);
                }
                else if(userEntry.equalsIgnoreCase("Method"))
                {
                    System.out.println("Please enter the name of the class where the method is located.");
                    userEntry = sc.nextLine();
                    userEntry = userEntry.trim();
                  
                    System.out.println("Please enter the name of the method you would like to rename.");
                    userEntry2 = sc.nextLine();
                    userEntry2 = userEntry2.trim();

                    System.out.println("Please enter what you want to rename it to.");
                    userEntry3 = sc.nextLine();
                    userEntry3 = userEntry3.trim();
                     //   holder.renameMethod(userEntry, userEntry2,userEntry3);
                    
                }
                else if(userEntry.equalsIgnoreCase("Parameter"))
                {
                    System.out.println("Please enter the name of the class where the parameter is located.");
                    userEntry = sc.nextLine();
                    userEntry = userEntry.trim();
                  
                    System.out.println("Please enter the name of the method where the parameter is located.");
                    userEntry2 = sc.nextLine();
                    userEntry2 = userEntry2.trim();

                    System.out.println("Please enter the name of the parameter you would like to rename.");
                    userEntry3 = sc.nextLine();
                    userEntry3 = userEntry3.trim();

                    System.out.println("Please enter what you want to rename it to.");
                    userEntry4 = sc.nextLine();
                    userEntry4 = userEntry4.trim();
                     //   holder.renameParameter(userEntry,userEntry2,userEntry3,userEntry4);
                    
                }
                else if(userEntry.equalsIgnoreCase("Exit"))
                {
                    System.out.println("Are you sure you would like to Exit? Y/N");
                    userEntry = sc.nextLine();
                    if(userEntry.equalsIgnoreCase("Y"))
                    break;
                }
                else{
                    System.out.println("Sorry, we don't recognize that command, please try again.");
                }
            }
            else if(userEntry.equalsIgnoreCase("Delete"))
            {
                System.out.println("Would you like to delete a class, method, parameter, field, or relationship?");
                userEntry = sc.nextLine();
                userEntry = userEntry.trim();
                if(userEntry.equalsIgnoreCase("Class"))
                {
                    System.out.println("Please enter the name of the class you would like to remove.");
                    userEntry = sc.nextLine();
                    userEntry = userEntry.trim();
                    holder.deleteClass(userEntry);
                }
                else if(userEntry.equalsIgnoreCase("Field"))
                {
                    System.out.println("Please enter the name of the class where the field is located.");
                    userEntry = sc.nextLine();
                    userEntry = userEntry.trim();
                    System.out.println("Please enter the name of the field you would like to remove.");
                    userEntry2 = sc.nextLine();


                    userEntry2 = userEntry2.trim();
                    holder.deleteField(userEntry, userEntry2);
                }
                else if(userEntry.equalsIgnoreCase("Method"))
                {
                    System.out.println("Please enter the name of the class you would like to delete the method from.");
                    userEntry = sc.nextLine();
                    userEntry = userEntry.trim();
                  
                    System.out.println("Please enter the name of the method you would like to remove.");
                    userEntry2 = sc.nextLine();
                    userEntry2 = userEntry2.trim();
                     //   holder.deleteMethod(userEntry, userEntry2);
                    
                }
                else if(userEntry.equalsIgnoreCase("Parameter"))
                {
                    System.out.println("Please enter the name of the class you would like to remove the parameter from.");
                    userEntry = sc.nextLine();
                    userEntry = userEntry.trim();
                    
                    System.out.println("Please enter the name of the method you would like to remove the parameter from.");
                    userEntry2 = sc.nextLine();
                    userEntry2 = userEntry2.trim();

                    System.out.println("Please enter the name of the parameter you would like to remove.");
                    userEntry3 = sc.nextLine();
                    userEntry3 = userEntry3.trim();
                     //   holder.deleteParameter(userEntry, userEntry2,userEntry3);
                    
                }
                else if(userEntry.equalsIgnoreCase("Relationship"))
                {
                    System.out.println("Please enter the first class within the relationship you would like to delete.");
                    userEntry = sc.nextLine();
                    userEntry = userEntry.trim();
                    System.out.println("Please enter the second class within the relationship you would like to delete.");
                    userEntry2 = sc.nextLine();
                    userEntry2 = userEntry2.trim();
                    holder.deleteRelationship(userEntry,userEntry2);
                }
                else if(userEntry.equalsIgnoreCase("Exit"))
                {
                    System.out.println("Are you sure you would like to Exit? Y/N");
                    userEntry = sc.nextLine();
                    if(userEntry.equalsIgnoreCase("Y"))
                    break;
                }
                else{
                    System.out.println("Sorry, we don't recognize that command, please try again.");
                }
            }
            else if(userEntry.equalsIgnoreCase("ChangeType"))
            {
                System.out.println("Please enter the first class within the relationship you want to change the type of.");
                userEntry = sc.nextLine().trim();
                System.out.println("Please enter the second class within the relationship you want to change the type of.");
                userEntry2 = sc.nextLine().trim();
                System.out.println("Please enter the relationship type you would like to change this relationship to."
                    + "\nCan be either \"aggregation\", \"composition\", \"inheritance\", or \"realization\".");
                userEntry3 = sc.nextLine().trim();
                if (getRelTypeFromString(userEntry3) != null) {
                    holder.changeRelationshipType(userEntry,userEntry2,getRelTypeFromString(userEntry3));
                } else {
                    System.out.println("\"" + userEntry3 + "\" is not a valid relationship type.");
                }

            }
            else if(userEntry.equalsIgnoreCase("Display"))
            {
                System.out.println("Would you like to display 'one' class, 'all' classes, or their 'relationships'?");
                userEntry = sc.nextLine();
                userEntry = userEntry.trim();
                if(userEntry.equalsIgnoreCase("One"))
                {
                    System.out.println("Please enter the name of the class you want to display.");
                    userEntry = sc.nextLine();
                    userEntry = userEntry.trim();
                    holder.listClass(userEntry);
                }
                else if(userEntry.equalsIgnoreCase("All"))
                {
                    holder.ListClasses();
                }
                else if(userEntry.equalsIgnoreCase("Relationships"))
                {
                    holder.ListRelationships();
                }
                else if(userEntry.equalsIgnoreCase("Exit"))
                {
                    break;
                }
                else{
                    System.out.println("Sorry, we don't recognize that command, please try again.");
                }
            }
            else if(userEntry.equalsIgnoreCase("Exit"))
            {
                System.out.println("Are you sure you would like to Exit? Y/N");
                userEntry = sc.nextLine();
                if(userEntry.equalsIgnoreCase("Y"))
                break;
            }
            else if(userEntry.equalsIgnoreCase("Save"))
            {
                System.out.println("Please enter directory to save to. Example: \"/Users/jeff/Desktop/\"");
                userEntry = sc.nextLine();
                System.out.println("Please enter a name for your save file. (The .json extension will be appended automatically)");
                userEntry2 = sc.nextLine();
                holder.save(userEntry, userEntry2);
            }
            else if(userEntry.equalsIgnoreCase("Load"))
            {
                
            }
            else if(userEntry.equalsIgnoreCase("Help"))
            {
                System.out.println("Command List:");
                System.out.println("Note: 'class', 'method', 'parameter', 'field' and 'relationship' are sub commands within 'add', 'rename', and 'delete' all commands are 1 word.");
                System.out.println("Add - Prompts you to add either a 'class', 'method', 'parameter', 'field', or 'relationship' ");
                System.out.println("Rename - Prompts you to rename either a 'class', 'method', 'parameter', or 'field'");
                System.out.println("Delete - Prompts you to remove either a 'class','method', 'parameter', 'field', or 'relationship'");
                System.out.println("ChangeType – Allows you to change the type of an existing relationship.");
                System.out.println("Display - Gives the option to display either 'one' or 'all' classes.");
                System.out.println("Save - Saves the current existing diagram.");
                System.out.println("Load - Loads a diagram from file.");
                System.out.println("Exit - Allows you to stop the program from running.");
                System.out.println("Help - Displays the possible commands able to be made within the program.");
                // Suggestion: Sample – Loads a sample class diagram, first prompting the user if they would like to save their current work.
                // Suggestion: Clear – Clears the class diagram, first prompting the user if they are sure they want to delete their current unsaved work.
            }
            else
            {
                System.out.println("Sorry, we don't recognize that command, please try again.");
            }
        }
        sc.close();
    }

    /**
     * Helper method that returns a value from the RelationshipType enum that matches the input string.
     * @param input The input string.
     * @reutn The correct RelationshipType enum value if input equals "aggregation", "composition", "inheritance", 
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
