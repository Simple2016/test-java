package lqw.test.sender;

import com.sun.mail.util.MailSSLSocketFactory;
import lqw.test.builder.MailSessionBuilder;
import lqw.test.builder.MessageBuilder;
import lqw.test.builder.MultiPartBuilder;
import lqw.test.contant.EmailContants;
import lqw.test.domain.MailAuthenticator;
import lqw.test.util.IOUtils;

import javax.mail.*;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.Map;
import java.util.Properties;

/**
 * Created by liqw on 2017/10/17.
 */
public class MailSender {
    Message mailMessage;

    public MailSender() {
        Session session = MailSessionBuilder.buildMailSession();
        mailMessage = new MessageBuilder().buildMessage(session);
    }

    /**
     * 发送html页面带附件的邮件
     *
     * @param content       html页面
     * @param attachFileMap 附件
     * @return
     */
    public boolean sentHtmlAttachEmail(String content, Map<String, byte[]> attachFileMap) {
        Multipart multipart = MultiPartBuilder.buildMineMultipart(content, attachFileMap);
        try {
            mailMessage.setContent(multipart);
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
        try {
            Transport.send(mailMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 发送纯文本的邮件
     *
     * @param text
     * @return
     */
    public boolean sentTextEmail(String text) {
        try {
            mailMessage.setText(text);
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
        try {
            Transport.send(mailMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
