package pl.edu.agh.ontology.generator.application;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import pl.edu.agh.ontology.generator.application.exception.ApplicationGeneratorException;
import pl.edu.agh.ontology.generator.application.exception.OntologyFileNotExistsExcpetion;
import pl.edu.agh.ontology.generator.application.exception.PrepareEnvironmentException;

public class Program {

	public static void main(String[] args) {

		CommandLine commandLine = null;
		Option optionApplicationName = new Option("appName", true, "The application name");
		Option optionOntologyFileName = new Option("op", true, "The path to ontology file");
		Option applicationPropertiesFilePath = new Option("appProps", true, "The path to application properties file");
		Options options = new Options();
		options.addOption(optionApplicationName).addOption(optionOntologyFileName).addOption(applicationPropertiesFilePath);
		CommandLineParser parser = new GnuParser();
		try {
			commandLine = parser.parse(options, args);
			Thread applicationGeneratorThread = null;

			if (commandLine.hasOption("appName") == true && commandLine.hasOption("op") == true) {
				String appName = commandLine.getOptionValue("appName");
				String ontologyFilePath = commandLine.getOptionValue("op");
				applicationGeneratorThread = new Thread(new ApplicationGenerator(appName, ontologyFilePath));
			} else if (commandLine.hasOption("appName") == true) {
				String appName = commandLine.getOptionValue("appName");
				applicationGeneratorThread = new Thread(new ApplicationGenerator(appName));
			} else {

				System.out.println("Wymagany conajmniej jeden argument: appName");
				return;

			}

			applicationGeneratorThread.start();

		} catch (ParseException e) {
			e.printStackTrace();
			System.out.println("Generation error");
		} catch (OntologyFileNotExistsExcpetion e) {
			e.printStackTrace();
			System.out.println("Generation error");
		} catch (PrepareEnvironmentException e) {
			e.printStackTrace();
			System.out.println("Generation error");
		} catch (ApplicationGeneratorException e) {
			e.printStackTrace();
			System.out.println("Generation error");
		}

	}
}
