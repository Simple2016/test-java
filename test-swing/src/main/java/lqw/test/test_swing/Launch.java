package lqw.test.test_swing;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import lqw.test.test_swing.compent.MyPanel;
import lqw.test.test_swing.swing_helper.ColorHelper;

/**
 * 根据xml 生成程序界面 还没写递归
 *
 */
public class Launch {
	static List<Container> containerList = new ArrayList<Container>();

	public static void main(String[] args) throws Exception {
		load();
		init();
	}

	public static void load() throws Exception {
		SAXReader reader = new SAXReader();
		Document document = reader
				.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("test1.xml"));
		Element element = document.getRootElement();
		List<Element> eleList = element.elements();
		for (Element elementChild : eleList) {
			String name = elementChild.getName();
			int x = Integer.parseInt(elementChild.attributeValue("x"));
			int y = Integer.parseInt(elementChild.attributeValue("y"));
			int width = Integer.parseInt(elementChild.attributeValue("width"));
			int height = Integer.parseInt(elementChild.attributeValue("height"));
			String bgcolor = elementChild.attributeValue("background-color");
			String click = elementChild.attributeValue("click");

			MyPanel jp = (MyPanel) Class.forName("lqw.test.test_swing.compent.MyPanel").newInstance();
			MyPanel jp1 = jp.init(x, y, width, height, bgcolor);
			// jp1.addMouseListener((TestMouseAdapter)
			// Class.forName(click).newInstance());
			containerList.add(jp1);
		}
	}

	public static void init() {
		final JFrame jf = new JFrame("test");
		jf.setLayout(null);
		jf.setSize(300, 400);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				try {
					save(jf);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		for (Container conta : containerList) {
			jf.add(conta);
		}
		jf.setVisible(true);
	}

	private static void save(JFrame jframe) throws Exception {

		containerList.removeAll(containerList);
		getContainer(jframe.getContentPane());
		Element root = DocumentHelper.createElement("frame");
		Document document = DocumentHelper.createDocument(root);
		root.addAttribute("width", "" + jframe.getWidth());
		root.addAttribute("height", "" + jframe.getHeight());
		for (Component component : containerList) {
			Container container = ((Container) component);
			if (container instanceof MyPanel) {
				Color backgroundColor = container.getBackground();
				Rectangle r = container.getBounds();
				String colorHex = ColorHelper.toHexFromColor(backgroundColor);
				root.addElement(MyPanel.class.getName()).addAttribute("background-color", colorHex)
						.addAttribute("x", "" + (int) r.getX()).addAttribute("y", "" + (int) r.getY())
						.addAttribute("width", "" + (int) r.getWidth()).addAttribute("height", "" + (int) r.getHeight())
						.addAttribute("click", "lqw.test.test_swing.listener.TestMouseAdapter");
			}
		}

		// 把生成的xml文档存放在硬盘上 true代表是否换行
		OutputFormat format = new OutputFormat("    ", true);
		format.setEncoding("utf-8");// 设置编码格式

		XMLWriter xmlWriter = new XMLWriter(new FileOutputStream("src/main/resources/test1.xml"), format);

		xmlWriter.write(document);
		xmlWriter.close();

	}

	public static void getContainer(Component comp) {
		Component[] comps = ((Container) comp).getComponents();
		for (Component component : comps) {
			containerList.add(((Container) component));
			getContainer((Container) component);
		}
	}

}
