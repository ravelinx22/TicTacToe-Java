package Frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SquareInterface extends JPanel implements ActionListener
{

	// Two dimensional array of JButtons.
	private JButton[][] squaresButtons;

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
	 * Actions of the buttons.
	 * @param Event that happen.
	 */
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
