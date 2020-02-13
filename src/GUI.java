import processing.core.PApplet;

public class GUI extends PApplet {

    private Shell shell;

    public void setShell(Shell shell) {
        this.shell = shell;
    }

    @Override
    public void settings() {
        size(displayWidth / 4, displayHeight - 100);
        frameRate = 30;
    }

    @Override
    public void draw() {

        background(255);

        stroke(0);
        strokeWeight(2);
        noFill();

        for (int i = 1; i <= shell.MAX_MOVES; i++) {

            rect(0, height - (i * 100), width, height);

            for (int j = 1; j <= shell.NUMBER_SLOTS; j++) {
                ellipse((float) (width / shell.NUMBER_SLOTS) * (j - .5f), height - (i * 100) - 50, 25, 25);
            }
        }

    }

}
