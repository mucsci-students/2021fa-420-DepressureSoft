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

    public void snapshotModel(GUI currentGUI){
        // Anytime we change state we want to clear the redo history
        undoHistory.push(currentGUI);
        redoHistory.clear();
    }

    public GUI undo(GUI currentGUI){
        // Anytime we undo, push the redo stack
        GUI model = undoHistory.pop();
        redoHistory.push(currentGUI);
        return model;
    }

    public GUI redo(GUI currentGUI){
        GUI model = redoHistory.pop();
        undoHistory.push(currentGUI);
        return model;
    }

    public boolean isUndoHistoryEmpty(){
        return undoHistory.empty();
    }

    public boolean isRedoHistoryEmpty(){
        return redoHistory.empty();
    }
}