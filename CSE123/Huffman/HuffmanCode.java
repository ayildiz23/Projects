// Ataberk Yildiz
// 12/06/2023
// CSE 123
// P3: Huffman
// TA: Trien Vuong
// This is a HuffmanCode class that allows you to compress and decompress messages
// to store values in a more efficient way

import java.util.*;
import java.io.*;

public class HuffmanCode {
    private HuffmanNode overallRoot;  

    // Constructs a new HuffmanCode tree based on the given frequencies of characters
    // within a document.  
    //
    // Parameters:
    //   frequencies - frequencies is an array of frequencies where frequences[i] is 
    //   the count of the character with ASCII value i.
    public HuffmanCode(int[] frequencies) {
        overallRoot = buildTree(frequencies);
    }

    // Helper for the HuffmanCode(int[] frequencies) constructor
    // Builds a queue out of the array of frequencies given
    private HuffmanNode buildTree(int[] frequencies) {
        Queue<HuffmanNode> queue = new PriorityQueue<>();
        for (int i = 0; i < frequencies.length; i ++) {
            if (frequencies[i] > 0) {
                queue.add(new HuffmanNode((char) i, frequencies[i]));
            }
        }
        return buildTree(queue);
    }

    // Helper for the HuffmanCode(int[] frequencies) constructor
    // Creates the tree based on the queue given
    private HuffmanNode buildTree(Queue<HuffmanNode> queue) {
        if (queue.size() != 1) {
            HuffmanNode left = queue.remove();
            HuffmanNode right = queue.remove();
            queue.add(new HuffmanNode((char) 0, left.frequency + right.frequency, left, right));
            buildTree(queue);
        }
        return queue.peek();
    }


    // Constructs a new HuffmanCode based on the given input.  
    //
    // Parameters:
    //   input - a Scanner that reads from a .code file that contains
    //   previously constructed code
    public HuffmanCode(Scanner input) {
        overallRoot = new HuffmanNode((char) 0, -1);
        while (input.hasNextInt()) {
            int asciiValue = Integer.parseInt(input.nextLine());
            String code = input.nextLine();
            overallRoot = readTree(asciiValue, code, overallRoot);
        }
    }

    // Helper for the HuffmanCode(Scanner input) constructor
    private HuffmanNode readTree(int value, String code, HuffmanNode node) {
        if (code.length() == 0) {
            return new HuffmanNode((char) value, -1);
        } else {
            char nextMove = code.charAt(0);
            if (nextMove == '0') {
                if (node.left == null) {
                    node.left = new HuffmanNode((char) 0, -1);
                }
                node.left = readTree(value, code.substring(1), node.left);
            } else {
                if (node.right == null) {
                    node.right = new HuffmanNode((char) 0, -1);
                }
                node.right = readTree(value, code.substring(1), node.right);
            }
        }
        return node; 
    }

    // Stores the current HuffmanCode to the given output stream in the standard form
    //
    // Parameters:
    //   output - output stream to save the current HuffmanCode to
    public void save(PrintStream output) {
        save(output, overallRoot, "");
    }

    // Helper for the save method
    private void save(PrintStream output, HuffmanNode node, String soFar) {
        if (node != null) {
            if (node.character == (char) 0) {
                save(output, node.left, soFar + "0");
                save(output, node.right, soFar + "1");
            } else {
                output.println((int) node.character);
                output.println(soFar);
            }
        }
    }

    // Reads individual bits from the input stream and writes the corresponding 
    // characters to the ouput. 
    //
    // Parameters:
    //   input - the stream of bits to translate
    //   output - output stream to write the translation to
    public void translate(BitInputStream input, PrintStream output) {
        while(input.hasNextBit()) {
            output.write(translate(input, overallRoot));
        }
    }

    // Helper for the translate method
    private int translate(BitInputStream input, HuffmanNode node) {
        if (node.left == null && node.right == null) {
            return (int) node.character;
        } else if (input.nextBit() == 1) {
            return translate(input, node.right);
        } else {
            return translate(input, node.left);
        }
    }

    // HuffmanNode is a class to represent a single node within a HuffmanCode.
    // Each node contains information about the node's character, frequency of that character,
    // its left node, and right node
    private static class HuffmanNode implements Comparable<HuffmanNode> {
        public char character;
        public int frequency;
        public HuffmanNode left;
        public HuffmanNode right;

        // Constructs a leaf node with the given data.
        public HuffmanNode(char character, int frequency) {
            this(character, frequency, null, null);
        }

        // Constructs a leaf or branch node with the given data and links.
        public HuffmanNode(char character, int frequency, HuffmanNode left, HuffmanNode right) {
            this.character = character;
            this.frequency = frequency;
            this.left = left;
            this.right = right;
        }

        // Compares HuffmanNodes by comparing their frequencies
        public int compareTo(HuffmanNode other) {
            return this.frequency - other.frequency;
        }
    }
}
