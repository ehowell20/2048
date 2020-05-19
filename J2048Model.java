// Model for Java 2048
// Author: Erika
// This class initializes the pieces and also
// spawns a new piece every time the board is moved.
// The Model also moves the pieces on the board and
// determines if the game has been won.

import java.util.*;

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
	public void init(int[][] board)
	{
		// pick random number from 1 to DIM for column
		int randomCol = (int)(Math.random() * DIM);
		// pick random number from 1 to DIM for row
		int randomRow = (int)(Math.random() * DIM);
		// put the number 2 in that index
		board[randomRow][randomCol] = 2;
		// pick random numbers from 1 to DIM until it's different than the first random numbers
		int randomCol2;
		int randomRow2;
		do
		{
			randomCol2 = (int)(Math.random() * DIM);
			randomRow2 = (int)(Math.random() * DIM);
		}
		while (randomCol2 == randomCol && randomRow2 == randomRow);
		// put the number 2 in that index
		board[randomRow2][randomCol2] = 2;
	}

	// Spawn a new value in an empty location in the board.
	// 90% of the time, it should be a 2.
	// 10% of the time, it should be a 4.
	public void spawn(int[][] board)
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
		int randomCol;
		int randomRow;
		// generate a random col and row number until the col and row refer to an empty spot
		// check for empty spaces
		searchZero:
		
		for (int i = 0; i < DIM; i++)
		{
			for (int j = 0; j < DIM; j++)
			{
				// if empty space, allow number to spawn
				if (board[i][j] == 0)
				{
					do
					{
						randomRow = (int)(Math.random() * DIM);
						randomCol = (int)(Math.random() * DIM);
					}
					// do while random spot not empty and 
					while (board[randomRow][randomCol] != 0);
					// spawn value in a random empty space
					board[randomRow][randomCol] = value;
					break searchZero;
				}
			}
		}
	}

	// returns score
	public int getScore()
	{
		return score;
	}

	// return true if lost, player lost if
	// no empty tiles on the board and
	// no 2 same tiles are vertically or horizontally next to each other
	public boolean playerLost(int[][] board)
	{
		// check left to right and up to down
		for (int i = 0; i < DIM; i++)
		{
			for (int j = 0; j < DIM-1; j++)
			{
				// return false if 0 (empty) tile is found
				if (board[i][j] == 0 || board[i][j+1] == 0)
				{
					return false;
				}
				// return false if same 2 tiles (horizontally)
				else if (board[i][j] == board[i][j+1])
				{
					return false;
				}
				// return false if same 2 tiles (vertically)
				else if (board[j][i] == board[j+1][i])
				{
					return false;
				}
			}
		}
		return true;
	}

	// shift right
	public void shiftRight(int[] temp)
	{
		// stops loop from scanning index after it merges
		int stopper = DIM;
		for (int i = DIM-1; i > -1; i--)
		{
			// changeable i value
			int k = i;
			// look for non 0 numbers and not in last position
			if (temp[i] != 0 && i != 3)
			{
				// scan right for a non 0
				// stop scanning if merge since merges only happen on the far right
				// which means there should be no more zeros
				for (int j = i+1; j < stopper; j++)
				{
					// if 0, switch numbers
					if (temp[j] == 0)
					{
						temp[j] = temp[k];
						temp[k] = 0;
					}
					// if same number, merge
					else if (temp[j] == temp[k])
					{
						temp[j] += temp[k];
						temp[k] = 0;
						// increase score by merged number
						score += temp[j];
						// increase to set right boundry one lower as to not merge with anything beyond it
						stopper = j;
					}
					// if didn't switch or merge, skip to comparing next 2 numbers
					else
					{
						break;
					}
					// increase k by 1 to keep the values being compared next to each other
					// use int k instead of int i in order not to mess up the loop
					k++;
				}
			}
		}
	}
	// shift left
	public void shiftLeft(int[] temp)
	{
		// stops loop from scanning index after it merges
		int stopper = -1;
		for (int i = 0; i < DIM; i++)
		{
			// changeable i value
			int k = i;
			// look for non 0 numbers and not in first position
			if (temp[i] != 0 && i != 0)
			{
				// scan left for a non 0
				// stop scanning if merge since merges only happen on the far right
				// which means there should be no more zeros
				for (int j = i-1; j > stopper; j--)
				{
					// if 0, switch numbers
					if (temp[j] == 0)
					{
						temp[j] = temp[k];
						temp[k] = 0;
					}
					// if same number, merge
					else if (temp[j] == temp[k])
					{
						temp[j] += temp[k];
						temp[k] = 0;
						// increase to set left boundry one higher as to not merge with anything beyond it
						stopper = j;
						// increase score by merged number
						score += temp[j];
					}
					// if didn't switch or merge, stop comparing
					else
					{
						break;
					}
					// decrease k by 1 to keep the values being compared next to each other
					// use int k instead of int i in order not to mess up the loop
					k--;
				}
			}
		}
	}

	// copies column or row into temp array
	public int[] tempArray(int[][] board, int[] temp, int rowcol, char rc)
	{
		// if column preferenced
		if (rc == 'c')
		{
			// copy column values into temp array
			for (int i = 0; i < DIM; i++)
			{
				temp[i] = board[i][rowcol];
			}
		}
		// if row preferenced
		else
		{
			// copy row values into temp array
			for (int i = 0; i < DIM; i++)
			{
				temp[i] = board[rowcol][i];
			}
		}
		return temp;
	}

	// applies changes to the board made in the temp array
	public void updateBoard(int[][] board, int[] temp, int rowcol, char rc)
	{
		// if column preferenced
		if (rc == 'c')
		{
			// replace board col with temp values
			for (int i = 0; i < DIM; i++)
			{
				board[i][rowcol] = temp[i];
			}
		}
		// if row preferenced
		else
		{
			// copy row values into temp array
			for (int i = 0; i < DIM; i++)
			{
				board[rowcol][i] = temp[i];
			}
		}
	}
}