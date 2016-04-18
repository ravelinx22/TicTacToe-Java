package Frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Backend.*;

public class SquareInterface extends JPanel implements ActionListener
{

	// Two dimensional array of JButtons.
	private JButton[][] squaresButtons;

	// TableInterface Attribute
	private TableInterface inter;

	// Table
	private Table tab;

	/**
	 * Constructor 
	 * @param x Size in x
	 * @param y Size in y
	 */
	public SquareInterface(int x, int y)
	{
		// Initial estate of the panel
		setLayout(new GridLayout(x,y));
		squaresButtons = new JButton[x][y];
		tab = new Table(TableInterface.CORX,TableInterface.CORY);

		//Initialize the buttons and add them to the frame.
		for(int i = 0; i < squaresButtons.length;i++)
		{
			for(int j = 0; j < squaresButtons[i].length; j++)
			{
				squaresButtons[i][j] = new JButton();

				//Event handeling
				squaresButtons[i][j].addActionListener(this);
				squaresButtons[i][j].setActionCommand(i +"," +j);

				// Add buttons to the panel layout.
				add(squaresButtons[i][j]);
			}
		}
	}

	/**
	 * User plays
	 * @param x Coordinate x
	 * @param y Coordinate y
	 */
	public void playUser(int x, int y)
	{
		try
		{
			tab.play(x, y, Table.CROSS);
			squaresButtons[x][y].setEnabled(false);
			squaresButtons[x][y].setText("X");
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(this, "Error", ex.getMessage(), JOptionPane.ERROR_MESSAGE); 
		}
	}

	/**
	 * AI plays
	 */
	public void playAI()
	{
		boolean played = false;

		while(!played)
		{
			int x = StdRandom.uniform(0, 3);
			int y = StdRandom.uniform(0, 3);
			
			if(tab.isSelected(x, y))
				continue;

			played = true;

			try
			{
				tab.play(x,y,Table.CIRCLE);
				squaresButtons[x][y].setEnabled(false);
				squaresButtons[x][y].setText("O");
			}
			catch(Exception e)
			{
				System.out.println("x");
			}
		}
	}

	/**
	 * Actions of the buttons.
	 * @param Event that happen.
	 */
	public void actionPerformed(ActionEvent e) {

		String command = e.getActionCommand();
		String[] coordinates = command.split(",");
		int x = Integer.parseInt(coordinates[0]);
		int y = Integer.parseInt(coordinates[1]);

		
		playUser(x,y);
		
		if(tab.hasWon())
		{
			JOptionPane.showMessageDialog(this, "Winner", "Congratulations you won!", JOptionPane.INFORMATION_MESSAGE); 
		}
		else
		{
			playAI();
			if(tab.hasWon())
			{
				JOptionPane.showMessageDialog(this, "Looser", "The AI won", JOptionPane.INFORMATION_MESSAGE); 
			}
		}
	    
		
	    System.out.println(tab.hasWon());
	}
}
