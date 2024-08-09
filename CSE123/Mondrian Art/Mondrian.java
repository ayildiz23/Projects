// Ataberk Yildiz
// Brett Wortzman
// CSE 123
// C2: Mondrian Art
//
// This is a class that can be used to create Mondrian Art
// This class can create both a basic representationa and a complex representation
// of Mondrian art which involves picking colors based on location

import java.util.*;
import java.awt.*;

public class Mondrian {

    private Random rand = new Random();

    /**
     * Creates a basic Mondrian Art piece with the given array of pixels 
     *
     * @throws an IllegalArgumentException if the height and width aren't the specified number
     */
    public void paintBasicMondrian(Color[][] pixels) {
        if (pixels.length < 300 || pixels[0].length < 300) {
            throw new IllegalArgumentException("The width and length should be >= 300 px");
        }
        paintBasicMondrian(pixels, 0, 0, pixels.length, pixels[0].length);
    }

    /**
     * Helper method for creating a basic Mondrian Art piece. Splits the 
     * canvas into smaller quadrants and paints them a random color 
     */
    private void paintBasicMondrian(Color[][] pixels, int x, int y, int width, int height) {
        if (width < pixels.length / 4 && height < pixels[0].length / 4) {
            fillRegion(pixels, x, y, width, height);
        } else if (width >= pixels.length / 4 && height >= pixels[0].length / 4) {
            int splitX = x + rand.nextInt(width);
            int splitY = y + rand.nextInt(height);
            paintBasicMondrian(pixels, splitX, y, width - (splitX - x), splitY - y);
            paintBasicMondrian(pixels, x, y, splitX - x, splitY - y);
            paintBasicMondrian(pixels, splitX, splitY, width - (splitX - x), 
                height - (splitY - y));
            paintBasicMondrian(pixels, x, splitY, splitX - x, height - (splitY - y));
        } else if (width >= pixels.length / 4) {
            int splitX = x + rand.nextInt(width);
            paintBasicMondrian(pixels, x, y, splitX - x, height);
            paintBasicMondrian(pixels, splitX, y, width - (splitX - x), height);
        } else {
            int splitY = y + rand.nextInt(height);
            paintBasicMondrian(pixels, x, y, width, splitY - y);
            paintBasicMondrian(pixels, x, splitY, width, height - (splitY - y));
        }
    }

    /**
     * Fills a quadrant with a random color
     */
    private void fillRegion(Color[][] pixels, int x, int y, int width, int height) {
        Color color = getRandomColor();
        for (int i = x + 1; i < x + width - 1; i++) {
            for (int j = y + 1; j < y + height - 1; j++) {
                pixels[i][j] = color;
            }
        }
    }

    /**
     * Generates a random color to be used for creating basic Mondrian Art
     */
    private Color getRandomColor() {
        Color[] colors = {Color.RED, Color.CYAN, Color.YELLOW, Color.WHITE};
        int random = rand.nextInt(colors.length);
        return colors[random];
    }

    /**
     * Creates a complex Mondrian Art piece with the given array of pixels 
     *
     * @throws an IllegalArgumentException if the height and width aren't the specified number
     */
    public void paintComplexMondrian(Color[][] pixels) {
        if (pixels.length < 300 || pixels[0].length < 300) {
            throw new IllegalArgumentException("The width and length should be >= 300 px");
        }
        paintComplexMondrian(pixels, 0, 0, pixels.length, pixels[0].length);
    }

    /**
     * Helper method for creating a complex Mondrian Art piece. Splits the 
     * canvas into smaller quadrants and paints them a color based on their location 
     */
    private void paintComplexMondrian(Color[][] pixels, int x, int y, int width, int height) {
        if (width < pixels.length / 4 && height < pixels[0].length / 4) {
            fillRegionBasedOnLocation(pixels, x, y, width, height);
        } else if (width >= pixels.length / 4 && height >= pixels[0].length / 4) {
            int splitX = x + rand.nextInt(width);
            int splitY = y + rand.nextInt(height);
            paintComplexMondrian(pixels, splitX, y, width - (splitX - x), splitY - y);
            paintComplexMondrian(pixels, x, y, splitX - x, splitY - y);
            paintComplexMondrian(pixels, splitX, splitY, width - (splitX - x), 
                height - (splitY - y));
            paintComplexMondrian(pixels, x, splitY, splitX - x, height - (splitY - y));
        } else if (width >= pixels.length / 4) {
            int splitX = x + rand.nextInt(width);
            paintComplexMondrian(pixels, x, y, splitX - x, height);
            paintComplexMondrian(pixels, splitX, y, width - (splitX - x), height);
        } else {
            int splitY = y + rand.nextInt(height);
            paintComplexMondrian(pixels, x, y, width, splitY - y);
            paintComplexMondrian(pixels, x, splitY, width, height - (splitY - y));
        }
    }

    /**
     * Fills a quadrant a certain color based on where it is
     */
    private void fillRegionBasedOnLocation(Color[][] pixels, int x, int y, int width, int height) {
        Color color = getColorBasedOnLocation(y, pixels[0].length);
        for (int i = x + 1; i < x + width - 1; i++) {
            for (int j = y + 1; j < y + height - 1; j++) {
                pixels[i][j] = color;
            }
        }
    }

    /**
     * Generates a color based on where the region is to be used
     * for creating complex Modrian Art
     */
    private Color getColorBasedOnLocation(int x, int totalWidth) {
        int quadrant = totalWidth / 3;
        Color color = Color.RED;
        if (x <= (quadrant * 2) && x >= quadrant) {
            color = Color.GREEN;
        } else if (x > (quadrant * 2)){
            color = Color.BLUE;
        }
        return color;
    }
}
