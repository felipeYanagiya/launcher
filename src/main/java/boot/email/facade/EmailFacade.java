package boot.email.facade;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import boot.email.service.EmailService;

/**
 * @author felipey.
 */
@RestController
@EnableAutoConfiguration
public class EmailFacade {

    @Autowired
    private EmailService service;

    @RequestMapping("/send-email")
    public void sendEmail(@RequestParam(value = "address") String address) throws MessagingException {
        service.sendEmailToSubscriber(address);
    }

}
