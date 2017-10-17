package lqw.test.builder;

import com.sun.mail.util.MailSSLSocketFactory;
import lqw.test.contant.EmailContants;
import lqw.test.domain.MailAuthenticator;
import lqw.test.util.IOUtils;

import javax.mail.Authenticator;
import javax.mail.Session;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.Properties;

/**
 * Created by liqw on 2017/10/17.
 */
public class MailSessionBuilder {
    public static Session buildMailSession() {
        InputStream inputStream = IOUtils.readClasspathFileStream("email-server.properties");
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        MailSSLSocketFactory sslSF = null;
        try {
            sslSF = new MailSSLSocketFactory();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        sslSF.setTrustAllHosts(true);
        properties.put(EmailContants.MAIL_SMTP_SSL_SOCKETFACTORY, sslSF);
        // 需要身份认证，创建一个密码验证器
        String userName = properties.getProperty(EmailContants.MAIL_USER);
        String passWord = properties.getProperty(EmailContants.MAIL_PASSWORD);
        MailAuthenticator authenticator = new MailAuthenticator(userName, passWord);
        // 根据邮件会话属性和密码验证器构造一个发送邮件的session
        Session sendMailSession = Session.getDefaultInstance(properties, authenticator);
//        sendMailSession.setDebug(true);
        return sendMailSession;
    }
}
