package com.acme.mailreader.utils;

import java.util.Comparator;

import com.acme.mailreader.model.Mail;

/**
 * Comparateur de mails
 * 
 * Comme on désire afficher les mails les plus importants en premier, l'element le plus grand retourne une valeur négative
 *
 */
public class MailComparator implements Comparator<Mail> {

	public int compare(Mail unMail, Mail unAutreMail) {
		if (unMail == null || unAutreMail == null) {
			return 0;
		}
		if (unMail.isImportant() != unAutreMail.isImportant()) {
			if (unMail.isImportant() && !unAutreMail.isImportant()) {
				return -1;
			} else {
				return 1;
			}
		}
		if (unMail.getStatut() != unAutreMail.getStatut()) {
			int comp = unMail.getStatut().ordinal()
					- unAutreMail.getStatut().ordinal();
			return comp > 0 ? -1 : 1;
		}
		if (unMail.getSujet() != unAutreMail.getSujet()) {
			return unAutreMail.getSujet().compareTo(unMail.getSujet());
		}
		return unAutreMail.getDate().compareTo(unMail.getDate());
	}
	

}
