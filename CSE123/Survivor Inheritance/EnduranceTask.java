// Ataberk Yildiz
// Brett Wortzman
// CSE 123
// C1: Survivor Challenge
//
// This is a class to represent an EnduranceTask. This task
// presents challenges that may or may not take multiple actions to complete.

import java.util.*;

public class EnduranceTask extends Task {

    private String type;
    private int duration;
    private int numOfSuccess = 0;
    private List<String> actions = new ArrayList<>
        (Arrays.asList("jump", "run", "swim", "crawl", "climb"));

    /**
     *   Constructs a new EnduranceTask with the given type of action to do, 
     *   duration of time to do it, and  the description.
     */
    public EnduranceTask(String type, int duration, String description) {
        super(description);
        this.type = type;
        this.duration = duration;
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
        return numOfSuccess == duration;
    }

    /**
     * Attempts to take an action to work towards completing the task. Tasks may require any
     *  number of actions to complete, and multiple required actions may be ordered or not.
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
        if (type.equals(action)) {
            numOfSuccess ++;
            actionSuccess = true;
        }
        return actionSuccess;
    }



}
