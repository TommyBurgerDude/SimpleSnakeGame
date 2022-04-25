import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

//Controls - WASD and Arrows
	public class MyKeyAdapter extends KeyAdapter {
		
		static char direction = 'R';
		
		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				if (direction != 'R') {
					direction = 'L';
				}
				break;
			case KeyEvent.VK_RIGHT:
				if (direction != 'L') {
					direction = 'R';
				}
				break;
			case KeyEvent.VK_UP:
				if (direction != 'D') {
					direction = 'U';
				}
				break;
			case KeyEvent.VK_DOWN:
				if (direction != 'U') {
					direction = 'D';
				}
				break;
			case KeyEvent.VK_A:
				if (direction != 'R') {
					direction = 'L';
				}
				break;
			case KeyEvent.VK_D:
				if (direction != 'L') {
					direction = 'R';
				}
				break;
			case KeyEvent.VK_W:
				if (direction != 'D') {
					direction = 'U';
				}
				break;
			case KeyEvent.VK_S:
				if (direction != 'U') {
					direction = 'D';
				}
				break;
			}
		}
	}