package com.appmailer.MailerApp;

import com.appmailer.MailerApp.Services.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class MailerAppApplication {

	@Autowired
	private EmailSenderService senderService;

	public static void main(String[] args) {
		SpringApplication.run(MailerAppApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void triggerMail(){
		System.out.println("Start");

//		senderService.sendSimpleEmail("ogeoffrey17@gmail.com",
//				"This is the body for a test",
//				"WE LOVE YOU.");

		senderService.sendEmailWithAttachment(
				"ogeoffrey17@gmail.com",
				"This email body carries attachments",
				"We can send",
				"/home/odero/RecLetter.pdf"
		);
	}

}
