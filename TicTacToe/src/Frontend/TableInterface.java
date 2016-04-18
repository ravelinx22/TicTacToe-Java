package Frontend;
import Backend.*;
import javax.swing.*;
import java.awt.*;


public class TableInterface extends JFrame 
{
	// Two dimensional array of JButtons.
	private JButton[][] squaresButtons;

	//Constants that represents the two dimensional array dimensions
	public final static int CORX = 3;
	public final static int CORY = 3;

	// Connection to the backend.
	private Table table;

	public TableInterface()
	{
		table = new Table(CORX,CORY);
		
		// Starting show of the window.
		setSize(400,400);
		setTitle("Tic Tac Toe");
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setBackground(Color.BLACK);
		setLayout(new GridLayout(3,3));
		this.getContentPane().setBackground(Color.WHITE);
		squaresButtons = new JButton[CORX][CORY];

		//Initialize the buttons and add them to the frame.
		for(int i = 0; i < squaresButtons.length;i++)
		{
			for(int j = 0; j < squaresButtons[i].length; j++)
			{
				squaresButtons[i][j] = new JButton();
				add(squaresButtons[i][j]);
			}
		}

	}
	
	public static void main(String args[])
	{
		//Creates and makes visible the window.
		TableInterface window = new TableInterface();
		window.setVisible(true);
	}
}
