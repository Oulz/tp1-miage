package com.acme.mailreader.domain;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

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
		Mail mail1 = new Mail();
		Mail mail2 = null;
		assertThat(comparator.compare(mail1, mail2), is(0));
	}
	
	public final void mailPlusImportantEnPremier() {
		Mail unMail = new Mail.Builder("sujet").important(true).build();
		Mail unAutreMail = new Mail.Builder("sujet").important(false).build();
		assertThat(unMail, not(nullValue()));
		assertThat(comparator.compare(unMail, unAutreMail), is(MailComparator.PREMIER_PLUS_PETIT));		
	}
	
	@Test
	public final void ordreAlphabetiqueSiMemeImportance() {
		Mail unMail = new Mail.Builder("sujet").important(true).build();
		Mail unAutreMail = new Mail.Builder("sujet").important(false).build();
		assertThat(unMail, not(nullValue()));
		assertThat(comparator.compare(unMail, unAutreMail), is(MailComparator.PREMIER_PLUS_GRAND));		
	}
	
}
