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

    int LESS_IMPORTANT = 1;
    int EQUAL = 0;
    int MORE_IMPORTANT = -1;
    
	public int compare(Mail mail, Mail otherMail) {
		
		if (oneOfTheMailIsNull(mail, otherMail)) {
			throw new IllegalArgumentException("Can't compare with a null value");
			//return EQUAL;
		}
		if (notTheSameImportance(mail,otherMail)) {
			return mostImportantMail(mail,otherMail);
		}
		if (notTheSameStatut(mail,otherMail)) {
			return sortByStatut(mail,otherMail);			
		}
		if (notTheSameSubject(mail,otherMail)) {
			return compareMailSubjet(mail,otherMail) ;
		}
		return mail.getDate().compareTo(otherMail.getDate());
	}
	
	private int mostImportantMail(Mail mail, Mail otherMail) {
		if (mail.isImportant() && !otherMail.isImportant()) {
			return MORE_IMPORTANT;
		} else {
			return LESS_IMPORTANT;
		}
	}

	private boolean notTheSameSubject(Mail mail, Mail otherMail) {
		if (mail.getSujet() == null || otherMail.getSujet() == null) {
			return true;
		}
		return !mail.getSujet().equals(otherMail.getSujet());
	}

	private int sortByStatut(Mail mail, Mail otherMail) {
		int compareOrder = compareOrderStatus(mail,otherMail);
		return compareOrder < 0 ? LESS_IMPORTANT : MORE_IMPORTANT;
	}
	
	private boolean notTheSameStatut(Mail mail, Mail otherMail) {
		return mail.getStatut() != otherMail.getStatut();
	}

	private boolean notTheSameImportance(Mail mail, Mail otherMail) {
		return mail.isImportant() != otherMail.isImportant();
	}

	private boolean oneOfTheMailIsNull(Mail mail, Mail otherMail) {
		return mail == null || otherMail == null ;
	}

	private int compareMailSubjet(Mail mail, Mail otherMail) {
		return mail.getSujet().compareTo(otherMail.getSujet());
	}

	private int compareOrderStatus(Mail mail, Mail otherMail) {
		return mail.getStatut().ordinal() - otherMail.getStatut().ordinal();
	}
	

}
