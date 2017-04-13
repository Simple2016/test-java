package lqw.test.test_swing.compent;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class SelectionBox {
	// 起始点，鼠标按下时记录
	static Point pBegin;
	// 绘图矩形
	static Rectangle rect;
	static JPanel jPanelWaveBox = new JPanel();

	public static void main(String args[]) {
		System.err.println("start");
		final JFrame jf = new JFrame();
		jf.setLocationRelativeTo(null);
		jf.setSize(500, 500);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setLayout(null);
		jf.setVisible(true);

		jf.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent evt) {
				Graphics2D g2 = (Graphics2D) jf.getGraphics();
				// 启用XOR模式
				g2.setXORMode(Color.white);
				// 绘制之前的矩形
				if (rect != null) {
					// g2.setStroke(new BasicStroke(1.0f, 2, 0, 10.0f));
					// g2.draw(rect);

					g2.draw(rect);
				}
				// 绘制现在的矩形
				rect = new Rectangle((int) Math.min(pBegin.getX(), evt.getPoint().getX()),
						(int) Math.min(pBegin.getY(), evt.getPoint().getY()),
						(int) Math.abs(pBegin.getX() - evt.getPoint().getX()),
						(int) Math.abs(pBegin.getY() - evt.getPoint().getY()));
				// Stroke dash = new BasicStroke(2.5f, BasicStroke.CAP_BUTT,
				// BasicStroke.JOIN_ROUND, 3.5f,
				// new float[] { 15, 10, }, 0f);
				// g2.setStroke(dash);
				//
				// g2.setStroke(new BasicStroke(1f, BasicStroke.CAP_BUTT,
				// BasicStroke.JOIN_MITER, 10.0f,
				// new float[] { 6.0f }, 0.0f));

				g2.draw(rect);
				g2.setPaintMode();
				g2.dispose();
			}
		});
		jf.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent evt) {
				try {
					// 画选择框（实际效果为去掉）
					Graphics2D g2 = (Graphics2D) jf.getGraphics();
					g2.setXORMode(Color.white);
					g2.draw(rect);
					g2.setPaintMode();
					g2.dispose();
				} catch (Exception e) {
				}
				pBegin = null;
				rect = null;
			}

			public void mousePressed(MouseEvent evt) {
				pBegin = evt.getPoint();
				jPanelWaveBox.requestFocus();
			}
		});

	}
}
