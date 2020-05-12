// View for Java 2048
// Author: Erika
//This class interacts with the user by printing the board to
// the screen along with the current score. It is the only class
// that should System.out.print(). The View should not talk to the Model.

public class J2048View {
	// Instance variables
	private int DIM;
	// Constructors
	// creates model with parameter
	public Model(int dimension)
	{
		this.DIM = dimension;
	}
	// creates model with no parameter
	public Model()
	{
		this(4);
	}
	// Methods
	// Draws the board.
	public void draw(int[] board)
}