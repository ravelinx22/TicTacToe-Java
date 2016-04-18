package Frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;

import Backend.*;

public class SquareInterface extends JPanel implements ActionListener
{
	/**
	 * Two dimensional array of JButtons.
	 */
	private JLabel[][] squaresLabels;

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
		squaresLabels = new JLabel[x][y];
		tab = new Table(TableInterface.CORX,TableInterface.CORY);
		principal = p;

		//Initialize the labels and add them to the frame.
		for(int i = 0; i < squaresLabels.length;i++)
		{
			for(int j = 0; j < squaresLabels[i].length; j++)
			{
				ImageIcon icon = new ImageIcon(new ImageIcon("./Data/Circle.png").getImage().getScaledInstance(80,80, Image.SCALE_DEFAULT));
				squaresLabels[i][j] = new JLabel(icon);
				
				// Add buttons to the panel layout.
				add(squaresLabels[i][j]);
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
			squaresLabels[x][y].setEnabled(false);
			squaresLabels[x][y].setText("X");
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
				squaresLabels[x][y].setEnabled(false);
				squaresLabels[x][y].setText("O");
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
		for(int i = 0; i < squaresLabels.length; i++)
		{
			for(int j = 0; j < squaresLabels[i].length; j++)
			{
				squaresLabels[i][j].setEnabled(state);
			}
		}
	}

	/**
	 * Resets interface game buttons and backend squares.
	 */
	public void resetGame()
	{
		tab.resetTable();

		for(int i = 0; i < squaresLabels.length; i++)
		{
			for(int j = 0; j < squaresLabels[i].length; j++)
			{
				squaresLabels[i][j].setText("");
				squaresLabels[i][j].setEnabled(true);
			}
		}
	}

	/**
	 * Method that paints the lines of the tic tac toe.
	 */
	public void paintComponent(Graphics g)
	{
		Graphics2D graph = (Graphics2D) g;
		int height = this.getHeight();
		int width = this.getWidth();

		graph.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		Shape drawLine1 = new Line2D.Double(15, (height/3), width-15, (height/3));
		Shape drawLine2 = new Line2D.Double(15, ((height/3)*2), width-15, ((height/3)*2));
		Shape drawLine3 = new Line2D.Double((width/3), 15, (width/3), height-15);
		Shape drawLine4 = new Line2D.Double(((width/3)*2), 15, ((width/3)*2), height-15);

		graph.setStroke(new BasicStroke(5));
		graph.setPaint(Color.BLACK);

		graph.draw(drawLine1);
		graph.draw(drawLine2);
		graph.draw(drawLine3);
		graph.draw(drawLine4);
		
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
