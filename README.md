# 2021fa-420-DepressureSoft

1. Download Visual Studio Code 

2. Download GitHub Desktop

3. Go to https://code.visualstudio.com/docs/java/java-tutorial and download the necessary Java Kit for your system.
NOTE: Make sure your system has the necessary Java Compiler installed on Visual Studio. 

4. Sign in to your gitHub account and click File -> New Repository then add DepressureSoft's repository to your list.

5. Select 'Open in Visual Studio Code'

6. Once opened go to the 'UMLInterface.java'

7. Right-click and select 'Run Java' and refer to incode instructions to run the program.


Note: Save/Load functionality has been delayed until sprint 2.

KNOWN BUGS/ISSUES
-----------------

- User-inputted class and attribute names are reduced to all lowercase characters within the main method of UMLInterface.java
- When trying to delete an attribute, code in UMLInterface copies the variable holding the class name into the variable holding the attribute name, resulting in a call to deleteAttribute(className, className).
