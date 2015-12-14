package email.service;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author felipey.
 */
@Service
public class EmailService {

    private static final String EMAIL_SMTP_AUTH = "mail.smtp.auth";

    private static final String EMAIL_SMTP_TTLS = "mail.smtp.starttls.enable";

    private static final String EMAIL_SMTP_HOST = "mail.smtp.host";

    private static final String EMAIL_SMTP_PORT = "mail.smtp.port";

    @Value("${email.user.name}")
    private String userName;

    @Value("${email.password}")
    private String password;

    @Value("${email.from.address}")
    private String fromAddress;

    @Value("${email.smtp.auth}")
    private String smtpAuth;

    @Value("${email.smtp.ttls}")
    private String smtpTtls;

    @Value("${email.smtp.host}")
    private String smtpHost;

    @Value("${email.smtp.port}")
    private String smtpPort;


    @RequestMapping("/email")
    public void sendEmail(@RequestParam("address")String emailAddress) {
        Properties props = new Properties();
        props.put(EMAIL_SMTP_AUTH, smtpAuth);
        props.put(EMAIL_SMTP_TTLS, smtpTtls);
        props.put(EMAIL_SMTP_HOST, smtpHost);
        props.put(EMAIL_SMTP_PORT, smtpPort);

        Session session = Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(userName, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromAddress));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(emailAddress));
            message.setSubject("Testing Subject");
            message.setText("Dear Mail Crawler,"
                    + "\n\n No spam to my email, please!");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }


}
