package lqw.test.test_dom4j;

import java.io.FileOutputStream;
import java.io.IOException;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

/**
 * Hello world!
 *
 */
public class BuildXml {

    public static void main(String[] args) throws IOException {

        createXml();
        System.out.println("create xml ok");
    }

    public static void createXml() throws IOException {
        Element root = DocumentHelper.createElement("root");
        Document document = DocumentHelper.createDocument(root);
        root.addAttribute("a1", "a1");
        root.addAttribute("a2", "a2");

        root.addElement("element").addAttribute("a1", "a1").addAttribute("a2", "a2").addElement("element1")
                        .addText("haha");

        // 把生成的xml文档存放在硬盘上 true代表是否换行
        OutputFormat format = new OutputFormat("    ", true);
        format.setEncoding("utf-8");// 设置编码格式
        System.err.println(Class.class.getClass().getResource("/").getPath());
        XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(Class.class.getClass().getResource("/").getPath() + "/test.xml"), format);

        xmlWriter.write(document);
        xmlWriter.close();
    }
}
