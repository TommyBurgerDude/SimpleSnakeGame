import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements ActionListener{

	static final int SCREEN_WIDTH = 600;
	static final int SCREEN_HEIGHT = 600;
	static final int UNIT_SIZE = 25;
	static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;
	static final int DELAY = 75;
	final int x[] = new int [GAME_UNITS];
	final int y[] = new int [GAME_UNITS];
	int bodyParts = 6;
	int applesEaten;
	int appleX;
	int appleY;
	boolean running = false;
	Timer timer;
	Random random;
	
	
	GamePanel() {	
		random = new Random();
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setBackground(Color.BLACK);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		startGame();
	}
	
	public void startGame() {
		newApple();
		running = true;
		timer = new Timer(DELAY, this);
		timer.start();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
	
	public void draw(Graphics g) {
		if (running) {
			
			//Apple Object
			g.setColor(Color.red);
			g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);
			
			//Snake Parts
			for (int i = 0; i < bodyParts; i++) {
				if (i == 0) {
					g.setColor(Color.green);
					g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
				}
				//Snake Head
				else {
					g.setColor(new Color(45, 180, 0));
					g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
				}
			}
			
			//Score Display
			g.setColor(Color.red);
			g.setFont(new Font("Calibri", Font.BOLD, 40));
			FontMetrics metrics = getFontMetrics(g.getFont());
			g.drawString("Score: " + applesEaten, (SCREEN_WIDTH - metrics.stringWidth("Score: " + applesEaten))/2, g.getFont().getSize());
		}
		
		//End Game
		else {
			gameOver(g);
		}
	}
	//Randomly Place an Apple
	public void newApple() {
		appleX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE)) * UNIT_SIZE;
		appleY = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE)) * UNIT_SIZE;
		
	}
	
	//Move Snake shapes
	public void move () {
		
		for (int i = bodyParts; i > 0; i--) {
			x[i] = x[i-1];
			y[i] = y[i-1];		
		}
		
		//Movement based on Direction
		switch(MyKeyAdapter.direction) {
		case 'U' :
			y[0] = y[0] - UNIT_SIZE;
			break;
		case 'D' :
			y[0] = y[0] + UNIT_SIZE;
			break;
		case 'L' :
			x[0] = x[0] - UNIT_SIZE;
			break;
		case 'R' :
			x[0] = x[0] + UNIT_SIZE;
			break;
		}
	}
	
	//Apple is Eaten
	public void checkApple() {
		if((x[0] == appleX) && y[0] == appleY) {
			bodyParts++;
			applesEaten++;
			newApple();
		}
	}
	
	//Check for Game Overs
	public void checkCollision() {
		
		//Check for Body Collision
		for (int i = bodyParts; i > 0; i--) {
			if ((x[0] == x[i] && y[0] == y[i])) {
				running = false;
			}
		}
		
		//Check Border Collisions
		if(x[0] < 0) {
			running = false;
		}
		if (x[0] > SCREEN_WIDTH) {
			running = false;
		}
		if (y[0] < 0) {
			running = false;
		}
		if (y[0] > SCREEN_HEIGHT) {
			running = false;
		}
		
		if (!running) {
			timer.stop();
		}
	}
	
	//Game has Ended
	public void gameOver(Graphics g) {
		
		//Display Score
		g.setColor(Color.red);
		g.setFont(new Font("Calibri", Font.BOLD, 40));
		FontMetrics metricsscore = getFontMetrics(g.getFont());
		g.drawString("Score: " + applesEaten, (SCREEN_WIDTH - metricsscore.stringWidth("Score: " + applesEaten))/2, g.getFont().getSize());
		
		//Display End Text
		g.setColor(Color.red);
		g.setFont(new Font("Calibri", Font.BOLD, 75));
		FontMetrics metrics = getFontMetrics(g.getFont());
		g.drawString("Game Over", (SCREEN_WIDTH - metrics.stringWidth("Game Over"))/2, SCREEN_HEIGHT / 2);
	}
		
		
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (running) {
			move();
			checkApple();
			checkCollision();
		}
		repaint();
	}

}
