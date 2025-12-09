package code;

import image.APImage;
import image.Pixel;

public class ImageManipulation {

    /** CHALLENGE 0: Display Image */
    public static void main(String[] args) {
        String fileName = "src/cyberpunk2077.jpg";
        APImage image = new APImage(fileName);
        image.draw();
        grayScale(fileName);
        blackAndWhite(fileName);
        edgeDetection(fileName);
        reflectImage(fileName);
        rotateImage(fileName);

    }
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
    public static void edgeDetection(String pathToFile) {
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
                int threshold = 30;

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
        int height = image.getHeight();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width / 2; x++) {
                Pixel left  = image.getPixel(x, y);
                Pixel right = image.getPixel(width - 1 - x, y);

                int tempR = left.getRed();
                int tempG = left.getGreen();
                int tempB = left.getBlue();

                left.setRed(right.getRed());
                left.setGreen(right.getGreen());
                left.setBlue(right.getBlue());

                right.setRed(tempR);
                right.setGreen(tempG);
                right.setBlue(tempB);
            }
        }

        image.draw();
    }

    /** CHALLENGE FIVE: Rotate */
public static void rotateImage(String pathToFile) {
    APImage original = new APImage(pathToFile);
    int oldWidth  = original.getWidth();
    int oldHeight = original.getHeight();

    APImage rotated = new APImage(oldHeight, oldWidth);

    for (int oldY = 0; oldY < oldHeight; oldY++) {
        for (int oldX = 0; oldX < oldWidth; oldX++) {
            Pixel p = original.getPixel(oldX, oldY);
            int newX = oldHeight - 1 - oldY;  
            int newY = oldX;                

            rotated.getPixel(newX, newY).setRed(p.getRed());
            rotated.getPixel(newX, newY).setGreen(p.getGreen());
            rotated.getPixel(newX, newY).setBlue(p.getBlue());
        }
    }

    rotated.draw();
}
}