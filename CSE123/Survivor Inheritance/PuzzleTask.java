// Ataberk Yildiz
// Brett Wortzman
// CSE 123
// C1: Survivor Challenge
//
// This is a class to represent a PuzzleTask. This task
// presents a challenge that tests intelligence or memory by making
// contestants solve a puzzle or riddle. 

import java.util.*;

public class PuzzleTask extends Task {

    private String solution;
    private int numOfSuccess = 0;
    private int currentHintNum = 0;
    List<String> hints = new ArrayList<>();
    List<String> actions = new ArrayList<>(Arrays.asList("hint", "solve <solution>"));

    /**
     *   Constructs a new PuzzleTask with the given solution, hints,
     *   and  the description.
     */
    public PuzzleTask(String solution, List<String> hints, String description) {
        super(description);
        this.hints = hints;
        this.solution = solution;
    }

    /**
     * Returns a string representation of the task. 
     * 
     * @return the string representation of the task
     */
    public String getDescription() {
        return super.getDescription() + "\n Hint: " + this.hints.get(currentHintNum - 1);
    }

    /**
     * Returns a list of actions that may be attempted to complete this task.
     * 
     * @return the list of valid actions for the task
     */
    public List<String> getActionOptions() {
        return actions;
    }

    /**
     * Returns whether or not this task has been completed.
     * 
     * @return true if the task has been completed, false otherwise
     */
    public boolean isComplete() {
        return numOfSuccess >= 1;
    }

    /**
     * Attempts to take an action to work towards completing the task. Tasks may require any
     *  number of actions to complete, especiall when the user asks for hints.
     *  If the attempted action is successful, the task state is updated to reflect the completed
     *  action, which may or may not complete the task. Valid actions to attempt are those in the
     *  list returned by getActionOptions().
     * 
     * @param action the action to be attempted
     * @return true if the action was successful at completing part or all of the task, 
     *          false otherwise
     * @throws IllegalArgumentException if the action attempted is not a valid action for this task
     *              (as specified by getActionOptions()
     * @see getActionOptions
     */
    public boolean takeAction(String action) {
        String[] arr = action.split(" ");
        if (!arr[0].equals(actions.get(0)) && !arr[0].equals("solve")) {
            throw new IllegalArgumentException("This is not a valid action");
        } else if (arr[0].equals(actions.get(0)) && arr.length > 2) {
            throw new IllegalArgumentException("This is not a valid action");
        }
        boolean actionSuccess = false;
        if (actions.get(0).equals(action)) {
            currentHintNum ++;
            if (currentHintNum > hints.size()) {
                return false;
            }
            actionSuccess = true;
            return actionSuccess;
        } 
        
        if (solution.equals(arr[1])) {
            numOfSuccess = 1;
            actionSuccess = true;
        }
        return actionSuccess;
    }
    
}
