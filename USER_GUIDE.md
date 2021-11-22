# Getting Started 

Hello! Thank you for choosing our UML Editor. You might still be a little confused after completing the instructions to run our program in the README.md, and this document is meant to help with that. The first half of this document will cover CLI help, so if you're looking for GUI assistance please scroll to the "GUI Overview" header.

## CLI First Steps 

- Type `help` to view a list of useful commands, all of which will be further explained in this document.
- Type `help command` to view helpful information about a specific command, replacing "command" with the desired command (ex. add, delete, rename). 

### CLI Add Commands

- `add class className`
  - Creates a class named "className" in the UML Diagram. The class will be initialized without methods, parameters, fields, or relationships.
- `add method className methodName returnType`
  - Creates a method named "methodName" in the class named "className" with the return type "returnType".
- `add field className fieldName returnType`
  - Creates a field named "fieldName" in the class named "className" with the return type "returnType".
- `add parameter className methodName parameterName returnType`
  - Creates a parameter named "parameterName" in the method "methodName" (within the class named "className") with the return type "returnType".
- `add relationship sourceClass destinationClass relationshipType`
  - Creates a relationship between the class "sourceClass" and the class "destinationClass" with the type "relationshipType".
  - There are four options for relationship types: aggregation, composition, inheritance, and realization.

### CLI Delete Commands

- `delete class className`
  - Removes the class "className" from the UML Diagram, also removing any methods, fields, and parameters within the class.
- `delete method className methodName`
  - Removes the method "methodName" from the class "className", also removing any parameters contained within the method.
- `delete field className fieldName`
  - Removes the field "fieldName" from the class "className".
- `delete parameter className methodName parameterName`
  - Removes the parameter "parameterName" from the method "methodName" (within the class "className").
- `delete relationship sourceClass destinationClass`
  - Removes the relationship between the class "sourceClass" and the class "destinationClass".

### CLI Rename Commands

- `rename class className newName`
  - Renames the class "className" to "newName".
- `rename method className methodName newName`
  - Renames the method "methodName" (within the class "className") to "newName".
- `rename field className fieldName newName`
  - Renames the field "fieldName" (within the class "className") to "newName".
- `rename parameter className methodName parameterName newName`
  - Renames the parameter "parameterName" (within the method "methodName", which is itself within the class "className") to "newName".

### CLI File Commands

- `save directoryName fileName`
  - Saves the current UML Diagram in the directory "directoryName", giving it the file name "fileName".
  - UML Diagram will be saved as a JSON file, and the ".json" extension will be appended automatically if not included in the "fileName".
- `load filePath`
  - Loads a UML Diagram from the given file path "filePath". 

### CLI Change Type Commands

- `changetype method className methodName newType`
  - Changes the return type of the method "methodName" (within class "className") to "newType".
- `changetype field className fieldName newType`
  - Changes the return type of the field "fieldName" (within class "className") to "newType".
- `changetype parameter className methodName parameterName newType`
  - Changes the return type of the parameter "parameterName" (within the method "methodName", which is itself within the class "className") to "newType".
- `changetype relationship sourceClass destinationClass relationshipType`
  - Changes the relationship type of the relationship between class "sourceClass" and class "destinationClass".
  - There are four options for relationship types: aggregation, composition, inheritance, and realization.

### CLI Display Commands

- `display all`
  - Displays all classes currently in the UML Diagram, also listing their respective fields, methods, and parameters.
- `display class className`
  - Displays all information about the class "className", specifically its fields, methods, and parameters.
- `display relationships`
  - Displays all relationships within the UML Diagram, listing the source class, destination class, and relationship type.

### CLI Edit Commands

- `undo`
  - Reverts to the program state before the most recent change.
- `redo`
  - Reverts to the program state before "undo" was executed, if "undo" has been executed.

### CLI Exit Command

- `exit` 
  - Closes the UML Editor.
  - Will prompt the user with "Are you sure you want to exit?", to which the user can respond `y` or `n`. If `y` the program will exit, otherwise the program will resume.

## GUI Overview

The GUI functions differently from the CLI in that all the commands listed above for the CLI have button counterparts in the GUI. To find a command in the GUI, hover over the header that the desired command is located in (as noted below). Then, select the command desired and follow the on-screen prompts to enter whatever information is required.

If a command is grayed out, that command is currently unavailable because of the current state of the UML Diagram. For instance, you cannot delete a class if no class exists to be deleted, so the "Class" option under "Delete" will be grayed out until a class is added.

Please reference the "Help" button to find a brief overview of available commands. 

When wishing to terminate the program, please select the red "X" button in the upper-right corner of the window. 

### GUI Button Header Structure

- File
  - Save (Also accessible via keyboard shortcut CTRL + S)
  - Load (Also accessible via keyboard shortcut CTRL + O)

- Add
  - Class
  - Relationship
  - Field
  - Method
  - Parameter

- Delete
  - Class
  - Relationship
  - Field
  - Method
  - Parameter

- Rename
  - Class
  - Field
  - Method
  - Parameter

- Edit
  - Undo (Also accessible via keyboard shortcut CTRL + Z)
  - Redo (Also accessible via keyboard shortcut CTRL + Y)

- Help

### GUI File Buttons

- Save
  - Enter name to save UML Diagram file as.
  - Select "Save" to save the UML Diagram as JSON file.
- Save as Image
  - Enter name to save UML Diagram image as.
  - Select "Save" to save the UML Diagram as a jpg.
- Load
  - Locate UML Diagram JSON file to load.
  - Select "Load" to load the UML Diagram JSON file into the program.

### GUI Add Buttons

