package Frontend;
import Backend.*;
import javax.swing.*;
import java.awt.*;


public class TableInterface extends JFrame 
{
	private JButton square1;
	private JButton square2;
	private JButton square3;
	private JButton square4;
	private JButton square5;
	private JButton square6;
	private JButton square7;
	private JButton square8;
	private JButton square9;

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
		
		//Initialize the button
		square1 = new JButton();
		square2 = new JButton();
		square3 = new JButton();
		square4 = new JButton();
		square5 = new JButton();
		square6 = new JButton();
		square7 = new JButton();
		square8 = new JButton();
		square9 = new JButton();
		
		//Add to the layout the components
		add(square1);
		add(square2);
		add(square3);
		add(square4);
		add(square5);
		add(square6);
		add(square7);
		add(square8);
		add(square9);
	}


	public static void main(String args[])
	{
		//Creates and makes visible the window.
		TableInterface window = new TableInterface();
		window.setVisible(true);
	}
}
