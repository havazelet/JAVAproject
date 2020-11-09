package Test;

import Renderer.ImageWriter;
import org.junit.Test;

import java.awt.*;
import java.util.Random;


public class ImageWriterTest {
//
//    @Test
//
//    public void writeImageTest() {
//
//        ImageWriter imageWriter = new ImageWriter("Image writer test", 500, 500, 1, 1);
//        for (int i = 0; i < 499; i++)
//            for (int j = 0; j < 499; j++) {
//                if ((i % 50 == 0) || (j % 50 == 0))
//                    imageWriter.writePixel(i, j,new Color(255,255,255));
//            }
//
//        imageWriter.writeToimage();
//
//    }
    @Test

    public void writeImageTest() {

        ImageWriter imageWriter = new ImageWriter("Image writer test", 500, 500, 1, 1);

        Random rand = new Random();

        for (int i = 0; i < imageWriter.getHeight(); i++) {

            for (int j = 0; j < imageWriter.getWidth(); j++) {

                if (i % 25 == 0 || j % 25 == 0 || i == j || i == imageWriter.getWidth() - j) {
                    imageWriter.writePixel(j, i, 0, 0, 0); // Black
                } else if (i >= 200 && i <= 300 && j >= 200 && j <= 300) {
                    imageWriter.writePixel(j, i, 255, 0, 0); // Red
                } else if (i >= 150 && i <= 350 && j >= 150 && j <= 350) {
                    imageWriter.writePixel(j, i, 0, 255, 0); // Green
                } else if (i >= 100 && i <= 400 && j >= 100 && j <= 400) {
                    imageWriter.writePixel(j, i, 0, 0, 255); // Blue
                } else if (i >= 50 && i <= 450 && j >= 50 && j <= 450) {
                    imageWriter.writePixel(j, i, 255, 255, 0); // Yellow
                } else {
                    imageWriter.writePixel(j, i, rand.nextInt(255), rand.nextInt(255),
                            rand.nextInt(255)); // Random
                }
            }

        }
        imageWriter.writeToimage();
    }

}
