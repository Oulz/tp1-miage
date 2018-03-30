package com.acme.mailreader.domain;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

import java.time.Instant;

import org.junit.Before;
import org.junit.Test;

import com.acme.mailreader.utils.DateIncorrecteException;

public class MailComparatorTest {
	
	private MailComparator comparator;
	
	@Before
	public void setUp() throws Exception {
		this.comparator = new MailComparator();
	}

	@Test
	public final void egauxSiDeuxMailsNuls() {
		Mail unMail = null;
		Mail unAutreMail = null;
		assertThat(comparator.compare(unMail, unAutreMail), is(0));
	}
	
	@Test
	public final void egauxSiUnDesMailsNuls() {
		Mail unMail = new Mail();
		Mail unAutreMail = null;
		assertThat(comparator.compare(unMail, unAutreMail), is(0));
	}
	
	@Test
	public final void egauxSiMemeDate() throws DateIncorrecteException {
		Mail unMail = new Mail.Builder("test").date(Instant.parse("2018-30-03T13:33:12Z")).build();
		Mail unAutreMail = new Mail.Builder("test").date(Instant.parse("2018-30-03T13:33:12Z")).build();
		assertThat(comparator.compare(unMail, unAutreMail), is(0));
	}
	
	@Test
	public final void egauxSiMemeImp(){
		Mail unMail = new Mail.Builder("test").important(true).build();
		Mail unAutreMail = new Mail.Builder("test").important(true).build();
		assertThat(comparator.compare(unMail, unAutreMail), is(0));
	}
	
	public final void mailPlusImportantEnPremier() {
		Mail unMail = new Mail.Builder("sujet").important(true).build();
		Mail unAutreMail = new Mail.Builder("sujet").important(false).build();
		assertThat(unMail, not(nullValue()));
		assertThat(comparator.compare(unMail, unAutreMail), is(MailComparator.PREMIER_PLUS_PETIT));		
	}
	
	@Test
	public final void premierPlusPetitSiMoinsImportant(){
		Mail unMail = new Mail.Builder("test").important(false).build();
		Mail unAutreMail = new Mail.Builder("test").important(true).build();
		assertThat(comparator.compare(unMail, unAutreMail), is(1));
	}
	
	@Test
	public final void ordreAlphabetiqueSiMemeImportance() {
		Mail unMail = new Mail.Builder("sujet").important(true).build();
		Mail unAutreMail = new Mail.Builder("sujet").important(false).build();
		assertThat(unMail, not(nullValue()));
		assertThat(comparator.compare(unMail, unAutreMail), is(MailComparator.PREMIER_PLUS_GRAND));		
	}
	
	
	@Test
	public final void premierPlusGrandSiPlusImportant(){
		Mail unMail = new Mail.Builder("test").important(true).build();
		Mail unAutreMail = new Mail.Builder("test").important(false).build();
		assertThat(comparator.compare(unMail, unAutreMail), is(-1));
	}
	
	@Test
	public final void premierPlusGrandSiMeilleurStatut(){
		Mail unMail = new Mail.Builder("test").statut(Mail.Statut.SENT).build();
		Mail unAutreMail = new Mail.Builder("test").statut(Mail.Statut.UNSENT).build();
		assertThat(comparator.compare(unMail, unAutreMail), is(-1));
	}
	
	@Test
	public final void premierPlusPetitSiMoinsBonStatut(){
		Mail unMail = new Mail.Builder("test").statut(Mail.Statut.UNSENT).build();
		Mail unAutreMail = new Mail.Builder("test").statut(Mail.Statut.SENT).build();
		assertThat(comparator.compare(unMail, unAutreMail), is(1));
	}
	
	@Test
	public final void egauxSiMemeStatut(){
		Mail unMail = new Mail.Builder("test").statut(Mail.Statut.SENT).build();
		Mail unAutreMail = new Mail.Builder("test").statut(Mail.Statut.SENT).build();
		assertThat(comparator.compare(unMail, unAutreMail), is(0));
	}
	
	@Test
	public final void premierPlusPetitSiDatePosterieure() throws DateIncorrecteException{
		Mail unMail = new Mail.Builder("test").date(Instant.parse("2017-04-05T13:33:12Z")).build();
		Mail unAutreMail = new Mail.Builder("test").date(Instant.parse("2017-04-04T13:33:12Z")).build();
		assertThat(comparator.compare(unMail, unAutreMail), is(1));
	}
	
	@Test
	public final void premierPlusGrandSiDateAnterieure() throws DateIncorrecteException {
		Mail unMail = new Mail.Builder("test").date(Instant.parse("2017-04-03T13:33:12Z")).build();
		Mail unAutreMail = new Mail.Builder("test").date(Instant.parse("2017-04-04T13:33:12Z")).build();
		assertThat(comparator.compare(unMail, unAutreMail), is(-1));
	}
	
	
	
}
