// Ataberk Yildiz
// Brett Wortzman
// CSE 123
// P1: Mini-Git
//
// This is a class that can be used to create a new Repository.
// A respository can store commits made by the user and tracks
// them in an order based on when the commit was made

import java.util.*;
import java.text.SimpleDateFormat;
import java.lang.Math;

public class Repository {

    private String name;
    private Commit head;

    /**
     *   Constructs a new Repository with the given name
     *
     *   @throws IllegalArgumentException if name is null or empty
     *
     */
    public Repository(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.name = name;
    }

    /**
     * Returns the string id of the head of the repository or null if the head is null. 
     * 
     * @return the string id of the head of the repository or null if it is null.
     */
    public String getRepoHead() {
        if (getRepoSize() > 0) {
            return head.id;
        }
        return null;
    }

    /**
     * Returns the size of the repository. 
     * 
     * @return an int size of the repository.
     */
    public int getRepoSize() {
        int size = 0;
        Commit temp = head;
        while (temp != null) {
            size ++;
            temp = temp.past;
        }
        return size;
    }

    /**
     * Returns a string representation of the head of a repository. 
     * 
     * @return the string representation of the head of a repository.
     */
    public String toString() {
        String result = "";
        if (getRepoSize() == 0) {
            result += name + " - No commits";
        } else {
            result += name + " - Current head: " + head.toString();
        }
        return result;
    }

    /**
     * Returns if the repository contains a commit with the indicated targetId 
     * 
     * @return true if the repository contains the targetId and false if not
     */
    public boolean contains(String targetId) {
        Commit temp = head;
        while (temp != null) {
            if (temp.id.equals(targetId)) {
                return true;
            }
            temp = temp.past;
        }
        return false;
    }

    /**
     * Returns a String representation of n commits within the repository. 
     * If n is more than the size of the repository, the string contains all commits. 
     * If the repository is empty, returns an empt string. 
     * 
     * @return a String representation of n commits within the repository.
     *
     * @throws IllegalArgumentException if n is nonpositive
     */
    public String getHistory(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        Commit temp = head;
        String result = "";
        for (int i = 0; i < Math.min(n, (getRepoSize())); i++) {
            result += temp.toString() + "\n";
            temp = temp.past;
        }
        return result;
    }

    /**
     * Creates a new commit and makes it the head of the repository.
     * 
     * @return the String id of the new head of the repository.
     */
    public String commit(String message) {
        head = new Commit(message, head);
        return head.id;
    }

    /**
     * Drops the commit whose id is targetId form the repository
     * 
     * @return true if a commit was dropped and false if not
     */
    public boolean drop(String targetId) {
        if (this.contains(targetId)) {
            if (head.id.equals(targetId)) {
                head = head.past;
                return true;
            } else {
                Commit temp = head;
                while (temp.past != null) {
                    if (temp.past.id.equals(targetId)) {
                        temp.past = temp.past.past;
                        return true;
                    } else {
                        temp = temp.past;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Takes all the commits in the other repository and moves them into this.
     * Combines the repositories based on TimeStamp with most recent becoming the new head.
     * The other repository becomes empty after this method is executed. 
     */
    public void synchronize(Repository other) {
        if (head == null) {
            this.head = other.head;
            other.head = null;
        } else {
            if (other.head != null && other.head.timeStamp > this.head.timeStamp) {
                Commit temp = other.head.past;
                other.head.past = this.head;
                this.head = other.head;
                other.head = temp;
            }
            Commit curr = this.head;
            while (curr.past != null && other.head != null) {
                if (other.head.timeStamp < curr.timeStamp) {
                    while (other.head != null && curr.past != null && other.head.timeStamp < curr.past.timeStamp) {
                        curr = curr.past;
                    }
                    Commit temp = other.head;
                    other.head = other.head.past;
                    temp.past = curr.past;
                    curr.past = temp;
                    curr = curr.past;
                } else {
                    curr = curr.past;
                } 
            }
            if (curr.past == null) {
                curr.past = other.head;
                other.head = null;
            } 
        } 
    }


    /**
     * DO NOT MODIFY
     * A class that represents a single commit in the repository.
     * Commits are characterized by an identifier, a commit message,
     * and the time that the commit was made. A commit also stores
     * a reference to the immediately previous commit if it exists.
     *
     * Staff Note: You may notice that the comments in this 
     * class openly mention the fields of the class. This is fine 
     * because the fields of the Commit class are public. In general, 
     * be careful about revealing implementation details!
     */
    public class Commit {

        private static int currentCommitID;

        /**
         * The time, in milliseconds, at which this commit was created.
         */
        public final long timeStamp;

        /**
         * A unique identifier for this commit.
         */
        public final String id;

        /**
         * A message describing the changes made in this commit.
         */
        public final String message;

        /**
         * A reference to the previous commit, if it exists. Otherwise, null.
         */
        public Commit past;

        /**
         * Constructs a commit object. The unique identifier and timestamp
         * are automatically generated.
         * @param message A message describing the changes made in this commit.
         * @param past A reference to the commit made immediately before this
         *             commit.
         */
        public Commit(String message, Commit past) {
            this.id = "" + currentCommitID++;
            this.message = message;
            this.timeStamp = System.currentTimeMillis();
            this.past = past;
        }

        /**
         * Constructs a commit object with no previous commit. The unique
         * identifier and timestamp are automatically generated.
         * @param message A message describing the changes made in this commit.
         */
        public Commit(String message) {
            this(message, null);
        }

        /**
         * Returns a string representation of this commit. The string
         * representation consists of this commit's unique identifier,
         * timestamp, and message, in the following form:
         *      "[identifier] at [timestamp]: [message]"
         * @return The string representation of this collection.
         */
        @Override
        public String toString() {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
            Date date = new Date(timeStamp);

            return id + " at " + formatter.format(date) + ": " + message;
        }

        /**
        * Resets the IDs of the commit nodes such that they reset to 0.
        * Primarily for testing purposes.
        */
        public static void resetIds() {
            Commit.currentCommitID = 0;
        }
    }
}
