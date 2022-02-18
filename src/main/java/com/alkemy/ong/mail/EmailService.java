package com.alkemy.ong.mail;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmailService {

    @Value("${app.sendgrid.key}")
    private String apikey;

    @Value("${from.email}")
    private String fromEmail;

    public void sendEmail(String email, String subject, String contentValue) throws IOException {

        Email from = new Email(fromEmail);
        Email to = new Email(email);
        Content content = new Content("text/plain", contentValue);
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(apikey);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
        } catch (IOException ex) {
            throw new RuntimeException("Error sending email");
        }
    }
}
