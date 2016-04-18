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
	 * Play - change state of a square.
	 * @param posX Position in x.
	 * @param posY Position in y.
	 * @param figure What figure is going to be in the square.
	 */
	private void play(int posX, int posY, int figure)
	{
		squares[posX][posY] = figure;
	}
	
	/**
	 * Is a square selected with an especific figure.
	 * @param posX Position in X
	 * @param posY Position in Y
	 * @param figure Figure that is expected to be in the square
	 * @return True if the figure in in the square, false if not.
	 */
	private boolean isSelectedWith(int posX, int posY, int figure)
	{
		return squares[posX][posY] == figure;
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
