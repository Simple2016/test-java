package lqw.test.test_dom4j;

import java.util.HashMap;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.XPath;
import org.dom4j.io.SAXReader;

/** 
 *
 * @ClassName   类名：TestXpath
 * @Description 功能说明：
 * <p>
 * TODO
 * </p>
 ************************************************************************
 * @date        创建日期：2016年10月10日
 * @author      创建人： liqw
 * @version     版本号：V1.0
 * <p>
 ***************************修订记录*************************************
 * 
 *   2016年10月10日   liqw  创建该类功能。
 *
 ***********************************************************************
 * </p>
 */

public class TestXpath {

    public static void main(String... strings) throws DocumentException {

        SAXReader reader = new SAXReader();

        Document document = reader.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("test.xml"));
        // String xpath = "/root/element/element1";//element1元素
        String xpath = "/root/element/@a1";// element的属性
        String text = selectSingleNode(document, xpath).getText();
        System.err.println(text);
        //没有命名空间可以直接获取
        System.err.println(document.selectSingleNode(xpath).getText());
    }

    public static XPath createXPath(Document document, String xpath) {
        XPath path = document.createXPath(xpath);
        Map<String, String> namespace = new HashMap<String, String>();
        namespace.put(document.getRootElement().getNamespace().getPrefix(),
                        document.getRootElement().getNamespace().getStringValue());
        path.setNamespaceURIs(namespace);
        return path;
    }

    public static Node selectSingleNode(Document document, String xpath) {
        XPath path = createXPath(document, xpath);
        return path.selectSingleNode(document.getRootElement());
    }
}
