import java.util.Scanner;

public class ConsoleManager {

    public Thread consoleListener;

    void listen(Shell controller) {

        if (consoleListener != null) {
            return;
        }
        consoleListener = new Thread(() -> {

            Scanner console = new Scanner(System.in);

            while (true) {

                if (console.hasNext()) {

                    String[] command = console.nextLine().split("\\s+");

                    switch (command[0].toLowerCase()) {
                        case "switch":
                        case "s":
                            controller.switchGuesser();
                            break;
                        case "new":
                        case "n":
                            controller.startNewGame();
                            break;
                        case "move":
                        case "m":
                            if (controller.isMachineGuessing()) {
                                if (command.length == 5) {
                                    ColorCode cc = new ColorCode(controller.NUMBER_SLOTS);
                                    try {
                                        for (byte i = 1; i < command.length; i++) {
                                            cc.set((byte) (i - 1), Byte.parseByte(command[i]));
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    controller.humanMove(cc);
                                }
                            } else {
                                System.out.println("use: move a b c d");
                            }
                            break;
                        case "eval":
                        case "e":
                            if (!controller.isMachineGuessing()) {
                                if (command.length == 3) {
                                    try {
                                        controller.processEval(controller.history.get(controller.getMoveCount() - 1), Byte.parseByte(command[1]), Byte.parseByte(command[2]));
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                    System.out.println("use: eval b w");
                                }
                            }
                            break;
                        case "help":
                        case "h":
                            System.out.println("Nutze folgende Kommandos:");
                            System.out.println("    NEW -> um ein neues Spiel zu starten");
                            System.out.println("    SWITCH -> um ein neues Spiel zu starten und einen neuen 'Codierer' zu bestimmen");
                            System.out.println("    MOVE [a] [b] [c] [d] -> um einen Zug zu machen (nutze nur die Zahlen 0-5)");
                            System.out.println("    EVAL [b] [w] -> um einen Zug zu bewerten (erstes Argument, für die Anzahl der richtigen Zahlen an der richtigen Position und\nzweites Argument für die Anzahl der richtigen Zahlen an der falschen Position)");
                            System.out.println("    HELP -> um diese Nachricht zu zeigen");
                            System.out.println("    QUIT -> um das Programm zu beenden");
                            break;
                        case "quit":
                        case "q":
                            controller.quit();
                    }

                }
            }
        });
        consoleListener.start();

    }

}
