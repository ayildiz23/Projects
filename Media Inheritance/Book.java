// Ataberk Yildiz
// 10/04/2023
// CSE 123
// P0: Warm Up and Review
// TA: Trien Vuong
// This class implements the Media interface to represent a book.

import java.util.*;

public class Book implements Media {
    private String title;
    private List<String> authors;
    private double sumRatings;
    List<Integer> ratings = new ArrayList<>();

    // constructs a book
    public Book (String title, String author) {
        this.title = title;
        authors = new ArrayList<String>();
        authors.add(author);
    }

    // constructs a book
    public Book (String title, List<String> authors) {
        this.title = title;
        this.authors = authors;
    }

    /**
    * Gets the title of this media.
    *
    * @return      The title of this media.
    */
    public String getTitle() {
        return title;
    }
    /**
    * Gets all artists associated with this media.
    *
    * @return      A list of artists for this media.
    */
    public List<String> getArtists() {
        return authors;
    }

    /**
    * Adds a rating to this media.
    *
    * @param score     The score for the new rating. Should be non-negative.
    *
    * @throws IllegalArgumentException if the score is a negative number
    */
    public void addRating(int score) {
        if (score < 0) {
            throw new IllegalArgumentException("Can't be less than 0");
        }
        ratings.add(score);
        sumRatings += score;
    }

    /**
    * Gets the number of times this media has been rated.
    *
    * @return      The number of ratings for this media.
    */
    public int getNumRatings() {
        return ratings.size();
    }

    /**
    * Gets the average (mean) of all ratings for this media.
    *
    * @return      The average (mean) of all ratings for this media. If no ratings exist, returns 0.
    */
    public double getAverageRating() {
        if (ratings.size() == 0) {
            return 0;
        }
        return sumRatings / ratings.size();
    }

    // returns a String representation for the Book class.
    public String toString() {
        if (ratings.size() == 0) {
            return title + " by " + authors + ": No ratings yet!";
        } 
        return title + " by " + authors + ": " + String.format("%.2f", getAverageRating())
            + " (" + ratings.size() + " ratings)";
          
    }

}
