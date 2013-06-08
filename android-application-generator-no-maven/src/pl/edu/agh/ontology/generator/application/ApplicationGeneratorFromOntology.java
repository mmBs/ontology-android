package pl.edu.agh.ontology.generator.application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.channels.FileChannel;
import java.util.Date;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLDataPropertyExpression;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;

import pl.edu.agh.ontology.generator.application.api.PropertiesApplicationInformation;
import pl.edu.agh.ontology.generator.application.domain.AndroidApplicationFactory;
import pl.edu.agh.ontology.generator.application.domain.Application;
import pl.edu.agh.ontology.generator.application.domain.MainActivity;
import pl.edu.agh.ontology.generator.application.domain.TextView;
import pl.edu.agh.ontology.generator.application.exception.ApplicationGeneratorException;
import pl.edu.agh.ontology.generator.application.exception.OntologyFileNotExistsExcpetion;
import pl.edu.agh.ontology.generator.application.exception.PrepareEnvironmentException;
import pl.edu.agh.ontology.generator.application.helper.MavenProjectBuilder;
import pl.edu.agh.ontology.generator.application.helper.exception.MavenProjectException;
import pl.edu.agh.ontology.generator.application.writer.AndroidStringResources;

/**
 * WORKING NOW
 */

public class ApplicationGeneratorFromOntology implements Runnable {

	private File workingDirectory;
	private File ontologyFile;
	private String applicationName;

	public ApplicationGeneratorFromOntology(String applicationName, String androidApplicationOntologyFilePath) throws ApplicationGeneratorException {
		this.applicationName = applicationName;
		File file = new File(androidApplicationOntologyFilePath);
		checkIfExistsOntologyFile(file);
		ontologyFile = file;
		prepareEnvironment();
	}

	public ApplicationGeneratorFromOntology(String applicationName) throws ApplicationGeneratorException {
		this.applicationName = applicationName;
		File file = new File("android-ontology.owl");
		checkIfExistsOntologyFile(file);
		ontologyFile = file;
		prepareEnvironment();
	}

	private void checkIfExistsOntologyFile(File file) throws ApplicationGeneratorException {
		if (file.exists() == false) {
			throw new OntologyFileNotExistsExcpetion(file);
		}
	}

	private void prepareEnvironment() throws PrepareEnvironmentException {
		workingDirectory = new File("workingDirectory");
		if (workingDirectory.exists() == false) {
			if (workingDirectory.mkdir() == false) {
				throw new PrepareEnvironmentException("Working directory cannot be created. Execution fail.");
			} else {
				System.out.println("Environment prepared...");
			}
		} else {
			if (workingDirectory.isDirectory() == false) {
				throw new PrepareEnvironmentException("Working directory cannot be created. File with the same name exists.");
			} else {
				System.out.println("Environment prepared...");
			}
		}
	}

	private File createDirectory(File parent, String name) throws ApplicationGeneratorException {
		File directory = new File(parent, name);
		if (directory.mkdir() == false) {
			throw new ApplicationGeneratorException(name + " directory cannot be created");
		}
		return directory;
	}

	private File createFile(File parent, String name) throws ApplicationGeneratorException {
		File file = new File(parent, name);
		try {
			if (file.createNewFile() == false) {
				throw new ApplicationGeneratorException(name + " file cannot be created");
			}
		} catch (IOException e) {
			throw new ApplicationGeneratorException(name + " file cannot be created", e);
		}
		return file;
	}

	private File createFile(String name) throws ApplicationGeneratorException {
		File file = new File(name);
		try {
			if (file.createNewFile() == false) {
				throw new ApplicationGeneratorException(name + " file cannot be created");
			}
		} catch (IOException e) {
			throw new ApplicationGeneratorException(name + " file cannot be created", e);
		}
		return file;
	}

