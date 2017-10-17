package lqw.test.builder;

import lqw.test.contant.EmailContants;
import lqw.test.util.IOUtils;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Properties;

/**
 * Created by liqw on 2017/10/17.
 */
public class MessageBuilder {
    private String from;
    private String subject;
    private Address[] mailToArr;
    private Address[] mailCcArr;

    public MessageBuilder() {
        Properties properties= new Properties();
        InputStream inputStream = IOUtils.readClasspathFileStream("email-config.properties");
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        from = properties.getProperty("from");
        subject = properties.getProperty("subject");
        String toArr = properties.getProperty("mailToArr");
        String ccArr = properties.getProperty("mailCcArr");
        if (toArr != null) {
            String[] split = toArr.split(",");
            mailToArr = new Address[split.length];
            for (int i = 0; i < split.length; i++) {
                try {
                    mailToArr[i] = new InternetAddress(split[i]);
                } catch (AddressException e) {
                    e.printStackTrace();
                }
            }
        }
        if (ccArr != null) {
            String[] split = ccArr.split(",");
            mailCcArr = new Address[split.length];
            for (int i = 0; i < split.length; i++) {
                try {
                    mailCcArr[i] = new InternetAddress(split[i]);
                } catch (AddressException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Message buildMessage(Session session) {
        Message message = new MimeMessage(session);
        try {
            // 设置邮件消息的主题
            message.setSubject(subject);
            // 设置邮件消息发送的时间
            message.setSentDate(Calendar.getInstance().getTime());
            Address address = new InternetAddress(from);
            //设置发件人
            message.setFrom(address);
            // 设置接收人
            if (mailToArr != null && mailToArr.length > 0) {
                message.setRecipients(Message.RecipientType.TO, mailToArr);
            }
            //设置抄送人
            if (mailCcArr != null && mailCcArr.length > 0) {
                message.setRecipients(Message.RecipientType.CC, mailCcArr);
            }

        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return message;
    }

}
