import processing.core.PApplet;

public class GUI extends PApplet{

    GUI() {
        this.start();
    }

    @Override
    public void setup() {
        size(displayWidth / 5, displayHeight);
        frameRate = 30;
    }

    @Override
    public void draw() {
        line(0, 0, width, height);
    }


}
