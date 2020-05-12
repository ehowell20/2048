// Controller for Java 2048
// Author: Erika
// This class initializes the pieces and also spawns
// a new piece every time the board is moved.
import java.util.*;

public class J2048Controller {
	public static void main(String[] args) {
		int DIM = 4;
		Scanner kb = new Scanner(System.in);
		// create model
		J2048Model model = new J2048Model(DIM);
		// create view
		J2048View view = new J2048View(DIM);
		// initialize board
		int[] board = new int[DIM];
		// set up board to play
		model.init(board);
		// print board
		view.draw(board);
		char direction = kb.next().charAt(0);
		// shift board in given direction
		model.shiftRight(board, direction);
		// print board
		view.draw(board);
	}
}