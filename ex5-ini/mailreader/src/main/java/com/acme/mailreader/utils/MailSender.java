package com.acme.mailreader.utils;

import com.acme.mailreader.domain.Mail;

public interface MailSender {
	
	/**
	 * Envoi un mail
	 * @param mail le mail à envoyer
	 * @return 
	 */
	String envoyerMail(Mail mail);
	

}
