package com.acme.mailreader.bdd;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.time.Instant;

import com.acme.mailreader.domain.Mail;
import com.acme.mailreader.domain.Mail.Statut;
import com.acme.mailreader.infrastructure.InMemoryMailSender;
import com.acme.mailreader.infrastructure.SmtpMailSender;
import com.acme.mailreader.service.MailService;
import com.acme.mailreader.utils.DateIncorrecteException;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class MailSendingStep {
	
	private Mail mailAEnvoyer;
	private MailService mailService ;
	private String resultat = "" ;

	
	@Given("^un mail avec l'importance \"([^\"]*)\", le statut \"([^\"]*)\", le sujet \"([^\"]*)\" et la date \"([^\"]*)\"$")
	public void un_mail(boolean importance, Statut statut, String sujet,
			String date) throws DateIncorrecteException {
		mailAEnvoyer = new Mail.Builder(sujet).important(importance).statut(statut).date(Instant.parse(date)).build();
	}

	@When("^j'envoie le mail avec l'importance'\"([^\"]*)\"$")
	public void envoie_mail(boolean production) throws Throwable {
		if(production) {
			mailService = new MailService(new SmtpMailSender());
		} else {
			mailService = new MailService(new InMemoryMailSender());
		}
		resultat = mailService.envoyerMail(mailAEnvoyer);
	}

	@Then("^l'envoie doit retourner \"([^\"]*)\"$")
	public void envoie_doit_retourner(String result) throws Throwable {
		assertThat(resultat,is(result));
	}
}