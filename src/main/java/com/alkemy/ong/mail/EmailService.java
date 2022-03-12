package com.alkemy.ong.mail;

import com.alkemy.ong.enumeration.EmailSubject;
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
import java.util.Arrays;
import java.util.List;

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
        EmailSubject subject = EmailSubject.NEW_USER;
        Mail mail = prepareMail(email, subject);

        sendRequest(mail);
    }

    public void sendThankForContactingEmail(String email) {
        EmailSubject subject = EmailSubject.NEW_CONTACT;
        Mail mail = prepareMail(email, subject);

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

    private Mail prepareMail(String email, EmailSubject subject) {

        Mail mail = new Mail();

        Email from = new Email();
        from.setEmail(fromEmail);

        mail.setFrom(from);

        Email to = new Email();
        to.setEmail(email);

        Personalization personalization = new Personalization();
        personalization.addTo(to);
        switch (subject) {
            case NEW_USER:
                personalization.addDynamicTemplateData("subject", EmailSubject.NEW_USER.getSubject());
                personalization.addDynamicTemplateData("title", "Te damos la bienvenida a Somos Más!");
                personalization.addDynamicTemplateData("body", "Agradecemos que quieras formar parte de la comunidad Somos Más, tu usuario fue registrado con éxito en nuestra plataforma. Ante cualquier consulta, no dudes en contactarnos.");
                break;
            case NEW_CONTACT:
                personalization.addDynamicTemplateData("subject", EmailSubject.NEW_CONTACT.getSubject());
                personalization.addDynamicTemplateData("title", "Formulario de contacto recibido");
                personalization.addDynamicTemplateData("body", "Muchas gracias por escribirnos! Somos Más se contactará con usted muy pronto.");
                break;
        }

        List<String> contacts = Arrays.asList("Mail: somosfundacionmas@gmail.com", "Instagram: SomosMás", "Facebook: Somos_Más", "Teléfono de contacto: 1160112988");
        personalization.addDynamicTemplateData("contacts", contacts);
        mail.addPersonalization(personalization);

        mail.setTemplateId(templateId);

        return mail;
    }
}
