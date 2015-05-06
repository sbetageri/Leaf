import java.util.ArrayList;

/**
 * Created by sri on 5/5/15.
 */
public class OutlineChar {
    // Gets the outline of the character from the Image;
    // The outline of the character is stored in an ArrayList
    ArrayList<Pixel> points;

    OutlineChar() {
        points = new ArrayList<Pixel>();
    }

    OutlineChar(CharImage img, PixelCharacter boundary) {
        this();
        Pixel start = boundary.start;
        Pixel end = boundary.end;
        for(int i = start.i; i <= end.i; i++)
            for(int j = start.j; j <= end.j; j++)
                if(img.isBoundary(i, j))
                    points.add(new Pixel(i, j));
    }

    ArrayList<Pixel> getPoints() {
        return points;
    }
}
