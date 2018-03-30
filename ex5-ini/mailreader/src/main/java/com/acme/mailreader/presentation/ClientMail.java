package com.acme.mailreader.presentation;

import com.acme.mailreader.commun.MailReaderModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Point d'entrée de notre client mail
 * <p>
 * Exemple d'utilisation de l'injection de dependance
 */
public class ClientMail {

	
	private static boolean production;

	public static void main(String[] args) {
		production = Boolean.parseBoolean(args[0]);
		Injector injector = Guice.createInjector(new MailReaderModule(
				production));
		String result = "" ;
		/*
		 * Now that we've got the injector, we can build objects.
		 */
		InterpreteurLignecommande cli = injector.getInstance(InterpreteurLignecommande.class);
		result = cli.nouveauMail(args);
		System.out.println(result);
	}

	

}