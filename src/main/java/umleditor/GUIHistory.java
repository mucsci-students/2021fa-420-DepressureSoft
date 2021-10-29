package umleditor;

import java.util.Stack;

public class GUIHistory {

    private static GUIHistory guiHistorySingleton = null;

    private Stack<GUI> undoHistory;

    private Stack<GUI> redoHistory;


    private GUIHistory(){
        undoHistory = new Stack<GUI>();
        redoHistory = new Stack<GUI>();
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

    public void snapshotModel(GUI classBoxes){
        // Anytime we change state we want to clear the redo history
        undoHistory.push(classBoxes);
        redoHistory.clear();
    }

    public GUI undo(GUI currentClassBoxes){
        // Anytime we undo, push the redo stack
        GUI model = undoHistory.pop();
        redoHistory.push(currentClassBoxes);
        return model;
    }

    public GUI redo(GUI currentClassBoxes){
        GUI model = redoHistory.pop();
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