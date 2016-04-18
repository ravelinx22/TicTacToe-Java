package Frontend;
import Backend.*;
import javax.swing.*;
import java.awt.*;

public class TableInterface extends JFrame 
{
	/**
	 * Constant that represents the two dimensional array dimension in X
	 */
	public final static int CORX = 3;
	
	/**
	 * Constant that represents the two dimensional array dimension in X
	 */
	public final static int CORY = 3;

	/**
	 * Panel that is going to be in the table
	 */
	private SquareInterface square;
	
	/**
	 * Constructor of the class TableInterface
	 */
	public TableInterface()
	{
		square = new SquareInterface(CORX, CORY, this);
		
		// Starting show of the window.
		setSize(400,400);
		setTitle("Tic Tac Toe");
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setBackground(Color.WHITE);
		setLayout(new BorderLayout());
		
		//Add panel.
		add(square);
	}
	
	/**
	 * Main method
	 * @param args Arguments
	 */
	public static void main(String args[])
	{
		//Creates and makes visible the window.
		TableInterface window = new TableInterface();
		window.setVisible(true);
	}
}
