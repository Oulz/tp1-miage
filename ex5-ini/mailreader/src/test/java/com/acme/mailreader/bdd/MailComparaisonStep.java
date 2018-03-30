package com.acme.mailreader.bdd;

import java.time.Instant;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;

import com.acme.mailreader.domain.Mail;
import com.acme.mailreader.domain.Mail.Statut;
import com.acme.mailreader.domain.MailComparator;
import com.acme.mailreader.utils.DateIncorrecteException;

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

public class MailComparaisonStep {
	private Mail unMail;
	private Mail unAutreMail;
	private String resultatComparaison;
	Comparator<Mail> comparator = new MailComparator();
	public Mail getunMail() {
		return unMail;
	}

	public void setunMail(Mail unMail) {
		this.unMail = unMail;
	}

	public Mail getunAutreMail() {
		return unAutreMail;
	}

	public String getResultatComparaison() {
		return resultatComparaison;
	}

	public void setResultatComparaison(String resultatComparaison) {
		this.resultatComparaison = resultatComparaison;
	}

	public void setunAutreMail(Mail unAutreMail) {
		this.unAutreMail = unAutreMail;
	}


	private static final Map<Integer, String> resuAsString = new HashMap<Integer, String>();
	static {
		resuAsString.put(MailComparator.PREMIER_PLUS_PETIT , "unMail_APRES");
		resuAsString.put(MailComparator.EGAUX, "EGAUX");
		resuAsString.put(MailComparator.PREMIER_PLUS_GRAND, "unMail_AVANT");
	}
	

	@Given("^un premier mail avec l'importance \"([^\"]*)\", le statut \"([^\"]*)\", le sujet \"([^\"]*)\" et la date \"([^\"]*)\"$")
	public void un_premier_mail(boolean importance, Statut statut,
			String sujet, String date) throws DateIncorrecteException {
			unMail = new Mail.Builder(sujet).date(Instant.parse(date)).important(importance).statut(statut).build();
	}

	@Given("^un second mail avec l'importance \"([^\"]*)\", le statut \"([^\"]*)\", le sujet \"([^\"]*)\" et la date \"([^\"]*)\"$")
	public void un_second_mail(boolean importance, Statut statut, String sujet,
			String date) throws DateIncorrecteException {
		unAutreMail = new Mail.Builder(sujet).date(Instant.parse(date)).important(importance).statut(statut).build();

	}

	

	@When("^je trie$")
	public void je_trie() throws Throwable {
		resultatComparaison = resuAsString.get(comparator.compare(unMail, unAutreMail));
	}

	@Then("^le test d'égalité doit retourner \"([^\"]*)\"$")
	public void le_test_d_egalité(String resu) throws Throwable {
		Assert.assertEquals(resu, resultatComparaison);
	}
	
	
	
	

}