- Class
  - ![image](https://user-images.githubusercontent.com/70029899/142935167-9b2e58e5-39e1-4652-98ed-e7bb3623bf3c.png)
  - Enter the desired name of the class in the text box, then select "Add" to add the class to the UML Diagram.
- Method
  - ![image](https://user-images.githubusercontent.com/70029899/142935954-40cee265-d4af-4664-bac9-fdf9ee179342.png)
  - Select the desired class from the "Select Class" dropdown box.
  - Enter the desired name of the method in the "Enter Method Name" text box.
  - Enter the desired method return type in the "Enter Method Return Type" text box.
  - Select "Add" to add the method to the desired class in the UML Diagram. 
- Field
  - ![image](https://user-images.githubusercontent.com/70029899/142936312-f2709d5c-f21f-4236-99b9-76578396932b.png)
  - Select the desired class from the "Select Class" dropdown box.
  - Enter the desired name of the field in the "Enter Field Name" text box.
  - Enter the desired field return type in the "Enter Field Type" text box.
  - Select "Add" to add the field to the desired class in the UML Diagram.
- Parameter
  - ![image](https://user-images.githubusercontent.com/70029899/142940862-47c6e40d-5c69-4333-ac97-7afc5fd64cda.png)
  - Select the desired class from the "Select Class" dropdown box.
  - Select the desired method from the "Select Method" dropdown box.
  - Enter the desired parameter name in the "Enter Parameter Name" text box.
  - Enter the desired parameter type in the "Enter Parameter Type" text box.
  - Select "Add" to add the parameter to the desired method within the desired class in the UML Diagram. 
- Relationship
  - ![image](https://user-images.githubusercontent.com/70029899/142941257-bbc3a978-5f2f-416b-9941-573bd23e93d3.png)
  - Select the desired source class from the "Select First Class" dropdown box.
  - Select the desired destination class from the "Select Second Class" dropdown box.
  - Select the desired relationship type from the "Select Relationship Type" dropdown box.
  - Select "Add" to add the relationship between the desired classes in the UML Diagram. 

### GUI Delete Buttons

- Class
  - ![image](https://user-images.githubusercontent.com/70029899/142941783-2fbf2aa5-c25c-4320-bf38-6bae1cc64cbc.png)
  - Select the desired class from the "Select Class" dropdown box.
  - Select "Delete" to remove the desired class from the UML Diagram.
- Method
  - ![image](https://user-images.githubusercontent.com/70029899/142943187-8db89429-ae3c-4469-b037-9c6a15d356de.png)
  - Select the desired class from the "Select Class" dropdown box.
  - Select the desired method from the "Select Method" dropdown box.
  - Select "Delete" to remove the desired method from the UML Diagram.
- Field
  - ![image](https://user-images.githubusercontent.com/70029899/142943370-13f19e85-9d2d-4abc-a8fd-1749b17a799f.png)
  - Select the desired class from the "Select Class" dropdown box.
  - Select the desired field from the "Select field" dropdown box.
  - Select "Delete" to remove the desired field from the UML Diagram. 
- Parameter
  - ![image](https://user-images.githubusercontent.com/70029899/142943823-51bea805-37d5-4302-b251-2212afbdcbac.png)
  - Select the desired class from the "Select Class" dropdown box.
  - Select the desired method from the "Select Method" dropdown box.
  - Select the desired parameter from the "Select Parameter" dropdown box.
  - Select "Delete" to remove the desired parameter from the UML Diagram.
- Relationship
  - ![image](https://user-images.githubusercontent.com/70029899/142943939-b199c8d7-d75a-421e-adcf-76e8dfe0d3f2.png)
  - Select the desired relationship from the "Select Relationship" dropdown box.
  - Select "Delete" to remove the desired relationship from the UML Diagram.

### GUI Rename Buttons

- Class
  - ![image](https://user-images.githubusercontent.com/70029899/142944873-63b9c9c1-1562-41da-9a36-10bd72dac1b7.png)
  - Select the desired class from the "Select Class" dropdown box.
  - Enter the new name of the class in the "Enter NEW Class Name" text box.
  - Select "Rename" to rename the desired class in the UML Diagram.
- Method
  - ![image](https://user-images.githubusercontent.com/70029899/142945010-a8deeb37-11ee-43e8-b41e-2532d93ab5f8.png)
  - Select the desired class from the "Select Class" dropdown box.
  - Select the desired method from the "Select Method" dropdown box.
  - Enter the new name of the method in the "Enter NEW Method Name" text box.
  - Select "Rename" to rename the desired method in the UML Diagram.
- Field
  - ![image](https://user-images.githubusercontent.com/70029899/142945144-dfa5f712-1ce2-44ed-b844-8e8997428ec4.png)
  - Select the desired class from the "Select Class" dropdown box.
  - Select the desired field from the "Select Field" dropdown box.
  - Enter the new name of the field in the "Enter NEW Field Name" text box.
  - Select "Rename" to rename the desired field in the UML Diagram.
- Parameter
  - ![image](https://user-images.githubusercontent.com/70029899/142945242-a0f0ff2e-566f-4325-9a72-0f3da9d3f603.png)
  - Select the desired class from the "Select Class" dropdown box.
  - Select the desired method from the "Select Method" dropdown box.
  - Select the desired parameter from the "Select Parameter" dropdown box.
  - Enter the new name of the parameter in the "Enter NEW Parameter Name" text box.
  - Select "Rename" to rename the desired parameter in the UML Diagram.  

### GUI Edit Buttons

- Undo
  - Reverts to the program state before the most recent change.
- Redo
  - Reverts to the program state before "undo" was executed, if "undo" has been executed.

### GUI Help Button

- Help
  - Displays the help menu.
