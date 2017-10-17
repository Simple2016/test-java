package lqw.test;

import lqw.test.sender.MailSender;
import lqw.test.util.IOUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) {
//        sentTextEmail();
        sentHtmlAttachEmail();
    }

    public static void sentTextEmail() {
        MailSender mailSender2 = new MailSender();
        boolean b = mailSender2.sentTextEmail("尊敬的顾客您好：\n" +
                "您于2017年01月22日选择开具电子票，我们将电子票发送给您。\n" +
                "电子票据信息如下：\n" +
                "开票日期：2017年01月22日\n" +
                "票据代码：26000117\n" +
                "票据号码：1000000006\n" +
                "金额合计：￥558.01\n" +
                "附件是电子票png文件，供下载使用。");
        System.out.println(b);
    }

    public static void sentHtmlAttachEmail() {
        MailSender mailSender2 = new MailSender();
        byte[] bytes = IOUtils.readClasspathFile("content.html");
        String content = new String(bytes);
        Map<String, byte[]> map = new HashMap<>();
        map.put("content.html", bytes);
        boolean b = mailSender2.sentHtmlAttachEmail(content, map);
        System.out.println(b);
    }
}
