package com.acme.mailreader.bdd;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

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

	private Mail mail1;
	public Mail getMail1() {
		return mail1;
	}

	public void setMail1(Mail mail1) {
		this.mail1 = mail1;
	}

	public Mail getMail2() {
		return mail2;
	}

	public String getResultatComparaison() {
		return resultatComparaison;
	}

	public void setResultatComparaison(String resultatComparaison) {
		this.resultatComparaison = resultatComparaison;
	}

	public void setMail2(Mail mail2) {
		this.mail2 = mail2;
	}

	private Mail mail2;
	private String resultatComparaison;
	Comparator<Mail> comparator = new MailComparator();
	private static final Map<Integer, String> resuAsString = new HashMap<Integer, String>();
	static {
		resuAsString.put(MailComparator.PREMIER_PLUS_PETIT , "MAIL1_APRES");
		resuAsString.put(MailComparator.EGAUX, "EGAUX");
		resuAsString.put(MailComparator.PREMIER_PLUS_GRAND, "MAIL1_AVANT");
	}
	

	@Given("^un premier mail avec l'importance \"([^\"]*)\", le statut \"([^\"]*)\", le sujet \"([^\"]*)\" et la date \"([^\"]*)\"$")
	public void un_premier_mail(boolean importance, Statut statut,
			String sujet, String date) throws DateIncorrecteException {
		//TODO
	}

	@Given("^un second mail avec l'importance \"([^\"]*)\", le statut \"([^\"]*)\", le sujet \"([^\"]*)\" et la date \"([^\"]*)\"$")
	public void un_second_mail(boolean importance, Statut statut, String sujet,
			String date) throws DateIncorrecteException {
		//TODO
	}

	

	@When("^je trie$")
	public void je_trie() throws Throwable {
		//TODO
	}

	@Then("^le test d'égalité doit retourner \"([^\"]*)\"$")
	public void le_test_d_egalité(String resu) throws Throwable {
		//TODO
		//assertThat(...);
	}
	

}
