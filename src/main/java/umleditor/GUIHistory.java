package umleditor;

import java.util.Stack;
import java.util.HashMap;

public class GUIHistory {

    private static GUIHistory guiHistorySingleton = null;

    private Stack<HashMap<String,classBox>> undoHistory;

    private Stack<HashMap<String,classBox>> redoHistory;


    private GUIHistory(){
        undoHistory = new Stack<HashMap<String,classBox>>();
        redoHistory = new Stack<HashMap<String,classBox>>();
    }

    public static GUIHistory getInstance(){
        if(guiHistorySingleton != null){
            return guiHistorySingleton;
        }
        else{
            guiHistorySingleton = new GUIHistory();
            return guiHistorySingleton;
        }
    }

    public void snapshotModel(HashMap<String,classBox> classBoxes){
        // Anytime we change state we want to clear the redo history
        undoHistory.push(classBoxes);
        redoHistory.clear();
    }

    public HashMap<String,classBox> undo(HashMap<String,classBox> currentClassBoxes){
        // Anytime we undo, push the redo stack
        HashMap<String,classBox> model = undoHistory.pop();
        redoHistory.push(currentClassBoxes);
        return model;
    }

    public HashMap<String,classBox> redo(HashMap<String,classBox> currentClassBoxes){
        HashMap<String,classBox> model = redoHistory.pop();
        undoHistory.push(currentClassBoxes);
        return model;
    }

    public boolean isUndoHistoryEmpty(){
        return undoHistory.empty();
    }

    public boolean isRedoHistoryEmpty(){
        return redoHistory.empty();
    }
}