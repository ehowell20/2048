// Controller for Java 2048
// Author: Erika
// This class initializes the pieces and also spawns
// a new piece every time the board is moved.
import java.util.*;

public class J2048Controller {
	public static void main(String[] args) {
		int DIM = 4;
		boolean lost = false;
		boolean valid = true;
		Scanner kb = new Scanner(System.in);
		// create model
		J2048Model model = new J2048Model(DIM);
		// create view
		J2048View view = new J2048View(DIM);
		// initialize board
		int[] board = new int[DIM];
		// set up board to play
		model.init(board);
		do
		{
			// print board
			view.draw(board);
			char direction = kb.next().charAt(0);
			do
			{
				switch (direction)
				{
					case 'a':
						// shift board left
						model.shiftLeft(board);
						break;
					case 'd':
						// shift board right
						model.shiftRight(board);
						break;
					default:
						System.out.println("Command not recognized. Please try again.");
						valid = false;
				}
			}
			while(!valid);
			model.spawn(board);
		}
		while (!lost);
	}
}