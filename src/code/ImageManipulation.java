package code;

import image.APImage;
import image.Pixel;

public class ImageManipulation {

    /** CHALLENGE 0: Display Image */
    public static void main(String[] args) {
        APImage image = new APImage("cyberpunk2077.jpg");
        image.draw();
    }

    // ONLY ONE getAverageColour method (the correct one)
    private static int getAverageColour(Pixel pixel) {
        return (pixel.getRed() + pixel.getGreen() + pixel.getBlue()) / 3;
    }

    /** CHALLENGE ONE: Grayscale */
    public static void grayScale(String pathOfFile) {
        APImage image = new APImage(pathOfFile);

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Pixel p = image.getPixel(x, y);
                int avg = getAverageColour(p);
                p.setRed(avg);
                p.setGreen(avg);
                p.setBlue(avg);
            }
        }
        image.draw();
    }

    /** CHALLENGE TWO: Black and White */
    public static void blackAndWhite(String pathOfFile) {
        APImage image = new APImage(pathOfFile);

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Pixel p = image.getPixel(x, y);
                int average = getAverageColour(p);

                if (average < 128) {
                    p.setRed(0);
                    p.setGreen(0);
                    p.setBlue(0);
                } else {
                    p.setRed(255);
                    p.setGreen(255);
                    p.setBlue(255);
                }
            }
        }
        image.draw();
    }

    /** CHALLENGE THREE: Edge Detection */
    public static void edgeDetection(String pathToFile, int threshold) {
        APImage image = new APImage(pathToFile);
        APImage result = image.clone();

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {

                int currentAvg = getAverageColour(image.getPixel(x, y));

                int leftAvg = currentAvg;
                if (x > 0) {
                    leftAvg = getAverageColour(image.getPixel(x - 1, y));
                }

                int belowAvg = currentAvg;
                if (y < image.getHeight() - 1) {
                    belowAvg = getAverageColour(image.getPixel(x, y + 1));
                }

                int diffLeft  = Math.abs(currentAvg - leftAvg);
                int diffBelow = Math.abs(currentAvg - belowAvg);
                Pixel outPixel = result.getPixel(x, y);

                if (diffLeft > threshold || diffBelow > threshold) {
                    outPixel.setRed(0);
                    outPixel.setGreen(0);
                    outPixel.setBlue(0);
                } else {
                    outPixel.setRed(255);
                    outPixel.setGreen(255);
                    outPixel.setBlue(255);
                }
            }
        }
        result.draw();
    }

    /** CHALLENGE FOUR: Reflect Image */
    public static void reflectImage(String pathToFile) {
        APImage image = new APImage(pathToFile);
        int width = image.getWidth();
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < width / 2; x++) {
                Pixel left  = image.getPixel(x, y);
                Pixel right = image.getPixel(width - 1 - x, y);
                right.setRed(left.getRed());
                right.setGreen(left.getGreen());
                right.setBlue(left.getBlue());
            }
        }
        image.draw();
    }

    /** CHALLENGE FIVE: Rotate Image 90° Clockwise */
    public static void rotateImage(String pathToFile) {
        APImage original = new APImage(pathToFile);
        int oldWidth  = original.getWidth();
        int oldHeight = original.getHeight();
        APImage rotated = new APImage(oldHeight, oldWidth);

        for (int oldY = 0; oldY < oldHeight; oldY++) {
            for (int oldX = 0; oldX < oldWidth; oldX++) {
                Pixel p = original.getPixel(oldX, oldY);
                int newX = oldY;
                int newY = oldWidth - 1 - oldX;

                Pixel target = rotated.getPixel(newX, newY);
                target.setRed(p.getRed());
                target.setGreen(p.getGreen());
                target.setBlue(p.getBlue());
            }
        }
        rotated.draw();
    }
}