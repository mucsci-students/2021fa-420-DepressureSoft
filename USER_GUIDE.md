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
