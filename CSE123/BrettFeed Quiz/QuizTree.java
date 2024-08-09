// Ataberk Yildiz
// 11/23/2023
// CSE 123
// P3: BrettFeed Quiz
// TA: Trien Vuong
// This is a QuizTree class that allows for taking a quiz
// giving you a result based on your choices

import java.util.*;
import java.io.*;

// TODO: Implement your QuizTree class
public class QuizTree {

    public QuizTreeNode root;
    public Scanner inputFile;

    // Constructs a new QuizTree based on the given inputFile
    //
    // Parameters:
    //   inputFile - a file with input in standard format with a pre-order traversal format
    //   to represent result nodes and choice nodes.
    public QuizTree(Scanner inputFile) {
        this.inputFile = inputFile;
        root = buildTree(new QuizTreeNode(inputFile.nextLine()));
    }
    
    // Builds a QuizTree based on the given file
    // 
    // Returns: a QuizTreeNode to represent the overall root.
    //
    // Parameters:
    //   node - QuizTreeNode to represent overall root
    private QuizTreeNode buildTree(QuizTreeNode node) {
        if (node.data.contains("END:")) {
            return node;
        } else {
            node.left = buildTree(new QuizTreeNode(inputFile.nextLine()));
            node.right = buildTree(new QuizTreeNode(inputFile.nextLine()));
        }
        return node;
    }
    
    // Allows the user to take the current quiz using the provided Scanner. This method should
    // prompt the user to choose between the options at each node and traverse the tree until
    // a leaf node is reached. When a leaf is reached, the user's result should be printed. 
    //
    // Parameters:
    //   console - provided Scanner that takes user input
    public void takeQuiz(Scanner console) {
        QuizTreeNode result = takeQuiz(console, root);
        System.out.println("Your result is: " + result.data.substring(4, result.data.length()));
    }

    // Helper method for takeQuiz
    private QuizTreeNode takeQuiz(Scanner console, QuizTreeNode node) {
        if (node.data.contains("END:")) {
            return node;
        } else {
            int index = node.data.indexOf("/");
            String option1 = node.data.substring(0, index);
            String option2 = node.data.substring(index + 1, node.data.length());
            System.out.print("Do you prefer " + option1 + " or " + option2 + "? ");
            String userInput = console.nextLine();
            while (!userInput.equalsIgnoreCase(option1) && !userInput.equalsIgnoreCase(option2)) {
                System.out.println("  Invalid response; try again.");
                System.out.print("Do you prefer " + option1 + " or " + option2 + "? ");
                userInput = console.nextLine();
            }
            if (userInput.equalsIgnoreCase(option1)) {
                 node = takeQuiz(console, node.left);
            } else {
                node = takeQuiz(console, node.right);
            }
        }
        return node;
    }
    
    // Replaces the node for the result toReplace with a new node representing a choice
    // between leftChoice and rightChoice leading to leftResult and rightResult respectively.
    // 
    // Parameters:
    //   toReplace - Result to replace 
    //   leftChoice - new left choice
    //   rightChoice - new right choice
    //   leftResult - new left result
    //   rightResult - new right result
    public void addQuestion(String toReplace, String leftChoice, String rightChoice, 
        String leftResult, String rightResult) {
            root = addQuestion(toReplace, leftChoice, rightChoice, leftResult, rightResult, root);
        }

    // Helper method for add Question
    private QuizTreeNode addQuestion(String toReplace, String leftChoice, String rightChoice, 
        String leftResult, String rightResult, QuizTreeNode node) {
        if (node != null) {
            if (node.data.equalsIgnoreCase("END:" + toReplace)) {
                node.data = leftChoice + "/" + rightChoice;
                node.left = new QuizTreeNode("END:" + leftResult);
                node.right = new QuizTreeNode("END:" + rightResult);
            } else {
                node.left = addQuestion(toReplace, leftChoice, rightChoice, leftResult, 
                    rightResult, node.left);
                node.right = addQuestion(toReplace, leftChoice, rightChoice, leftResult, 
                    rightResult, node.right);
            }
        }
        return node;
    }

    // Prints the current quiz to the output file
    // 
    // Parameters:
    //   outputFile - the output file to write to in standard format with a pre-order 
    //   traversal format to represent result nodes and choice nodes.
    public void export(PrintStream outputFile) {
        export(outputFile, root);
    }

    // Helper method for export
    private void export(PrintStream outputFile, QuizTreeNode node) {
        if (node != null) {
            outputFile.println(node.data);
            export(outputFile, node.left);
            export(outputFile, node.right);
        }
    }

    // QuizTreeNode is a class to represent a sigle node within a QuizTree.
    // A node can represent either a choice or a result.
    public static class QuizTreeNode {

        public String data;
        public QuizTreeNode left;
        public QuizTreeNode right;

        // Constructs a leaf node with the given data.
        public QuizTreeNode(String data) {
            this(data, null, null);
        }

        // Constructs a leaf or branch node with the given data and links.
        public QuizTreeNode(String data, QuizTreeNode left, QuizTreeNode right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }
}
