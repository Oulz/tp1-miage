package presentation;

import service.MailService;
import domaine.Mail;

public class InterpreteurLigneCommande {

	
	public void nouveauMail(String[] args){
		Mail mail = new Mail.Builder(args[1]).build();
		MailService mailService = new MailService();
		mailService.envoyerMail(mail);
	}

}
