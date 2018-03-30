package com.acme.mailreader.domain;

import java.time.Instant;

import org.junit.Ignore;
import org.junit.Test;

import com.acme.mailreader.commun.MailReaderModule;
import com.acme.mailreader.domain.Mail.Statut;
import com.acme.mailreader.infrastructure.InMemoryMailSender;
import com.acme.mailreader.service.MailService;
import com.acme.mailreader.utils.DateIncorrecteException;
import com.acme.mailreader.utils.DateIncorrecteException.ErreurDate;
import com.acme.mailreader.utils.MailInvalideException;

public class MailTest {
	
	@Test(expected=DateIncorrecteException.class)
	public final void erreurSiDateAvant1970() throws DateIncorrecteException {
		Mail mail= new Mail.Builder("sujetA").date(Instant.parse("1943-08-03T10:18:39.00Z")).build();
			
	}
	
	@Test(expected=DateIncorrecteException.class)
	public final void erreurSiDateApres2100() throws DateIncorrecteException {
		Mail mail= new Mail.Builder("sujetA").date(Instant.parse("2278-12-03T10:08:56.00Z")).build();		
	}
	
	@Test(expected=MailInvalideException.class)
	public final void erreurSiSujetSuperieur20Caracteres() throws MailInvalideException {
		Mail mail= new Mail.Builder("ADADADADADADDADADADADDADADADADADAD").build();
		MailService mailService = new MailService(new InMemoryMailSender());
		mailService.envoyerMail(mail);
	}
	

				
}