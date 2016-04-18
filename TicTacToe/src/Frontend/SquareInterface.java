package Frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;

import Backend.*;

public class SquareInterface extends JPanel implements MouseListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
				squaresLabels[i][j] = new JLabel("",JLabel.CENTER);
				// Add buttons to the panel layout.
				add(squaresLabels[i][j]);
			}
		}

		this.addMouseListener(this);
	}

	/**
	 * User plays
	 * @param x Coordinate x
	 * @param y Coordinate y
	 */
	public void playUser(int x, int y) throws Exception
	{
		ImageIcon cross = new ImageIcon(new ImageIcon("./Data/Cross.png").getImage().getScaledInstance(50,50, Image.SCALE_DEFAULT));

		tab.play(x, y, Table.CROSS);
		squaresLabels[x][y].setIcon(cross);
	}

	/**
	 * AI plays
	 */
	public void playAI()
	{
		boolean played = false;
		ImageIcon circle = new ImageIcon(new ImageIcon("./Data/Circle.png").getImage().getScaledInstance(50,50, Image.SCALE_DEFAULT));

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
				squaresLabels[x][y].setIcon(circle);
			}
			catch(Exception e)
			{
				System.out.println("x");
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
				squaresLabels[i][j].setIcon(null);
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
	 * Mouse click events
	 */
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();

		if((x >= 0 && x <= 128) && (y >= 0 && y <=120))
		{	
			playGeneral(0, 0);
		}
		else if((x >= 0 && x <= 128) && (y >= 125 && y <=244))
		{
			playGeneral(1, 0);
		}
		else if((x >= 0 && x <= 128) && (y >= 249 && y <=370))
		{
			playGeneral(2, 0);
		}
		else if((x >= 133 && x <= 259) && (y >= 0 && y <=120))
		{
			playGeneral(0, 1);
		}
		else if((x >= 133 && x <= 259) && (y >= 125 && y <=244))
		{
			playGeneral(1, 1);
		}
		else if((x >= 133 && x <= 259) && (y >= 249 && y <=370))
		{
			playGeneral(2, 1);
		}
		else if((x >= 264 && x <= 393) && (y >= 0 && y <=120))
		{
			playGeneral(0, 2);
		}
		else if((x >= 264 && x <= 393) && (y >= 125 && y <=244))
		{
			playGeneral(1, 2);
		}
		else if((x >= 264 && x <= 393) && (y >= 249 && y <=370))
		{	
			playGeneral(2, 2);
		}
	}

	/**
	 * Plays each move.
	 * @param x Position in x.
	 * @param y Position in y.
	 */
	public void playGeneral(int x, int y)
	{			 
		try
		{
			playUser(x,y);
			if(tab.hasWon())
			{
				JOptionPane.showMessageDialog(this, "Congratulations you won", "Winner", JOptionPane.INFORMATION_MESSAGE);
				int messageResult = JOptionPane.showConfirmDialog(this, "Would you like to start again?", "Tic Tac Toe", y);

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
			this.repaint();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			this.repaint();
		}
	}
	
	public void mousePressed(MouseEvent e) {
		//Nothing happens
	}

	public void mouseReleased(MouseEvent e) {
		//Nothing happens
	}

	public void mouseEntered(MouseEvent e) {
		//Nothing happens
	}

	public void mouseExited(MouseEvent e) {
		//Nothing happens
	}
}
