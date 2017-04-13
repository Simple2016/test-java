package lqw.test.test_dom4j;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ReadXml {

    public static void main(String... args) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("test.xml"));
        Element element = document.getRootElement();
        List<Element> eleList = element.elements();
        for (Element elementChild : eleList) {
            System.err.println(elementChild.getName());
        }
    }
}
