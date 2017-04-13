package lqw.test.test_swing_shade.shade3;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.RadialGradientPaint;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.geom.Arc2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author http://blog.csdn.net/ycb1689/article/details/8433092#comments
 *
 */
@SuppressWarnings("serial")
public class PieGradientPainter extends JPanel {
	private double[] data;
	private Color[] colors = createColors();
	private Pie3D[] pies;
	private Pie3D[] outerPies;

	public PieGradientPainter() {
		setBackground(Color.DARK_GRAY);
		data = new double[] { 10, 30, 40, 15, 25, 60 };
		int x = 0;
		int y = 0;
		int w = 300;
		int h = 300;
		int shiftAngle = -30;
		int delta = 40;
		outerPies = createPies(x, y, w, h, 0, shiftAngle, data, colors);

		x += delta / 2;
		y += delta / 2;
		w -= delta;
		h -= delta;
		pies = createPies(x, y, w, h, 0, shiftAngle, data, colors);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		int paddingLeft = 30;
		drawLinearPie(g2d, pies, outerPies, paddingLeft, 30);

		paddingLeft = (int) (outerPies[0].getArc().getWidth()) + paddingLeft * 2;
		drawRadialPie(g2d, pies, outerPies, paddingLeft, 30);
	}

	protected void drawLinearPie(Graphics2D g2d, Pie3D[] pies, Pie3D[] outerPies, int paddingLeft, int paddingTop) {
		g2d.translate(paddingLeft, paddingTop);
		int colorIndex = colors.length - 1;
		double radius = pies[0].getArc().getWidth() / 2;
		float[] fractions = { 0.0f, 0.55f, 1.0f };
		Point2D start = new Point2D.Double(0, pies[0].getPieCenter().getY() - radius);
		Point2D end = new Point2D.Double(0, pies[0].getPieCenter().getY() + radius * 1.5);

		for (int i = 0; i < pies.length; ++i) {
			Pie3D pie = pies[i];

			colorIndex = (colorIndex + 1) % colors.length;
			Color c = colors[colorIndex];

			g2d.setColor(c);
			g2d.fill(outerPies[i].getArc());

			// Linear gradiant paint.
			Color[] cs = { c.darker().darker(), c, Color.WHITE.darker() };
			LinearGradientPaint paint = new LinearGradientPaint(start, end, fractions, cs);
			g2d.setPaint(paint);
			g2d.fill(pie.getArc());
		}

		drawPieLabel(g2d, pies);
		g2d.translate(-paddingLeft, -paddingTop);
	}

	protected void drawRadialPie(Graphics2D g2d, Pie3D[] pies,

			Pie3D[] outerPies, int paddingLeft, int paddingTop) {
		g2d.translate(paddingLeft, paddingTop);
		int colorIndex = colors.length - 1;
		Point2D center = pies[0].getPieCenter();

		int radius = (int) pies[0].getArc().getWidth();
		float[] fractions = { 0.0f, 0.2f, 1.0f };

		for (int i = 0; i < pies.length; ++i) {
			Pie3D pie = pies[i];

			colorIndex = (colorIndex + 1) % colors.length;
			Color c = colors[colorIndex];

			g2d.setColor(c);
			g2d.fill(outerPies[i].getArc());

			// Radial gradient paint.

			Color[] cc = { c.brighter(), c, c.darker().darker().darker() };
			RadialGradientPaint paint = new RadialGradientPaint(center, radius, fractions, cc);
			g2d.setPaint(paint);

			g2d.fill(pie.getArc());
		}

		drawPieLabel(g2d, pies);
		g2d.translate(-paddingLeft, -paddingTop);
	}

	protected void drawPieLabel(Graphics2D g2d, Pie3D[] pies) {
		FontMetrics metrics = g2d.getFontMetrics();
		g2d.setColor(Color.BLACK);

		for (int i = 0; i < pies.length; ++i) {
			Pie3D p = pies[i];
			int sw = metrics.stringWidth(p.getLabel()) / 2;
			int sh = (metrics.getAscent()) / 2;
			int x = (int) (p.getLabelPosition().getX() - sw);
			int y = (int) (p.getLabelPosition().getY() + sh);
			g2d.drawString(p.getLabel(), x, y);
		}
	}

	private Color[] createColors() {

		// 返回16进制的值颜色

		List<Color> colors = new ArrayList<Color>();

		colors.add(Color.decode("#635D49"));

		colors.add(Color.decode("#4D7B20"));

		colors.add(Color.decode("#FF7321"));

		colors.add(Color.decode("#BFDD89"));

		colors.add(Color.decode("#AA6A2D"));

		colors.add(Color.decode("#9C1594"));

		colors.add(Color.decode("#00E500"));

		colors.add(Color.decode("#E2FF55"));

		colors.add(Color.decode("#D718A5"));

		colors.add(Color.decode("#BB2100"));

		colors.add(Color.decode("#D0F15A"));

		colors.add(Color.decode("#169800"));

		colors.add(Color.decode("#00DBFF"));

		colors.add(Color.decode("#00FF00"));

		return colors.toArray(new Color[0]);

	}

	public static Pie3D[] createPies(int x,

			int y,

			int w,

			int h,

			int shadowDepth,

			int shiftAngle,

			double[] data,

			Color[] colors) {

		double sum = 0;

		for (double d : data) {

			sum += d;

		}

		// 初始化Pies

		double arcAngle = 0;

		double startAngle = shiftAngle;

		Pie3D[] pies = new Pie3D[data.length];

		for (int i = 0; i < data.length; ++i) {

			arcAngle = data[i] * 360 / sum; // 使用百分比计算角度

			if (i + 1 == data.length) {

				arcAngle = 360 + shiftAngle - startAngle; // 保证闭合

				arcAngle = arcAngle > 0 ? arcAngle : 0;

			}

			Arc2D.Double arc = new Arc2D.Double(x, y, w, h, startAngle, arcAngle, Arc2D.PIE);

			double rate = data[i] / sum * 100 * 100 + 0.5;

			rate = ((int) rate) / 100.0;

			pies[i] = new Pie3D(arc, colors[i % colors.length], rate, shadowDepth, 30);

			startAngle += arcAngle;

		}

		return pies;

	}

	private static void createGuiAndShow() {

		JFrame frame = new JFrame("Pie with gradient effects");

		frame.getContentPane().add(new PieGradientPainter());

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		int sw = Toolkit.getDefaultToolkit().getScreenSize().width;

		int sh = Toolkit.getDefaultToolkit().getScreenSize().height;

		int w = 690;

		int h = 390;

		int x = (sw - w) / 2;

		int y = (sh - h) / 2 - 40;

		x = x > 0 ? x : 0;

		y = y > 0 ? y : 0;

		frame.setBounds(x, y, w, h);

		frame.setVisible(true);

	}

	public static void main(String[] args) {

		createGuiAndShow();

	}

}