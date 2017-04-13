package lqw.test.test_swing.compent;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import lqw.test.test_swing.swing_helper.ColorHelper;

public class MyPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MyPanel() {
		this(0, 0, 50, 50, "gray");
	}

	public MyPanel(int x, int y, int width, int height, String bgcolor) {
		setLayout(null);
		setBounds(x, y, width, height);
		setBackground(ColorHelper.getColor(bgcolor));
		final Point origin = new Point();
		final Dimension dimen = new Dimension();
		final List<Boolean> isMove = new ArrayList<Boolean>();
		isMove.add(true);
		addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

		});

		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int minwidth = 30;
				int minheight = 30;
				if (isMove.get(0)) {
					int width = e.getX() >= minwidth ? (int) dimen.getWidth() + e.getX() - origin.x : minwidth;
					int height = e.getY() >= minheight ? (int) dimen.getHeight() + e.getY() - origin.y : minheight;
					setSize(width, height);

				} else {
					Point p = getLocation();
					setLocation(p.x + e.getX() - origin.x, p.y + e.getY() - origin.y);
				}
			}

			@Override
			public void mouseMoved(MouseEvent e) {

				origin.x = e.getX();
				origin.y = e.getY();
				dimen.setSize(getWidth(), getHeight());
				if (origin.x >= getWidth() - 5 && origin.y >= getHeight() - 5) {
					isMove.set(0, true);
					setCursor(new Cursor(Cursor.SE_RESIZE_CURSOR));
				} else {
					isMove.set(0, false);
					setCursor(new Cursor(Cursor.MOVE_CURSOR));
				}

			}
		});

	}

	public MyPanel init(int x, int y, int width, int height, String bgcolor) {
		return new MyPanel(x, y, width, height, bgcolor);
	}

}
