// Controller for Java 2048
// Author: Erika
// This class initializes the pieces and also spawns
// a new piece every time the board is moved.
import java.util.*;

public class J2048Controller {
	public static void main(String[] args) {
		int DIM = 4;
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
	}
}