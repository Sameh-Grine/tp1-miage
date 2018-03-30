package com.acme.mailreader.presentation;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.acme.mailreader.domain.Mail;
import com.acme.mailreader.service.MailService;
import com.acme.mailreader.utils.MailInvalideException;
import com.google.inject.Inject;

public class InterpreteurLignecommande {
	
	private MailService serviceMail;
	
	@Inject
	public InterpreteurLignecommande(MailService serviceMail) {
		super();
		this.serviceMail = serviceMail;
	}
	
	/**
	 * Parse les arguments de la ligne de commande
	 * @param args tableau d'arguments
	 */
	public String nouveauMail(String[] args){
		String sujet = args[1];
		String result = null ;
		Mail mail = new Mail.Builder(sujet).build();
		try {
			result=  serviceMail.envoyerMail(mail);
		} catch (MailInvalideException e) {
			Logger.getGlobal().log(Level.SEVERE,e.getMessage(), e);
		}
		return result;
	}
}
