package Frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Backend.*;

public class SquareInterface extends JPanel implements ActionListener
{
	/**
	 * Two dimensional array of JButtons.
	 */
	private JButton[][] squaresButtons;

	/**
	 * Instance of the class Table.
	 */
	private Table tab;

	/**
	 * TableInterface class instance.
	 */
	private TableInterface principal;

	/**
	 * Constructor 
	 * @param x Size in x
	 * @param y Size in y
	 */
	public SquareInterface(int x, int y, TableInterface p)
	{
		// Initial estate of the panel
		setLayout(new GridLayout(x,y));
		squaresButtons = new JButton[x][y];
		tab = new Table(TableInterface.CORX,TableInterface.CORY);
		principal = p;

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
	 * Disables all buttons.
	 */
	public void setAllButtonsEnabledTo(boolean state)
	{
		for(int i = 0; i < squaresButtons.length; i++)
		{
			for(int j = 0; j < squaresButtons[i].length; j++)
			{
				squaresButtons[i][j].setEnabled(state);
			}
		}
	}

	/**
	 * Resets interface game buttons and backend squares.
	 */
	public void resetGame()
	{
		tab.resetTable();

		for(int i = 0; i < squaresButtons.length; i++)
		{
			for(int j = 0; j < squaresButtons[i].length; j++)
			{
				squaresButtons[i][j].setText("");
				squaresButtons[i][j].setEnabled(true);
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
			JOptionPane.showMessageDialog(this, "Congratulations you won", "Winner", JOptionPane.INFORMATION_MESSAGE);
			int messageResult = JOptionPane.showConfirmDialog(this, "Would you like to start again?", "Tic Tac Toe", y);
			setAllButtonsEnabledTo(false);

			if(messageResult == JOptionPane.YES_OPTION)
			{
				resetGame();
			}
			else
			{
				principal.dispose();	
			}

		}
		else
		{		
			if(tab.isItTie())
			{
				JOptionPane.showMessageDialog(this, "Sorry the game end in a tie", "Tie", JOptionPane.INFORMATION_MESSAGE); 
				int messageResult = JOptionPane.showConfirmDialog(this, "Would you like to start again?", "Tic Tac Toe", y);
				setAllButtonsEnabledTo(false);

				if(messageResult == JOptionPane.YES_OPTION)
				{
					resetGame();
				}
				else
				{
					principal.dispose();
				}
			}
			else
			{
				playAI();
				if(tab.hasWon())
				{
					JOptionPane.showMessageDialog(this, "The AI won", "Looser", JOptionPane.INFORMATION_MESSAGE); 
					int messageResult = JOptionPane.showConfirmDialog(this, "Would you like to start again?", "Tic Tac Toe", y);
					setAllButtonsEnabledTo(false);

					if(messageResult == JOptionPane.YES_OPTION)
					{
						resetGame();
					}
					else
					{
						principal.dispose();
					}
				}
			}
		}
	}
}
