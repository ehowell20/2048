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
	public J2048Model(int dimension)
	{
		this.DIM = dimension;
	}
	// creates model with no parameter
	public J2048Model()
	{
		this(4);
	}
	// Methods
	// Fill board with two 2s in random locations.
	public void init(int[] board)
	{
		// pick random number from 1 to DIM
		int random = (int)(Math.random() * DIM);
		// put the number 2 in that index
		board[random] = 2;
		// pick random number from 1 to DIM until it's different than the first random number
		int random2;
		do
		{
			random2 = (int)(Math.random() * DIM);
		}
		while (random2 == random);
		// put the number 2 in that index
		board[random2] = 2;
	}

	// Spawn a new value in an empty location in the board.
	// 90% of the time, it should be a 2.
	// 10% of the time, it should be a 4.
	public void spawn(int[] board)
	{
		// value of number being placed (2 or 4)
		int value;
		// random number from 1 to 10
		int random = (int)(Math.random() * 10) + 1;
		// if the number is 10, spawn a 4
		if (random == 10)
		{
			value = 4;
		}
		// if the number is anything from 1 to 9, spawn a 2
		else
		{
			value = 2;
		}
		int random2;
		// generate a random index number until the index refers to an empty spot
		do
		{
			random2 = (int)(Math.random() * DIM);
		}
		while (board[random2] != 0);
		// spawn value in a random empty space
		board[random2] = value;
	}

	// returns score
	public double getScore()
	{
		return score;
	}

	// return true if lost, player lost if
	// no empty tiles on the board and
	// no 2 same tiles are vertically or horizontally next to each other
	//public boolean playerLost(int[] board);

	// shifts the board right
	public void shiftRight(int[] board)
	{
		boolean skip = false;
		for (int i = 0; i < DIM - 1; i++)
		{
			// combine numbers
			if (board[i+1] == board[i])
			{
				if (skip)
				{
					skip = !skip;
					break;
				}
				// double right value and change left value to 0
				board[i+1] *= 2;
				board[i] = 0;
				// dont merge with next number
				skip = !skip;
			}
			// move numbers
			else if (board[i+1] == 0 && board[i] != 0)
			{
				// switch values if right one is 0 and left one is not
				board[i+1] = board[i];
				board[i] = 0;
			}
		}
	}
	// shift right
	// shift left
	public void shiftLeft(int[] board)
	{
		for (int i = 0; i < DIM; i++)
		{
			// changeable i value
			int k = i;
			boolean skip = false;
			// look for non 0 numbers and not in first position
			if (board[i] != 0 && i != 0)
			{
				// scan backwards for a non 0
				for (int j = i-1; j > -1; j--)
				{
					// if 0, switch numbers
					if (board[j] == 0)
					{
						board[j] = board[k];
						board[k] = 0;
					}
					// if same number, merge
					else if (board[j] == board[k])
					{
						// if skipping this merge, reset skip to false to allow next merge
						if (skip)
						{
							skip = !skip;
							// don't merge this time
							break;
						}
						board[j] += board[k];
						board[k] = 0;
						skip = true;
					}
					// decrease k by 1 to keep the values being compared next to each other
					// use int k instead of int i in order not to mess up the loop
					k--;
				}
			}
		}
	}
}