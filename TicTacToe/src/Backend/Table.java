package Backend;

public class Table {

	/**
	 * Represents the starting point of the square.
	 */
	public final static int START = 0;

	/**
	 * Represents a cross in the square.
	 */
	public final static int CROSS = 1;

	/**
	 * Represents a circle in the square.
	 */
	public final static int CIRCLE = 2;


	/**
	 * Squares
	 */
	private int squares[][];

	/**
	 * Constructor.
	 */
	public Table(int x, int y)
	{
		// Initialize the two dimensional array
		squares = new int[x][y];

		//Start each square to the start value.
		for(int i = 0; i < squares.length; i++)
		{
			for(int j = 0; j < squares[i].length; j++)
			{
				squares[i][j] = START; 
			}
		}
	}

	/**
	 * Play - change state of a square.
	 * @param posX Position in x.
	 * @param posY Position in y.
	 * @param figure What figure is going to be in the square.
	 */
	public void play(int posX, int posY, int figure) throws Exception
	{
		if(squares[posX][posY] != START) throw new Exception("You already played there");

		else
		{
			squares[posX][posY] = figure;			
		}
	}

	/**
	 * Is a square selected 
	 * @param posX Position in X
	 * @param posY Position in Y
	 * @return True if the figure in in the square, false if not.
	 */
	public boolean isSelected(int posX, int posY)
	{
		return squares[posX][posY] != START;
	}

	/**
	 * Has the player won already
	 * @return true if the player has won, false if not.
	 */
	public boolean hasWon()
	{
		//Coordinates of the center, (1,1) so I just need one attribute.
		int center = 1;
		boolean ans = false;

		if(squares[center][center] != 0 && squares[center][center] == squares[center][center+1] && squares[center][center] == squares[center][center-1])
		{
			ans = true;
		}
		else if(squares[center][center] != 0 && squares[center][center] == squares[center-1][center] && squares[center][center] == squares[center+1][center])
		{
			ans = true;
		}
		else if(squares[center][center] != 0 && squares[center][center] == squares[center-1][center-1] && squares[center][center] == squares[center+1][center+1])
		{
			ans = true;
		}
		else if(squares[center][center] != 0 && squares[center][center] == squares[center+1][center-1] && squares[center][center] == squares[center-1][center+1])
		{
			ans = true;
		}
		else if(squares[center][center-1] != 0 && squares[center][center-1] == squares[center-1][center-1] && squares[center][center-1] == squares[center+1][center-1])
		{
			ans = true;
		}
		else if(squares[center][center+1] != 0 && squares[center][center+1] == squares[center-1][center+1] && squares[center][center+1] == squares[center+1][center+1])
		{
			ans = true;
		}
		else if(squares[center+1][center] != 0 && squares[center+1][center] == squares[center+1][center-1] && squares[center+1][center] == squares[center+1][center+1])
		{
			ans = true;
		}
		else if(squares[center-1][center] != 0 && squares[center-1][center] == squares[center-1][center-1] && squares[center-1][center] == squares[center-1][center+1])
		{
			ans = true;
		}


		return ans;
	}

	/**
	 * Is the game in a tie.
	 */
	public boolean isItTie()
	{
		boolean ans = true;

		for(int i = 0; i < squares.length; i++)
		{
			for(int j = 0; j < squares[i].length; j++)
			{
				if(squares[i][j] == START) ans = false;
			}
		}

		return ans;
	}

	/**
	 * Resets the table to the initial state.
	 */
	public void resetTable()
	{
		for(int i = 0; i < squares.length; i++)
		{
			for(int j = 0; j < squares[i].length; j++)
			{
				squares[i][j] = START;
			}
		}
	}

}
