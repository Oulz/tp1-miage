package commun;

import java.util.Comparator;

import domaine.Mail;

/**
 * Comparateur de mails
 * 
 * Comme on désire afficher les mails les plus importants en premier, l'element le plus grand retourne une valeur négative
 *
 */
public class MailComparator implements Comparator<Mail> {

	private static final int PREMSSUP = 1;
	private static final int PREMSINF = -1;
	private static final int EGAUX = 0;

	public int compare(Mail unMail, Mail unAutreMail) {
		if (unMailNul(unMail, unAutreMail)){
			return EGAUX;
		}
		if (diffImp(unMail, unAutreMail)) {
			return triImp(unMail, unAutreMail);
		}
		if (diffStatut(unMail, unAutreMail)) {
			return triStatut(unMail, unAutreMail);
		}
		if (unMail.getSujet() != unAutreMail.getSujet()) {
			return unAutreMail.getSujet().compareTo(unMail.getSujet());
		}
		return unAutreMail.getDate().compareTo(unMail.getDate());
	}

	private int triStatut(Mail unMail, Mail unAutreMail) {
		int comp = unMail.getStatut().ordinal()
				- unAutreMail.getStatut().ordinal();
		return comp > 0 ? PREMSINF : PREMSSUP;
	}

	private boolean diffStatut(Mail unMail, Mail unAutreMail) {
		return unMail.getStatut() != unAutreMail.getStatut();
	}

	private int triImp(Mail unMail, Mail unAutreMail) {
		if (unMail.isImportant() && !unAutreMail.isImportant()) {
			return PREMSINF;
		} else {
			return PREMSSUP;
		}
	}

	private boolean diffImp(Mail unMail, Mail unAutreMail) {
		return unMail.isImportant() != unAutreMail.isImportant();
	}

	private boolean unMailNul(Mail unMail, Mail unAutreMail) {
		return unMail == null || unAutreMail == null;
	}

	

}
