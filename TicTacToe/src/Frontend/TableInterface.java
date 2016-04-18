package Frontend;
import Backend.*;
import javax.swing.*;
import java.awt.*;


public class TableInterface extends JFrame 
{
	
	public TableInterface()
	{
		setSize(400,400);
		setTitle("Tic Tac Toe");
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new GridLayout(3,3));
		setLocationRelativeTo(null);
		
	}

	
	public static void main(String args[])
	{
       TableInterface window = new TableInterface();
       window.setVisible(true);
	}
}
