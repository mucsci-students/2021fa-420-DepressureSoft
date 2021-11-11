# Getting Started 

Hello! Thank you for choosing our UML Editor. You might still be a little confused after completing the instructions to run our program in the README.md, and this document is meant to help with that. The first half of this document will cover CLI help, so if you're looking for GUI assistance please scroll to the "GUI First Steps" header.

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

### CLI Save/Load Commands

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

### CLI Undo/Redo Commands

- `undo`
  - Reverts to the program state before the most recent change.
- `redo`
  - Reverts to the program state before "undo" was executed, if "undo" has been executed.

### CLI Exit Command

- `exit` 
  - Closes the UML Editor.
  - Will prompt the user with "Are you sure you want to exit?", to which the user can respond `y` or `n`. If `y` the program will exit, otherwise the program will resume.
