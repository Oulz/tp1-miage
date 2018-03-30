package service;

import domaine.Mail;
import infrastructure.InMemoryMailSender;
import infrastructure.MailSender;
import infrastructure.SmtpMailSender;
import presentation.ClientMail;

public class MailService {
	private MailSender sender;
	public void envoyerMail(Mail mail){
		if (ClientMail.production){
			sender = new SmtpMailSender();
		}else {
			sender = new InMemoryMailSender();
		}
		sender.envoyerMail(mail);
	}
}
