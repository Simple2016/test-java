package lqw.test.test_swing_shade.shade3;

import java.awt.Color;
import java.awt.geom.Arc2D;
import java.awt.geom.Area;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;

/**
 * @author http://blog.csdn.net/ycb1689/article/details/8433092#comments
 *
 */
class Pie3D {
	private Arc2D arc; // 这里的弧并不是圆上的一弧，而是椭圆的一部分.
	private Area frontSite;
	private Area leftSite;
	private Area rightSite;
	private Color color;
	private Pie3D selectedPie;
	private Point2D arcMiddle;
	private Point2D labelPosition;
	private double value;
	private int shadowDepth;
	private double selectedShiftDis; // 被选中的饼图在他的中线上移动的距离

	public Pie3D(Arc2D arc, Color color, double value) {
		this(arc, color, value, 10, 30);
	}

	public Pie3D(Arc2D arc, Color color, double value, int shadowDepth, double selectedShiftDis) {
		this.arc = arc;
		this.color = color;
		this.value = value;
		this.selectedShiftDis = selectedShiftDis;
		this.shadowDepth = shadowDepth;

		Arc2D arcBottom = new Arc2D.Double(arc.getX(), arc.getY() + shadowDepth, arc.getWidth(), arc.getHeight() + 0,
				arc.getAngleStart(), arc.getAngleExtent(), Arc2D.CHORD);
		Point2D[] topPs = getPointsOfArc(arc);
		Point2D[] bottomPs = getPointsOfArc(arcBottom);

		// Front site
		GeneralPath font = new GeneralPath();
		font.moveTo(topPs[1].getX(), topPs[1].getY());
		font.lineTo(topPs[2].getX(), topPs[2].getY());
		font.lineTo(bottomPs[2].getX(), bottomPs[2].getY());
		font.lineTo(bottomPs[1].getX(), bottomPs[1].getY());
		font.closePath();
		this.frontSite = new Area(arcBottom);
		this.frontSite.add(new Area(font));

		// Left site
		GeneralPath left = new GeneralPath();
		left.moveTo(topPs[0].getX(), topPs[0].getY());
		left.lineTo(topPs[1].getX(), topPs[1].getY());
		left.lineTo(bottomPs[1].getX(), bottomPs[1].getY());
		left.lineTo(topPs[0].getX(), topPs[0].getY() + 3);
		left.closePath();
		this.leftSite = new Area(left);

		// Right site
		GeneralPath right = new GeneralPath();
		right.moveTo(topPs[0].getX(), topPs[0].getY());
		right.lineTo(topPs[2].getX(), topPs[2].getY());
		right.lineTo(bottomPs[2].getX(), bottomPs[2].getY());
		right.lineTo(topPs[0].getX(), topPs[0].getY() + 3);
		right.closePath();
		this.rightSite = new Area(right);

		arcMiddle = calculateArcMiddle();

		// Label position: 五分之四处
		Point2D c = getPieCenter();
		// Point2D m = getChordMiddle();
		Point2D m = arcMiddle;
		double dis = GeometryUtil.distanceOfPoints(c, m) * 0.8;
		labelPosition = GeometryUtil.extentPoint(c, m, dis);
	}

	// 取得Arc上的三个点，在对Arc: center, left, right.
	public static Point2D[] getPointsOfArc(Arc2D arc) {
		Point2D center = new Point2D.Double(arc.getCenterX(), arc.getCenterY());
		Point2D left = arc.getStartPoint();
		Point2D right = arc.getEndPoint();
		Point2D[] points = new Point2D[] { center, left, right };

		return points;
	}

	public Pie3D getSelectedPie() {
		if (selectedPie == null) {

			selectedPie = createSeletecdPie();
		}

		return selectedPie;
	}

	private Pie3D createSeletecdPie() {
		// 沿中线方向移动selectedShiftDis个单位
		Point2D c = getPieCenter();
		Point2D m = getChordMiddle();
		Point2D p = GeometryUtil.extentPoint(c, m, selectedShiftDis);

		double deltaX = p.getX() - c.getX();
		double deltaY = p.getY() - c.getY();
		double x = arc.getX() + deltaX;
		double y = arc.getY() + deltaY;

		Arc2D shiftArc = (Arc2D) arc.clone();
		shiftArc.setFrame(x, y, arc.getWidth(), arc.getHeight());

		return new Pie3D(shiftArc, color, value, shadowDepth, selectedShiftDis);
	}

	public Arc2D getArc() {
		return arc;
	}

	public Area getFrontSite() {
		return frontSite;
	}

	public Area getLeftSite() {
		return leftSite;
	}

	public Area getRightSite() {
		return rightSite;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;

	}

	public Point2D getLabelPosition() {
		return labelPosition;

	}

	public void setLabelPosition(Point2D labelPosition) {
		this.labelPosition = labelPosition;
	}

	public double getValue() {
		return value;
	}

	public String getLabel() {
		return value + "%";
	}

	// 弦的中心点
	public Point2D getChordMiddle() {
		return GeometryUtil.middlePoint(arc.getStartPoint(), arc.getEndPoint());
	}

	// 饼图的圆心
	public Point2D getPieCenter() {
		return new Point2D.Double(arc.getCenterX(), arc.getCenterY());
	}

	// 弧上的中心点
	public Point2D getArcMiddle() {
		return arcMiddle;
	}

	private Point2D calculateArcMiddle() {
		// 创建一个新的弧，其扩展角度为当前弧的一半
		return new Arc2D.Double(arc.getX(), arc.getY(), arc.getWidth(), arc.getHeight(), arc.getAngleStart(),
				arc.getAngleExtent() / 2, Arc2D.PIE).getEndPoint();
	}
}