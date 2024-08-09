// Ataberk Yildiz
// Brett Wortzman
// CSE 123
// C1: Survivor Challenge
//
// This is a class to represent a PrecisionTask. This task
// presents a challenge where contestants have to take a particular 
// order of actions to complete the task.

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PrecisionTask extends Task {

    private int currentTask = 0;
    List<String> actions = new ArrayList<>(Arrays.asList("jump", "run", "swim", "crawl", "climb"));
    List<String> requiredActions = new ArrayList<>();

    /**
     *   Constructs a new PrecisionTask with the given list of requiredActions, 
     *   and  the description.
     */
    public PrecisionTask(List<String> requiredActions, String description) {
        super(description);
        this.requiredActions = requiredActions;
    }

    /**
     * Returns a string representation of the task. 
     * 
     * @return the string representation of the task
     */
    public String getDescription() {
        return super.getDescription();
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
        return currentTask == requiredActions.size();
    }

    /**
     * Attempts to take an action to work towards completing the task. 
     *  If the attempted action is successful, the task state is updated to reflect the completed
     *  action, which may or may not complete the task. Valid actions to attempt are those in the
     *  list returned by getActionOptions().
     * 
     * @param action the action to be attempted
     * @return true if the action was successful at completing part or all of the task, 
     *          false otherwise
     * @throws IllegalArgumentException if the action attempted is not a valid action for this task
     *              (as specified by getActionOptions())
     * @see getActionOptions
     */
    public boolean takeAction(String action) {
        boolean actionSuccess = false;
        if (!actions.contains(action)) {
            throw new IllegalArgumentException("This is not a valid action");
        } 
        if (requiredActions.get(currentTask).equals(action)) {
            currentTask ++;
            actionSuccess = true;
        }
        return actionSuccess;
    }
    
}
