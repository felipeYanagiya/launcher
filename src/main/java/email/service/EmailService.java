package email.service;

import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

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


    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @RequestMapping(value = "/email", method = RequestMethod.POST)
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

        // Prepare the evaluation context
        final Context ctx = new Context(locale);
        ctx.setVariable("subscriptionDate", new Date());
        ctx.setVariable("hobbies", Arrays.asList("Cinema", "Sports", "Music"));

        // Prepare message using a Spring helper
        final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
        message.setSubject("Welcome to Our Subscription!");
        message.setFrom("thymeleaf@example.com");
        message.setTo(recipientEmail);

        // Create the HTML body using Thymeleaf
        final String htmlContent = this.templateEngine.process("email-simple.html", ctx);
        message.setText(htmlContent, true /* isHtml */);

        // Send email
        this.mailSender.send(mimeMessage);

    }


}
