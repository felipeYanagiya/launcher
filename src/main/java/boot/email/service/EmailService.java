package boot.email.service;

import java.io.UnsupportedEncodingException;
import java.util.Locale;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

/**
 * @author felipey.
 */
@Service
public class EmailService {

    @Autowired
    private SpringTemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String username;

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmailToSubscriber(@RequestParam("recipientEmail") final String recipientEmail)
            throws MessagingException {

        sendSimpleMail(recipientEmail, Locale.ENGLISH);
    }

    /*
     * Send HTML mail (simple)
     */
    void sendSimpleMail(
            final String recipientEmail, final Locale locale)
            throws MessagingException {

        String subject = "Novo relatório";

        final Context ctx = new Context(locale);

        final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        message.setSubject(subject);
        message.setTo(recipientEmail);

        try {
            message.setFrom(new InternetAddress(username, "Sistema"));
        } catch (UnsupportedEncodingException e) {
        }

        final String htmlContent = this.templateEngine.process("email-simple", ctx);
        message.setText(htmlContent, true);

        this.mailSender.send(mimeMessage);

    }

}
