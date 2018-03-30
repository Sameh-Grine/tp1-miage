package com.acme.mailreader.presentation;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.acme.mailreader.domain.Mail;
import com.acme.mailreader.service.MailService;
import com.acme.mailreader.utils.MailInvalideException;

public class InterpreteurLignecommande {	
	
	private MailService serviceMail;
	@Inject
	public InterpreteurLignecommande(MailService _serviceMail) {
		super();
		this.serviceMail = _serviceMail;
	}
	
	/**
	 * Parse les arguments de la ligne de commande
	 * @param args tableau d'arguments
	 */
	public void nouveauMail(String[] args){
		String sujet = args[1];
		Mail mail = new Mail.Builder(sujet).build();
		try {
			serviceMail.envoyerMail(mail);
		} catch (MailInvalideException e) {
			Logger.getGlobal().log(Level.SEVERE,e.getMessage(), e);
		}
	}
}
