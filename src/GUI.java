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
    public void draw() {

        background(255);

        stroke(0);
        strokeWeight(2);

        for (int i = 0; i < SHELL.MAX_MOVES; i++) {
            noFill();
            rect(0, height - (i * 100), width, height);
            byte[] colors = null;

            if (SHELL.getGameState(i) != null) {
                colors = SHELL.getGameState(i).getColors();
            }

            for (int j = 0; j < SHELL.NUMBER_SLOTS; j++) {
                if (colors != null)
                    fill((colors[j] + 1) * (200 / SHELL.NUMBER_COLORS), (colors[j] + 2) * (120 / SHELL.NUMBER_COLORS),(colors[j]) * (255 / SHELL.NUMBER_COLORS));
                else noFill();
                ellipse((float) (width / SHELL.NUMBER_SLOTS) * (j + .5f), height - (i * 100) - 50, 25, 25);
            }
        }

    }

}
