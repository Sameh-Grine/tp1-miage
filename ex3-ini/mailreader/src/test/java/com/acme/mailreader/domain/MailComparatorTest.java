package com.acme.mailreader.domain;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.*;

import java.time.Instant;

import org.junit.Before;
import org.junit.Test;

import com.acme.mailreader.domain.Mail.Statut;
import com.acme.mailreader.utils.DateIncorrecteException;

public class MailComparatorTest {
	
	private MailComparator comparator;

	@Before
	public void setUp() throws Exception {
		this.comparator = new MailComparator();
	}

	@Test
	public final void egauxSiDeuxMailsNuls() {
		Mail mail1 = null;
		Mail mail2 = null;
		assertThat(comparator.compare(mail1, mail2), is(comparator.EGAUX));
	}
	
	@Test
	public final void trieParImportanceDeMail() {
		Mail mail1 = new Mail.Builder("sujetA").important(true).build();
		Mail mail2 = new Mail.Builder("sujetB").important(false).build();
		assertThat(comparator.compare(mail1, mail2), is(comparator.PREMIER_PLUS_GRAND));
	}
	
	@Test
	public final void trieParStatutDeMail() {
		Mail mail1 = new Mail.Builder("sujetA").important(true).statut(Statut.READ).build();
		Mail mail2 = new Mail.Builder("sujetB").important(true).statut(Statut.UNSENT).build();
		assertThat(comparator.compare(mail1, mail2), is(comparator.PREMIER_PLUS_GRAND));
	}
	
	@Test
	public final void trieParSujetDeMail() {
		Mail mail1 = new Mail.Builder("sujetA").important(true).statut(Statut.READ).build();
		Mail mail2 = new Mail.Builder("sujetB").important(true).statut(Statut.READ).build();
		assertThat(comparator.compare(mail1, mail2), is(comparator.PREMIER_PLUS_GRAND));
	}
	
	@Test
	public final void trieParDateDeMail() throws DateIncorrecteException {
		Instant oneMinuteLater = Instant.now().plusSeconds(60);
		Instant atTheMoment = Instant.now() ;
		Mail mail1 = new Mail.Builder("sujet").important(true).statut(Statut.READ).date(atTheMoment).build();
		Mail mail2 = new Mail.Builder("sujet").important(true).statut(Statut.READ).date(oneMinuteLater).build();
		assertThat(comparator.compare(mail1, mail2), is(comparator.PREMIER_PLUS_GRAND));
	}
}
