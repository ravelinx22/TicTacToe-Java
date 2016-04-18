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
	 * Player has won?
	 */
	private boolean won;


	/**
	 * Squares
	 */
	private int squares[][];

	/**
	 * Constructor.
	 */
	public Table()
	{
		squares =  new int[][]{
			{START,START,START},
			{START,START,START},
			{START,START,START}
		};

		won = false;
	}

	/**
	 * Resets the table to the initial state.
	 */
	private void resetTable()
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
