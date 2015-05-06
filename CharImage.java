import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by sri on 5/5/15.
 */
public class CharImage {
    BufferedImage img;
    int height;
    int width;

    static int white;
    static int black;
    static int threshold;

    static {
        white = Color.white.getRGB();
        black = Color.black.getRGB();
        threshold = new Color(127, 127, 127).getRGB();
    }

    void writeToDisk(String path, PixelCharacter point) throws IOException {
        BufferedImage temp = img.getSubimage(point.start.i, point.start.j, point.getWidth(), point.getHeight());
        ImageIO.write(temp, "bmp", new File(path));
    }

    CharImage() {
        img = null;
        height = 0;
        width = 0;
    }

    CharImage(int width, int height) {
        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        this.height = height;
        this.width = width;
    }

    CharImage(String source) throws IOException {
        this(new File(source));
    }

    CharImage(File obj) throws IOException {
        img = ImageIO.read(obj);
        height = img.getHeight();
        width = img.getWidth();
    }


    boolean isBelowThreshold(int i, int j) {
        if(getColor(i, j) < CharImage.threshold)
            return true;
        return false;
    }

    int getColor(int x, int y) {
        return img.getRGB(x, y);
    }

    void setColor(int x, int y, int rgb) {
        img.setRGB(x, y, rgb);
    }

    void writeDisk(String path) throws IOException {
        ImageIO.write(img, "bmp", new File(path));
    }

    boolean isWithin(int i, int j) {
        // returns true if i and j are within the boundary of the ******  IMAGE  *********
        if(i >= 0 && j <= height)
            return true;
        return false;
    }

    boolean isBoundary(int i, int j) {
        if(!isBlack(i, j))
            return false;
        if(isWithin(i, j - 1))
            if(isWhite(i, j - 1))
                return true;
        if(isWithin(i - 1, j))
            if(isWhite(i - 1, j))
                return true;
        if(isWithin(i, j + 1))
            if(isWhite(i, j + 1))
                return true;
        if(isWithin(i + 1, j))
            if(isWhite(i + 1, j))
                return true;
        return false;
    }

    boolean isBlack(int i, int j) {
        if(img.getRGB(i, j) == CharImage.black)
            return true;
        return false;
    }

    boolean isWhite(int i, int j) {
        if(img.getRGB(i, j) == CharImage.white)
            return true;
        return false;
    }
}
