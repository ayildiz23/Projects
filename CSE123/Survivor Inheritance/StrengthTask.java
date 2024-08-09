// Ataberk Yildiz
// Brett Wortzman
// CSE 123
// C1: Survivor Challenge
//
// This is a class to represent a StrengthTask. This task
// presents a challenge to enter a combination of letters
// a certain amount of times. 

import java.util.*;

public class StrengthTask extends PrecisionTask {
    
    private int number;
    private static List<String> actions = new ArrayList<>();
    private int numOfSuccess = 0;
    private String extraDescription = "";

    /**
     *   Constructs a new StrengthTask with the letters to enter,  
     *   number of times to enter them, and  the description.
     */
    public StrengthTask(String letters, int number, String description) {
        super(actions, description);
        this.number = number;
        createActionOptions(letters);
        buildDescription();
    }

    /**
     * Returns a string representation of the task. 
     * 
     * @return the string representation of the task
     */
    public String getDescription() {
        return super.getDescription() + extraDescription;
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
     * Builds the list of possible actions for the task
     */
    private List<String> createActionOptions(String letters) {
        actions.add(letters);
        letters = "";
        int j = 0;
        for (int i = 0; i < 4; i ++) {
            j = j + 2;
            for (j = 0; j < number; j++) {
                letters += actions.get(0);
            }
            actions.add(letters);
        }
        return actions;
    }

    /**
     * Builds the description for the task
     */
    private void buildDescription() {
        extraDescription += "\nEnter the letters " + "\"" + actions.get(0) 
            + "\" " + number + " times in a row";
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
        if (!actions.contains(action)) {
            throw new IllegalArgumentException("This is not a valid action");
        } 
        if (action.equals(actions.get(1))) {
            numOfSuccess++;
            return true;
        }
        return false;
    }
    
}
