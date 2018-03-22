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
	public static final int egaux=0;
	public static final int unMail_PlusImportant=-1;
	public static final int unMail_MoinsImportant=1;
	
	public int compare(Mail unMail, Mail unAutreMail) {
		if (unDesMailNull(unMail,unAutreMail)) {
			return egaux;
		}
		if (unMail.isImportant() != unAutreMail.isImportant()) {
			if (unMail.isImportant() && !unAutreMail.isImportant()) {
				return unMail_PlusImportant;
			} else {
				return unMail_MoinsImportant;
			}
		}
		if (unMail.getStatut() != unAutreMail.getStatut()) {
			int comp = unMail.getStatut().ordinal()
					- unAutreMail.getStatut().ordinal();
			return comp > egaux ? unMail_PlusImportant : unMail_MoinsImportant;
		}
		if (unMail.getSujet() != unAutreMail.getSujet()) {
			return unAutreMail.getSujet().compareTo(unMail.getSujet());
		}
		return unAutreMail.getDate().compareTo(unMail.getDate());
	}
	
	public boolean unDesMailNull(Mail unMail, Mail unAutreMail){
		if (unMail==null || unAutreMail==null)
		return true;
		else
		return false;
	}
	
	public boolean unMailImportant(Mail unMail, Mail unAutreMail){
		if (unMail.isImportant() || unAutreMail==null)
		return true;
		else
		return false;
	}
	

}
