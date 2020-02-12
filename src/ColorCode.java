import java.util.Random;

public class ColorCode {

    final private byte[] COLORS;

    public ColorCode(byte numberSlots, byte numberColors) {
        COLORS = new byte[numberSlots];
        Random random = new Random();
        for (int i = 0; i < COLORS.length; i++) {
            COLORS[i] = (byte) random.nextInt(numberSlots);
        }
    }

    public ColorCode(byte numberSlots) {
        COLORS = new byte[numberSlots];
    }

    public void set(byte x, byte y){
        if(x > COLORS.length)
            return;
        COLORS[x] = y;
    }

    public byte[] getColors() {
        return COLORS;
    }

}
