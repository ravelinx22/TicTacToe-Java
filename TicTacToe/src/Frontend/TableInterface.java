package Frontend;
import Backend.*;
import javax.swing.*;
import java.awt.*;


public class TableInterface extends JFrame 
{

	public TableInterface()
	{
		// Starting show of the window.
		setSize(400,400);
		setTitle("Tic Tac Toe");
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setBackground(Color.BLACK);
		setLayout(new GridLayout(3,3));
		this.getContentPane().setBackground(Color.WHITE);
	}


	public static void main(String args[])
	{
		//Creates and makes visible the window.
		TableInterface window = new TableInterface();
		window.setVisible(true);
	}
}
