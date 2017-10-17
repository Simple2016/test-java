package lqw.test;


import org.apache.commons.lang.StringUtils;

import javax.activation.DataHandler;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;
import javax.mail.util.ByteArrayDataSource;
import java.util.*;
import java.util.Map.Entry;

public class MailSender {

    private static List<String> requies = new ArrayList<String>();

    static {
        requies.add("mail.host");
        requies.add("mail.transport.protocol");
        requies.add("mail.smtp.auth");
    }

    public static void send(Messager messager, Properties prop) throws Exception {
        checkProp(prop);

        // 使用JavaMail发送邮件的5个步骤
        // 1、创建session
        Session session = Session.getInstance(prop);
        // 开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
        session.setDebug(true);
        // 2、通过session得到transport对象
        Transport ts = session.getTransport();
        ts.connect(messager.getFrom(), messager.getPassword());
        MimeMessage mimeMessage = createMail(messager, session);
        ts.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
        ts.close();

    }

    private static MimeMessage createMail(Messager messager, Session session) throws Exception {
        // 创建邮件对象
        MimeMessage message = new MimeMessage(session);
        // 指明邮件的发件人
        message.setFrom(new InternetAddress(messager.getFrom()));
        // 指明邮件的收件人，现在发件人和收件人是一样的，那就是自己给自己发
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(messager.getTo()));
        // 邮件的标题
        message.setSubject(messager.getSubject());
        // 邮件的文本内容

        MimeBodyPart text = new MimeBodyPart();
        // 描述数据关系
        MimeMultipart mm = new MimeMultipart();
        mm.addBodyPart(text);
        text.setContent(messager.getContent(), "text/html;charset=UTF-8");
        for (Entry<String, byte[]> image : messager.iamges.entrySet()) {

            MimeBodyPart imagePart = new MimeBodyPart();
            DataHandler dataHandler = new DataHandler(new ByteArrayDataSource(image.getValue(), "image/x-jg"));
            imagePart.setDataHandler(dataHandler);
            imagePart.setContentID(image.getKey());
            imagePart.setFileName(MimeUtility.encodeText(image.getKey()));
            mm.addBodyPart(imagePart);
        }

        message.setContent(mm);

        // message.setContent(messager.getContent(), "text/html;charset=UTF-8");
        // 返回创建好的邮件对象
        return message;
    }

    public static MessageBuilder getMessageBuilder() {
        return new MessageBuilder();
    }

    private static void checkProp(Properties prop) {
        for (String key : requies) {
            if (StringUtils.isEmpty(prop.getProperty(key))) {
                throw new RuntimeException("[" + key + "]不能为空");
            }
        }
    }

    public static class Messager {

        private String password;

        private String from;

        private String to;

        // 暂未实现
        private List<String> cc;

        private Map<String, byte[]> iamges;

        private String subject;

        private String content;

        public Messager(MessageBuilder builder) {
            this.from = builder.from;
            this.to = builder.to;
            this.cc = builder.cc;
            this.subject = builder.subject;
            this.content = builder.content;
            this.password = builder.password;
            this.iamges = builder.images;
        }

        public String getFrom() {
            return from;
        }

        public Map<String, byte[]> getIamges() {
            return iamges;
        }

        public void setIamges(Map<String, byte[]> iamges) {
            this.iamges = iamges;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public String getTo() {
            return to;
        }

        public void setTo(String to) {
            this.to = to;
        }

        public List<String> getCc() {
            return cc;
        }

        public void setCc(List<String> cc) {
            this.cc = cc;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

    }

    public static class MessageBuilder {

        private String from;

        private String to;

        private List<String> cc = new ArrayList<String>();

        private String subject;

        private String content;

        private String password;

        private Map<String, byte[]> images = new HashMap<String, byte[]>();

        public MessageBuilder from(String from) {
            this.from = from;
            return this;
        }

        public MessageBuilder addImage(String fileName, byte[] iamge) {
            this.images.put(fileName, iamge);
            return this;
        }

        public MessageBuilder to(String to) {
            this.to = to;
            return this;
        }

        public MessageBuilder cc(List<String> cc) {
            this.cc = cc;
            return this;
        }

        public MessageBuilder subject(String subject) {
            this.subject = subject;
            return this;
        }

        public MessageBuilder content(String content) {
            this.content = content;
            return this;
        }

        public MessageBuilder password(String password) {
            this.password = password;
            return this;
        }

        public Messager build() {
            return new Messager(this);
        }
    }


    public static void main(String[] args) throws Exception {

        Properties prop=new Properties();
        prop.put("mail.host","smtp.126.com");
        prop.put("mail.transport.protocol","smtp");
        prop.put("mail.smtp.auth","true");
        MessageBuilder builder = new MessageBuilder();
        String psd = "j*u)023@kds&%";
        String from= "lqw8lqw@126.com";
        String desPath= "1214891980@qq.com";
        String subject= "基于Java Mail的电子邮件收发系统毕业设计_百度文库";
        String content="你愿望中的商品正在促销你愿望中的商品正在促销你愿望中的商品正在促销你愿望中的商品正在促销你愿望中的商品正在促销你愿望中的商品正在促销你愿望中的商品正在促销你愿望中的商品正在促销你愿望中的商品正在促销你愿望中的商品正在促销你愿望中的商品正在促销你愿望中的商品正在促销你愿望中的商品正在促销你愿望中的商品正在促销你愿望中的商品正在促销你愿望中的商品正在促销";
        Messager messager = builder.password(psd).from(from).to(desPath).subject(subject).content(content)
               .build();
        new MailSender().send(messager, prop);
    }
}
