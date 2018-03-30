package com.acme.mailreader.infrastructure;

import java.util.logging.Logger;

import com.acme.mailreader.domain.Mail;
import com.acme.mailreader.utils.MailSender;

/**
 *
 * Implementation de mail sender qui n'envoi pas reellement
 * <p>Utile pour la recette</p>
 *
 *
 */
public class InMemoryMailSender implements MailSender {

	private Logger logger = Logger.getGlobal();
	
	public String envoyerMail(Mail mail) {
		logger.info("[Envoi d'un mail en mémoire]");
		return "[Envoi d'un mail en mémoire], " + mail.toString();

	}

}
