package Frontend;
import Backend.*;
import javax.swing.*;
import java.awt.*;


public class TableInterface extends JFrame 
{
	//Constants that represents the two dimensional array dimensions
	public final static int CORX = 3;
	public final static int CORY = 3;

	// Connection to the backend.
	private Table table;

	// Panel that is going to be in the table
	private SquareInterface square;
	
	public TableInterface()
	{
		table = new Table(CORX,CORY);
		square = new SquareInterface(CORX, CORY);
		
		// Starting show of the window.
		setSize(400,400);
		setTitle("Tic Tac Toe");
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setBackground(Color.BLACK);
		setLayout(new BorderLayout());
		
		//Add panel.
		add(square);
	}
	
	public static void main(String args[])
	{
		//Creates and makes visible the window.
		TableInterface window = new TableInterface();
		window.setVisible(true);
	}
}
