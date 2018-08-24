import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
//import sun.audio.*;
public class Game extends JPanel {

	Ball ball = new Ball(this);
	Racquet racquet = new Racquet(this);
	int speed = 1;

	private int getScore() {
		return speed - 1;
	}

	public Game() {
		

		addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
			}

			
			public void keyReleased(KeyEvent e) {
				racquet.keyReleased(e);
			}

			
			public void keyPressed(KeyEvent e) {
				racquet.keyPressed(e);
			}
		});
		setFocusable(true);
		//Sound.BACK.loop();
	}

	private void move() {
		ball.move();
		racquet.move();
	}


	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		ball.paint(g2d);
		racquet.paint(g2d);

		
		g2d.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
		g2d.setColor(Color.BLACK);
		g2d.drawString("SCORE", 200, 30);
		g2d.setColor(Color.BLUE);
		g2d.drawString(String.valueOf(getScore()), 250, 55);
	}

	public void gameOver() {
		//Sound.BACK.stop();
		Sound.GAMEOVER.play();
		JOptionPane.showMessageDialog(this, "SCORE: " + getScore(),
				"GAME OVER!!", JOptionPane.YES_NO_OPTION);
		System.exit(ABORT);
	}

	public static void main(String[] args) throws InterruptedException {
		JFrame frame = new JFrame("Mini Tennis");
		Game game = new Game();
		frame.add(game);
		frame.setSize(300, 400);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		while (true) {
			game.move();
			game.repaint();
			Thread.sleep(10);
		}
	}
}
