// View for Java 2048
// Author: Erika
// This class interacts with the user by printing the board to
// the screen along with the current score. It is the only class
// that should System.out.print(). The View should not talk to the Model.

public class J2048View {
	// Instance variables
	private int DIM;
	// Constructors
	// creates model with parameter
	public J2048View(int dimension)
	{
		this.DIM = dimension;
	}
	// creates model with no parameter
	public J2048View()
	{
		this(4);
	}
	// Methods
	// Draws the board.
	public void draw(int[][] board)
	{
		for (int i = 0; i < DIM; i++)
		{
			for (int j = 0; j < DIM; j++)
			{
				// print - if empty spot (equals 0)
				if (board[i][j] == 0)
				{
					System.out.printf("%4c", '-');
				}
				// print number
				else
				{
					System.out.printf("%4d", board[i][j]);
				}
			}
			System.out.println();
		}
		System.out.println();
	}
	// asks user to type in a key again
	public void printRetype()
	{
		System.out.println("Command not recognized. Please try again.");
	}
	// prints score
	public void printScore(double score)
	{
		System.out.println("Current Score: " + score);
	}
	// print losing screen
	public void printLose(double score)
	{
		System.out.println("Game Over!");
		System.out.println("Final Score: " + score);
	}
}