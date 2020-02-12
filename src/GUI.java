import processing.core.PApplet;

public class GUI extends PApplet {

    @Override
    public void setup() {
        size(width / 5, height);
    }

    @Override
    public void draw() {
        line(0, 0, pixelWidth, pixelHeight);
    }


}
