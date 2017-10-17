package lqw.test.builder;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import javax.mail.util.ByteArrayDataSource;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by liqw on 2017/10/17.
 */
public class MultiPartBuilder {
    public static Multipart buildMineMultipart(String content, String[] attachFileNames) {
        MimeMultipart mimeMultipart = new MimeMultipart();
        // 创建一个包含HTML内容的MimeBodyPart
        BodyPart bodyPart = new MimeBodyPart();
        // 设置html邮件消息内容
        try {
            bodyPart.setContent(content, "text/html; charset=utf-8");
            mimeMultipart.addBodyPart(bodyPart);
            if (attachFileNames != null && attachFileNames.length != 0) {
                for (String attachFile : attachFileNames) {
                    bodyPart = new MimeBodyPart();
                    FileDataSource fds = new FileDataSource(attachFile); //得到数据源
                    bodyPart.setDataHandler(new DataHandler(fds)); //得到附件本身并放入BodyPart
                    bodyPart.setFileName(MimeUtility.encodeText(fds.getName()));  //得到文件名并编码（防止中文文件名乱码）同样放入BodyPart
                    mimeMultipart.addBodyPart(bodyPart);
                }
            }
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return mimeMultipart;
    }

    public static Multipart buildMineMultipart(String content, Map<String, byte[]> attachFileMap) {
        MimeMultipart mimeMultipart = new MimeMultipart();
        // 创建一个包含HTML内容的MimeBodyPart
        BodyPart bodyPart = new MimeBodyPart();
        // 设置html邮件消息内容
        try {
            bodyPart.setContent(content, "text/html; charset=utf-8");
            mimeMultipart.addBodyPart(bodyPart);
            if (!attachFileMap.isEmpty()) {
                for (Map.Entry<String, byte[]> entry : attachFileMap.entrySet()) {
                    bodyPart = new MimeBodyPart();
                    ByteArrayDataSource fds = new ByteArrayDataSource(entry.getValue(), "application/png"); //得到数据源
                    bodyPart.setDataHandler(new DataHandler(fds)); //得到附件本身并放入BodyPart
                    bodyPart.setFileName(MimeUtility.encodeText(entry.getKey()));  //得到文件名并编码（防止中文文件名乱码）同样放入BodyPart
                    mimeMultipart.addBodyPart(bodyPart);
                }
            }

        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return mimeMultipart;
    }
}
