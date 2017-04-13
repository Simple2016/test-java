package lqw.test.test_swing_shade.shade2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 * @author http://www.open-open.com/home/space.php?uid=130&do=thread&id=21
 *
 */
public class GradientPanel extends JPanel {

	public GradientPanel(LayoutManager lm) {
		super(lm);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (!isOpaque()) {
			return;
		}

		int width = getWidth();
		int height = getHeight();
		Graphics2D g2 = (Graphics2D) g;
		GradientPaint gradientPaint = new GradientPaint(0, height / 2, Color.blue, width, height / 2, Color.red, false);

		g2.setPaint(gradientPaint);
		g2.fillRect(0, 0, width, height);

	}

	public static void main(String args[]) {
		JFrame jf = new JFrame("GradientPanelTest");
		jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		GradientPanel gp = new GradientPanel(new BorderLayout());
		jf.getContentPane().add(gp);
		jf.setSize(500, 400);
		jf.setVisible(true);
	}
}