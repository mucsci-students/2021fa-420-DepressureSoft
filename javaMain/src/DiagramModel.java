/**
 * Authors:
 * Date:
 * Description:
 */

import java.util.Scanner;
import java.util.ArrayList;
import javax.lang.model.SourceVersion;

public class DiagramModel {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        ArrayList<UMLClass> tester = new ArrayList<UMLClass>();
        String userEntry = "";
        System.out.println("Welcome to the text UML Diagram creator. To begin please select the action you would like to perform.");
        while(!userEntry.equals("Exit")){
            userEntry = sc.nextLine();
        }
        UMLClass test = new UMLClass(null, null, null);

        //Ignore this, this is to avoid annoying red squiggles
        System.out.println(test);
    }
}
