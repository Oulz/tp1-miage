package infrastructure;

import domaine.Mail;

public interface MailSender {
	public void envoyerMail(Mail mail);
}