	public static void copyFile(File sourceFile, File destFile) throws IOException {
		if (!destFile.exists()) {
			destFile.createNewFile();
		}

		FileChannel source = null;
		FileChannel destination = null;
		try {
			source = new FileInputStream(sourceFile).getChannel();
			destination = new FileOutputStream(destFile).getChannel();

			long count = 0;
			long size = source.size();
			while ((count += destination.transferFrom(source, count, size - count)) < size)
				;
		} finally {
			if (source != null) {
				source.close();
			}
			if (destination != null) {
				destination.close();
			}
		}
	}

	private synchronized void writeTextToFile(File file, String text) {

		PrintWriter out = null;

		try {
			out = new PrintWriter(file);
			out.write(text);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			out.close();
		}

	}

	@Override
	public void run() {

		String applicationLabel = null;
		String applicationIcon = null;
		String applicationTheme = null;

		OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
		OWLOntology androidApplicationOntology = null;

		try {
			androidApplicationOntology = manager.loadOntologyFromOntologyDocument(ontologyFile);
		} catch (OWLOntologyCreationException e) {
			e.printStackTrace();
			System.out.println("Generation error");
			return;
		}

		AndroidApplicationFactory androidApplicationFactory = new AndroidApplicationFactory(androidApplicationOntology);

		Application application = androidApplicationFactory.getApplication("http://www.agh.edu.pl/eaiib/android/ontology" + applicationName);

		if (application == null) {
			System.out.println("Application not found - Generation error... ");
		}

		for (OWLDataPropertyExpression dataPropertyExpression : application.getOwlIndividual().getDataPropertyValues(androidApplicationOntology).keySet()) {
			String dataPropertyExpressionIRI = dataPropertyExpression.toString();
			for (OWLLiteral literal : application.getOwlIndividual().getDataPropertyValues(androidApplicationOntology).get(dataPropertyExpression)) {

				// System.out.println("dataPropertyExpressionIRI=" + dataPropertyExpressionIRI);
				if (dataPropertyExpressionIRI.contains("hasLabel")) {
					applicationLabel = literal.getLiteral();
				} else if (dataPropertyExpressionIRI.contains("hasIcon")) {
					applicationIcon = literal.getLiteral();
				} else if (dataPropertyExpressionIRI.contains("hasTheme")) {
					applicationTheme = literal.getLiteral();
				}
			}
		}

		MainActivity mainActivity = null;
		if (application.getHasMainActivity().size() == 1) {
			MainActivity[] mainActivities = application.getHasMainActivity().toArray(new MainActivity[application.getHasMainActivity().size()]);
			mainActivity = mainActivities[0];
		} else {
			System.out.println("Error to much MainActivity added to application.");
			return;
		}

		String applicationText = null;
		String firstNameText = null;
		String lastNameText = null;
		String buttonText = null;

		for (TextView textView : mainActivity.getHasStructure()) {

			String textViewIRI = textView.getOwlIndividual().toString();
			// System.out.println("textViewIRI=" + textViewIRI);

			if (textViewIRI.contains("TextView")) {

				// System.out.println("&&&& TextView &&&&");
				for (OWLDataPropertyExpression dataPropertyExpression : textView.getOwlIndividual().getDataPropertyValues(androidApplicationOntology).keySet()) {
					String dataPropertyExpressionIRI = dataPropertyExpression.toString();
					for (OWLLiteral literal : textView.getOwlIndividual().getDataPropertyValues(androidApplicationOntology).get(dataPropertyExpression)) {

						// System.out.println("dataPropertyExpressionIRI=" + dataPropertyExpressionIRI);
						if (dataPropertyExpressionIRI.contains("hasText")) {
							applicationText = literal.getLiteral();
						}
					}
				}
				// System.out.println("&&&&&&&&");

			} else if (textViewIRI.contains("Button")) {
				// System.out.println("&&&& Button &&&&");
				for (OWLDataPropertyExpression dataPropertyExpression : textView.getOwlIndividual().getDataPropertyValues(androidApplicationOntology).keySet()) {
					String dataPropertyExpressionIRI = dataPropertyExpression.toString();
					for (OWLLiteral literal : textView.getOwlIndividual().getDataPropertyValues(androidApplicationOntology).get(dataPropertyExpression)) {

						// System.out.println("dataPropertyExpressionIRI=" + dataPropertyExpressionIRI);
						if (dataPropertyExpressionIRI.contains("hasText")) {
							buttonText = literal.getLiteral();
						}
					}
				}
				// System.out.println("&&&&&&&&");

			} else if (textViewIRI.contains("FirstName")) {
				// System.out.println("&&&& FirstName &&&&");
				for (OWLDataPropertyExpression dataPropertyExpression : textView.getOwlIndividual().getDataPropertyValues(androidApplicationOntology).keySet()) {
					String dataPropertyExpressionIRI = dataPropertyExpression.toString();
					for (OWLLiteral literal : textView.getOwlIndividual().getDataPropertyValues(androidApplicationOntology).get(dataPropertyExpression)) {

						// System.out.println("dataPropertyExpressionIRI=" + dataPropertyExpressionIRI);
						if (dataPropertyExpressionIRI.contains("hasHint")) {
							firstNameText = literal.getLiteral();
						}
					}
				}
				// System.out.println("&&&&&&&&");

			} else if (textViewIRI.contains("LastName")) {
				// System.out.println("&&&& LastName &&&&");
				for (OWLDataPropertyExpression dataPropertyExpression : textView.getOwlIndividual().getDataPropertyValues(androidApplicationOntology).keySet()) {
					String dataPropertyExpressionIRI = dataPropertyExpression.toString();
					// System.out.println("dataPropertyExpressionIRI=" + dataPropertyExpressionIRI);
					for (OWLLiteral literal : textView.getOwlIndividual().getDataPropertyValues(androidApplicationOntology).get(dataPropertyExpression)) {

						// System.out.println("dataPropertyExpressionIRI=" + dataPropertyExpressionIRI);
						if (dataPropertyExpressionIRI.contains("hasHint")) {
							lastNameText = literal.getLiteral();
						}
					}
				}
				// System.out.println("&&&&&&&&");

			}
		}

		System.out.println("applicationLabel=" + applicationLabel);
		System.out.println("applicationIcon=" + applicationIcon);
		System.out.println("applicationTheme=" + applicationTheme);
		System.out.println("applicationText=" + applicationText);
		System.out.println("buttonText=" + buttonText);
		System.out.println("firstNameText=" + firstNameText);
		System.out.println("lastNameText=" + lastNameText);

		Date projectDate = new Date();
		File projectDirectory = null;
		File res = null;
		File src = null;

		File drawableHdpi = null;
		File drawableMdpi = null;
		File drawableXhdpi = null;
		File drawableXxhdpi = null;
		File layout = null;
		File menu = null;
		File values = null;
		File valuesSw600dp = null;
		File valuesSw720dpLand = null;
		File valuesV11 = null;
		File valuesV14 = null;

		File androidManifest = null;
		File pom = null;

		File srcPMain = null;
		File srcPJava = null;
		File srcPCom = null;
		File srcPOntology = null;
		File srcPAndroid = null;
		File mainActivityClass = null;

		try {

			projectDirectory = createDirectory(workingDirectory, applicationName + projectDate.getTime());
			res = createDirectory(projectDirectory, "res");
			src = createDirectory(projectDirectory, "src");

			drawableHdpi = createDirectory(res, "drawable-hdpi");
			drawableMdpi = createDirectory(res, "drawable-mdpi");
			drawableXhdpi = createDirectory(res, "drawable-xhdpi");
			drawableXxhdpi = createDirectory(res, "drawable-xxhdpi");
			layout = createDirectory(res, "layout");
			menu = createDirectory(res, "menu");
			values = createDirectory(res, "values");
			valuesSw600dp = createDirectory(res, "values-sw600dp");
			valuesSw720dpLand = createDirectory(res, "values-sw720dp-land");
			valuesV11 = createDirectory(res, "values-v11");
			valuesV14 = createDirectory(res, "values-v14");

			androidManifest = createFile(projectDirectory, "AndroidManifest.xml");
			pom = createFile(projectDirectory, "pom.xml");

			srcPMain = createDirectory(src, "main");
			srcPJava = createDirectory(srcPMain, "java");
			srcPCom = createDirectory(srcPJava, "com");
			srcPOntology = createDirectory(srcPCom, "ontology");
			srcPAndroid = createDirectory(srcPOntology, "android");
			mainActivityClass = createFile(srcPAndroid, "MainActivity.java");

			File hdpiIcon = new File(applicationIcon + "_hdpi.png");
			File mdpiIcon = new File(applicationIcon + "_mdpi.png");
			File xhdpiIcon = new File(applicationIcon + "_xhdpi.png");
			File xxhdpiIcon = new File(applicationIcon + "_xxhdpi.png");

			File hdpiIconDest = createFile(drawableHdpi, "ic_launcher.png");
			File mdpiIconDest = createFile(drawableMdpi, "ic_launcher.png");
			File xhdpiIconDest = createFile(drawableXhdpi, "ic_launcher.png");
			File xxhdpiIconDest = createFile(drawableXxhdpi, "ic_launcher.png");

			copyFile(hdpiIcon, hdpiIconDest);
			copyFile(mdpiIcon, mdpiIconDest);
			copyFile(xhdpiIcon, xhdpiIconDest);
			copyFile(xxhdpiIcon, xxhdpiIconDest);

			writeTextToFile(mainActivityClass, StringConstansHelper.MAIN_ACTIVITY_CLASS);
			writeTextToFile(pom, StringConstansHelper.POM);
			writeTextToFile(androidManifest, StringConstansHelper.ANDROID_MANIFEST);

			File activityMainXML = new File(layout, "activity_main.xml");
			writeTextToFile(activityMainXML, StringConstansHelper.ACTIVITY_MAIN);

			File resMenuMain = new File(menu, "main.xml");
			writeTextToFile(resMenuMain, StringConstansHelper.RES_MENU_MAIN);

			File valuesDimensXML = new File(values, "dimens.xml");
			File valuesStringsXML = new File(values, "strings.xml");
			File valuesStylesXML = new File(values, "styles.xml");

			AndroidStringResources androidStringResources = new AndroidStringResources();
			androidStringResources.addString("app_name", applicationLabel);
			androidStringResources.addString("action_settings", "Settings");
			androidStringResources.addString("tv_text", applicationText);
			androidStringResources.addString("et_name", firstNameText);
			androidStringResources.addString("et_surname", lastNameText);
			androidStringResources.addString("btn_action", buttonText);

			writeTextToFile(valuesDimensXML, StringConstansHelper.VALUES_DIMENS);
			writeTextToFile(valuesStringsXML, androidStringResources.generateStringsXML());
			writeTextToFile(valuesStylesXML, StringConstansHelper.VALUES_STYLES);

			File valuesSw600dpDimensXML = new File(valuesSw600dp, "dimens.xml");
			writeTextToFile(valuesSw600dpDimensXML, StringConstansHelper.VALUES_SW600DP_DIMENS);

			File valuesSw720dpDimensXML = new File(valuesSw720dpLand, "dimens.xml");
			writeTextToFile(valuesSw720dpDimensXML, StringConstansHelper.VALUES_SW720DP_DIMENS);

			File valuesV11StylesXML = new File(valuesV11, "styles.xml");
			writeTextToFile(valuesV11StylesXML, StringConstansHelper.VALUES_V11_STYLES);

			File valuesV14StylesXML = new File(valuesV14, "styles.xml");
			writeTextToFile(valuesV14StylesXML, StringConstansHelper.VALUES_V14_STYLES);

		} catch (ApplicationGeneratorException e) {
			e.printStackTrace();
			System.out.println("Generation error");
			return;
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Generation error");
			return;
		}

		try {
			MavenProjectBuilder.buildProject(pom.getAbsolutePath());
		} catch (MavenProjectException e) {
			e.printStackTrace();
			System.out.println("Generation error");
			return;
		}

		System.out.println("Generation success");

	}
}
