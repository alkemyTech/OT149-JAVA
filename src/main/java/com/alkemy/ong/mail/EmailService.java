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

    public void sendEmail(String email) throws IOException {

        Email from = new Email("lautaroyanzon13@gmail.com");
        String subject = "Welcome message";
        Email to = new Email(email);
        Content content = new Content("text/plain", "generic message");
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(apikey);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            throw ex;
        }
    }
}
