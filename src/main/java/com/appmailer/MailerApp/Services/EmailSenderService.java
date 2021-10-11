package com.appmailer.MailerApp.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class EmailSenderService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendSimpleEmail(String toEmail,String body,String subject){
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom("ogeoffrey17@gmail.com");
        mailMessage.setTo(toEmail);
        mailMessage.setText(body);
        mailMessage.setSubject(subject);

        mailSender.send(mailMessage);
        System.out.println("Simple email sent.. ");
    }

    public void sendEmailWithAttachment(String toEmail, String body,
                                        String subject,
                                        String attachment){
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,true);
            messageHelper.setFrom("ogeoffrey17@gmail.com");
            messageHelper.setTo(toEmail);
            messageHelper.setText(body);
            messageHelper.setSubject(subject);

            FileSystemResource fileSystemResource = new FileSystemResource(new File(attachment));
            messageHelper.addAttachment(fileSystemResource.getFilename(), fileSystemResource);
            mailSender.send(mimeMessage);

            System.out.println("Email sent with attachments");

        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
}
