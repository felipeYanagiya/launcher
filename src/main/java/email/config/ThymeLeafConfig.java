package email.config;

import java.io.IOException;
import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

/**
 * @author felipey.
 */
@Configuration
public class ThymeLeafConfig {

    @Bean
    public JavaMailSender getJavaMailSenderImpl() throws IOException {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

        Properties properties = new Properties();
        properties.load(new ClassPathResource("config/application.properties").getInputStream());
        /* some properties here */

        javaMailSender.setHost(properties.getProperty("mail.server.host"));
        javaMailSender.setPort(Integer.parseInt(properties.getProperty("mail.server.port")));
        javaMailSender.setProtocol(properties.getProperty("mail.server.protocol"));
        javaMailSender.setUsername(properties.getProperty("mail.server.username"));
        javaMailSender.setPassword(properties.getProperty("mail.server.password"));

        javaMailSender.setJavaMailProperties(properties);

        return javaMailSender;
    }

    @Bean
    public ClassLoaderTemplateResolver emailTemplateResolver() {
        ClassLoaderTemplateResolver emailTemplateResolver = new ClassLoaderTemplateResolver();
        emailTemplateResolver.setPrefix("/templates/email/");
        emailTemplateResolver.setSuffix(".html");
        emailTemplateResolver.setTemplateMode("HTML5");
        emailTemplateResolver.setCharacterEncoding("UTF-8");
        emailTemplateResolver.setOrder(1);

        return emailTemplateResolver;
    }

    @Bean
    public ServletContextTemplateResolver defaultWebTemplateResolver() {
        ServletContextTemplateResolver webTemplateResolver = new ServletContextTemplateResolver();
        webTemplateResolver.setPrefix("/templates/");
        webTemplateResolver.setSuffix(".html");
        webTemplateResolver.setTemplateMode("HTML5");
        webTemplateResolver.setCharacterEncoding("UTF-8");
        webTemplateResolver.setOrder(2);

        return webTemplateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(emailTemplateResolver());
        templateEngine.setTemplateResolver(defaultWebTemplateResolver());
        return templateEngine;
    }

    @Bean
    public ThymeleafViewResolver thymeleafViewResolver() {
        ThymeleafViewResolver thymeleafViewResolver = new ThymeleafViewResolver();

        thymeleafViewResolver.setTemplateEngine(templateEngine());
        thymeleafViewResolver.setCharacterEncoding("UTF-8");

        return thymeleafViewResolver;
    }

}
