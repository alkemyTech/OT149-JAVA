package com.alkemy.ong.mail;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class EmailService {

    @Value("${app.sendgrid.key}")
    private String apikey;

    @Value("${from.email}")
    private String fromEmail;

    @Value("${template.id}")
    private String templateId;

    public void sendEmail(String email, String subject, String contentValue) {

        Email from = new Email(fromEmail);
        Email to = new Email(email);
        Content content = new Content("text/plain", contentValue);
        Mail mail = new Mail(from, subject, to, content);

        sendRequest(mail);
    }

    public void sendWelcomeEmail(String email) {

        Mail mail = prepareMail(email);

        sendRequest(mail);
    }

    private void sendRequest(Mail mail) {
        SendGrid sg = new SendGrid(apikey);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            sg.api(request);
        } catch (IOException ex) {
            log.error("Error sending email", ex);
            throw new RuntimeException("Error sending email");
        }
    }

    private Mail prepareMail(String email) {

        Mail mail = new Mail();

        Email from = new Email();
        from.setEmail(fromEmail);

        mail.setFrom(from);

        Email to = new Email();
        to.setEmail(email);

        Personalization personalization = new Personalization();
        personalization.addTo(to);
        mail.addPersonalization(personalization);

        mail.setTemplateId(templateId);

        return mail;
    }
}
