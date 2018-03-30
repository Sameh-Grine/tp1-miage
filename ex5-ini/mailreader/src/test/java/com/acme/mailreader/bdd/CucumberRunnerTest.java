package com.acme.mailreader.bdd;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = { "pretty", "html:target/cucumber" }, features = { "src/test/resources/" })
/**
 * Lanceur des tests BDD
 * @author bflorat
 *
 */
public class CucumberRunnerTest {
	// Classe vide à dessein
}