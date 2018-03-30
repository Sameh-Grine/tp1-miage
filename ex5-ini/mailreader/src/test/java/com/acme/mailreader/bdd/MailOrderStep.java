package com.acme.mailreader.bdd;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.acme.mailreader.domain.Mail;
import com.acme.mailreader.domain.MailComparator;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * Les steps (actions) du test
 * 
 * <p>
 * A noter qu'une nouvelle instance de cette classe est créée à chaque scenario
 * </p>
 *
 */

public class MailOrderStep {

	private List<Mail> listeMailsAvantTri;
	
	private List<Mail> listeMailsApresTri;


	@Given("^les mails :$")
	public void les_mails(List<Mail> mails) throws Throwable {
		listeMailsAvantTri = mails;
	}

	@When("^je trie la liste$")
	public void trie_liste() throws Throwable {
		listeMailsApresTri = new ArrayList<Mail>(listeMailsAvantTri);
		Collections.sort(listeMailsApresTri, new MailComparator());
	}
	@Then("^la liste ordonnée doit être :$")
	public void la_liste_ordonnée_doit_être(List<Mail> mails) throws Throwable {
		assertThat(listeMailsApresTri,is(mails));
	}
}
