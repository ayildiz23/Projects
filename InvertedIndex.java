// Ataberk Yildiz
// 10/04/2023
// CSE 123
// P0: Warm Up and Review
// TA: Trien Vuong
// This is an InvertedIndex class. It takes a list and creates an index that's inverted.

import java.util.*;

public class InvertedIndex {
    public static void main(String[] args) {
        List<String> docs = new ArrayList<>();
        docs.add("Raiders of the Lost Ark");
        docs.add("The Temple of Doom");
        docs.add("The Last Crusade");
        
        Map<String, Set<String>> result = createIndex(docs);
        System.out.println(docs);
        System.out.println();
        System.out.println(result);
    }

    // Produces and returns an "inverted index" of the parameter list of documents.
    // The inverted index is a map where the keys are the individual words and the 
    // values are the individual documents that also contain that word. The keys 
    // of the returned map are case-insensitive, meaning "The" and "the" will be
    // treated as the same key. 
    //
    // Parameters:
    //   list - the list of documents
    //
    // Returns:
    //   A map that is an inverted index of the lsit of documents
    public static Map<String, Set<String>> createIndex(List<String> list) {
        Map<String, Set<String>> map = new TreeMap<>();
        for (int i = 0; i < list.size(); i++) {
            // Creates a set with the unique words from the sentece
            String sentence = list.get(i).toLowerCase();
            String[] arrayOfStrings = sentence.split(" ");
            Set<String> uniqueWords = new HashSet<>();
            for (int j = 0; j < arrayOfStrings.length; j++) {
                uniqueWords.add(arrayOfStrings[j]);
            }
            // Creates or adds on to a set if the sentence contains the unique word
            for (String word : uniqueWords) {
                if (!map.keySet().contains(word.toLowerCase())) {
                    Set<String> newSet = new HashSet<>();
                    map.put(word.toLowerCase(), newSet);
                }
                map.get(word.toLowerCase()).add(list.get(i));
            }
        }
        return map;
    }

}
