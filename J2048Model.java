// Model for Java 2048
// Author: Erika
// This class initializes the pieces and also
// spawns a new piece every time the board is moved.
// The Model also moves the pieces on the board and
// determines if the game has been won.

public class J2048Model {
	// Instance variables
	private int score;
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
	// Fill board with two 2s in random locations.
	public void init(int[] board)

	// Spawn a new value in an empty location in the board.
	// 90% of the time, it should be a 2.
	// 10% of the time, it should be a 4.
	public void spawn(int[] board)
	
	// return true if lost, player lost if
	// no empty tiles on the board and
	// no 2 same tiles are vertically or horizontally next to each other
	public boolean playerLost(int[] board)
	
	// shifts the board (w up, a left, s down, d right)
	public void shift(char direction)
}