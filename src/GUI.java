import processing.core.PApplet;

public class GUI extends PApplet {

    private static Shell SHELL;

    static void setShell(Shell shell) {
        SHELL = shell;
    }

    @Override
    public void settings() {
        size(displayWidth / 4, displayHeight - 100);
        frameRate = 30;
    }

    @Override
    public void setup() {
        colorMode(HSB);
    }

    @Override
    public void draw() {

        background(255);

        stroke(0);
        strokeWeight(2);

        if (SHELL.isMachineGuessing()) {
            //draw all colors
        } else {
            //evaluate black / white pins
        }

        for (int i = 1; i <= SHELL.MAX_MOVES; i++) {

            //draw colors

            noFill();
            rect(0, height - ((i + 1) * 100), width - 100, 100);
            ColorCode colorCode = SHELL.getGameState(i - 1);

            for (int j = 0; j < SHELL.NUMBER_SLOTS; j++) {
                if (colorCode != null)
                    fill((255f / SHELL.NUMBER_COLORS * colorCode.getColors()[j]), 255, 255);
                ellipse((float) ((width - 100) / SHELL.NUMBER_SLOTS) * (j + .5f), height - (i * 100) - 50, 25, 25);
            }

            //draw pins

            for (int j = 0; j < SHELL.NUMBER_SLOTS; j++) {
                if (colorCode != null) {
                    if (SHELL.getBlackPins(colorCode) >= j) {
                        fill(0, 0, 0);
                    } else if (SHELL.getWhitePins(colorCode) + SHELL.getBlackPins(colorCode) >= j) {
                        fill(0, 0, 255);
                    } else {
                        fill(0, 255, 255);
                    }
                } else noFill();
                ellipse((100f / SHELL.NUMBER_SLOTS) * (j + .5f), height - (i * 100) - 25 + SHELL.NUMBER_SLOTS % (j + 1) == 0 ? 25 : 0, 5, 5);
            }

        }

    }

}
