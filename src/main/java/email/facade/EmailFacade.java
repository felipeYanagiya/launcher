package email.facade;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import email.service.EmailService;

/**
 * @author felipey.
 */
@Component
public class EmailFacade {

    @Autowired
    private EmailService service;

    @RequestMapping("/send-email")
    public void sendEmail(@RequestParam(value = "address") String address) throws MessagingException {
        service.sendEmailToSubscriber(address);
    }

}
