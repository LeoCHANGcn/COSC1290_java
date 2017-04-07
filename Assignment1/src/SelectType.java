import java.util.Scanner;

public class SelectType {
	private Scanner scanner;
	private int input;
	private String gameType;
	private boolean haveChosenGame = true;

	public SelectType() {
		do {
			scanner = new Scanner(System.in);
			System.out.println("Please input a game type.(1,2 or 3)\n1.Swimming\n2.Cycling\n3.Running");

			try {

				input = scanner.nextInt();
				if (input == 1) {
					gameType = "Swimming";
					System.out.println("\n[" + gameType + " Selected]\n");
					haveChosenGame = false;
				} else if (input == 2) {
					gameType = "Cycling";
					System.out.println("\n[" + gameType + " Selected]\n");
					haveChosenGame = false;
				} else if (input == 3) {
					gameType = "Running";
					System.out.println("\n[" + gameType + " Selected]\n");
					haveChosenGame = false;
				} else {
					System.out.println("\n[Please input the menu number. Try again.]\n");
				}

			} catch (Exception ex) {
				System.out.println("\n[It is not a number. Try again.]\n");
			}
		} while (haveChosenGame);

	}

	public String getGameType() {
		return gameType;
	}

	public boolean gethaveChosenGame() {
		return haveChosenGame;
	}

}