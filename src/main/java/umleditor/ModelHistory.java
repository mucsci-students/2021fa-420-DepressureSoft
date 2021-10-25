package umleditor;

import java.util.Stack;

public class ModelHistory {

    /** 
    * Singelton instance of the model history
    */
    private static ModelHistory historySingleton = null;

    /** 
    * Stack representing the history of all diagram models. 
    * Theoretically there's no limit to this history because a stack
    * is implemented with a vector? IDK if this is a bad thing yet, we'll see.
    */
    private Stack<DiagramModel> undoHistory;

    /** 
    * Stack representing the redo history of all diagram models. 
    * Theoretically there's no limit to this history because a stack
    * is implemented with a vector? IDK if this is a bad thing yet, we'll see.
    */
    private Stack<DiagramModel> redoHistory;

    /** 
    * Private constructor cuz its a singleton
    */
    private ModelHistory() {
        undoHistory = new Stack<DiagramModel>();
        redoHistory = new Stack<DiagramModel>();
    }

    /** 
    * Gets static instance of ModelHistory in memory 
    * If none exists, creates the instance
    * If it does exists, return it
    * @return The sinngleton model instance history
    */
    public static ModelHistory getInstance(){
        if(historySingleton != null){
            return historySingleton;
        }
        else{
            historySingleton = new ModelHistory();
            return historySingleton;
        }
    }

    /**
     * Creates a snapshot of the model passed in by pushing it onto the undo stack.
     * Calling this method also clears the redo stack. 
     * @param model The new model to be added to the history
     */
    public void snapshotModel(DiagramModel model){
        // Anytime we change state we want to clear the redo history
        undoHistory.push(model);
        redoHistory.clear();
    }
    
    public void clearHistory() {
    	undoHistory.clear();
    	redoHistory.clear();
    }

    /**
     * Gets the last snapshot that was pushed onto the undo stack.
     * Behind the scenes it also pushes it onto the redo stack.
     * @return the diagram model that was retrieved off the undo stack
     */
    public DiagramModel undo(DiagramModel currentModel){
        // Anytime we undo, push the redo stack
        DiagramModel model = undoHistory.pop();
        redoHistory.push(currentModel);
        return model;
    }
    /**
     * Gets the last redo snapshot that was pushed onto the redo stack.
     * @return the diagram model that was retrieved off the redo stack
     */
    public DiagramModel redo(DiagramModel currentModel){
        DiagramModel model = redoHistory.pop();
        undoHistory.push(currentModel);
        return model;
    }

    public boolean isUndoHistoryEmpty(){
        return undoHistory.empty();
    }

    public boolean isRedoHistoryEmpty(){
        return redoHistory.empty();
    }
}