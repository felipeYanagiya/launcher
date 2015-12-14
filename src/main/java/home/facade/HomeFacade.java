package home.facade;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author felipey.
 */
@Component
public class HomeFacade {

    @RequestMapping("")
    public void setUpHomeContext() {

    }

}
