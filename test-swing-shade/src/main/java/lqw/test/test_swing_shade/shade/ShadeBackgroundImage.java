package lqw.test.test_swing_shade.shade;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author http://blog.sina.com.cn/s/blog_9e90a0fd01017mf3.html
 *
 */
public class ShadeBackgroundImage extends JFrame {

	private static final long serialVersionUID = 4693799019369193520L;
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShadeBackgroundImage frame = new ShadeBackgroundImage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ShadeBackgroundImage() {
		setTitle("背景为渐变色的主界面");// 设置窗体标题
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();// 创建内容面板
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		ShadePanel shadePanel = new ShadePanel();// 创建渐变背景面板
		contentPane.add(shadePanel, BorderLayout.CENTER);// 添加面板到窗体内容面板
	}
}
