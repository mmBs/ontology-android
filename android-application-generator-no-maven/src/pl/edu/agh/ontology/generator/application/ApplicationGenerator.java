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
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLDataPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;

import pl.edu.agh.ontology.generator.application.api.ApplicationInformation;
import pl.edu.agh.ontology.generator.application.api.PropertiesApplicationInformation;
import pl.edu.agh.ontology.generator.application.domain.AndroidApplicationFactory;
import pl.edu.agh.ontology.generator.application.domain.Application;
import pl.edu.agh.ontology.generator.application.domain.Button;
import pl.edu.agh.ontology.generator.application.domain.EditText;
import pl.edu.agh.ontology.generator.application.domain.OnClickListener;
import pl.edu.agh.ontology.generator.application.domain.SimpleMainActivity;
import pl.edu.agh.ontology.generator.application.domain.TextView;
import pl.edu.agh.ontology.generator.application.domain.Vocabulary;
import pl.edu.agh.ontology.generator.application.exception.ApplicationGeneratorException;
import pl.edu.agh.ontology.generator.application.exception.OntologyFileNotExistsExcpetion;
import pl.edu.agh.ontology.generator.application.exception.PrepareEnvironmentException;
import pl.edu.agh.ontology.generator.application.helper.MavenProjectBuilder;
import pl.edu.agh.ontology.generator.application.helper.exception.MavenProjectException;
import pl.edu.agh.ontology.generator.application.writer.AndroidStringResources;

public class ApplicationGenerator implements Runnable {

	private ApplicationInformation applicationInformationFile;
	private File workingDirectory;
	private File ontologyFile;
	private String applicationName;

	public ApplicationGenerator(String applicationName, String androidApplicationOntologyFilePath) throws ApplicationGeneratorException {
		this.applicationName = applicationName;
		File file = new File(androidApplicationOntologyFilePath);
		checkIfExistsOntologyFile(file);
		ontologyFile = file;
		applicationInformationFile = new PropertiesApplicationInformation();
		prepareEnvironment();
	}

	public ApplicationGenerator(String applicationName) throws ApplicationGeneratorException {
		this.applicationName = applicationName;
		File file = new File("android-ontology.owl");
		checkIfExistsOntologyFile(file);
		ontologyFile = file;
		applicationInformationFile = new PropertiesApplicationInformation();
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

	private void writeTextToFile(File file, String text) {

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

			File hdpiIcon = new File((String) applicationInformationFile.getValue("application.main.view.icon.path.drawable") + "_hdpi.png");
			File mdpiIcon = new File((String) applicationInformationFile.getValue("application.main.view.icon.path.drawable") + "_mdpi.png");
			File xhdpiIcon = new File((String) applicationInformationFile.getValue("application.main.view.icon.path.drawable") + "_xhdpi.png");
			File xxhdpiIcon = new File((String) applicationInformationFile.getValue("application.main.view.icon.path.drawable") + "_xxhdpi.png");

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
			androidStringResources.addString("app_name", (String) applicationInformationFile.getValue("application.label"));
			androidStringResources.addString("action_settings", "Settings");
			androidStringResources.addString("tv_text", (String) applicationInformationFile.getValue("application.main.view.tv_text"));
			androidStringResources.addString("et_name", (String) applicationInformationFile.getValue("application.main.view.et_name"));
			androidStringResources.addString("et_surname", (String) applicationInformationFile.getValue("application.main.view.et_surname"));
			androidStringResources.addString("btn_action", (String) applicationInformationFile.getValue("application.main.view.btn_action"));

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

		Application application = androidApplicationFactory.createApplication("#Application"+applicationName);
		System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
		System.out.println("application.getOwlIndividual().getIRI()=" + application.getOwlIndividual().getIRI());
		System.out.println("Vocabulary.CLASS_APPLICATION.toString()=" + application.getOwlIndividual().toStringID());
		System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
		OWLDataProperty hasLabel = Vocabulary.DATA_PROPERTY_HASLABEL;
		OWLDataProperty hasIcon = Vocabulary.DATA_PROPERTY_HASICON;
		OWLDataProperty hasTheme = Vocabulary.DATA_PROPERTY_HASTHEME;

		OWLDataPropertyAssertionAxiom hasLabelDataPropertyAssertion = manager.getOWLDataFactory().getOWLDataPropertyAssertionAxiom(hasLabel, application.getOwlIndividual(), (String) applicationInformationFile.getValue("application.label"));
		manager.addAxiom(androidApplicationOntology, hasLabelDataPropertyAssertion);

		OWLDataPropertyAssertionAxiom hasIconDataPropertyAssertion = manager.getOWLDataFactory().getOWLDataPropertyAssertionAxiom(hasIcon, application.getOwlIndividual(), (String) applicationInformationFile.getValue("application.main.view.icon.path.drawable"));
		manager.addAxiom(androidApplicationOntology, hasIconDataPropertyAssertion);

		OWLDataPropertyAssertionAxiom hasThemeDataPropertyAssertion = manager.getOWLDataFactory().getOWLDataPropertyAssertionAxiom(hasTheme, application.getOwlIndividual(), (String) applicationInformationFile.getValue("application.theme"));
		manager.addAxiom(androidApplicationOntology, hasThemeDataPropertyAssertion);

		SimpleMainActivity simpleMainActivity = androidApplicationFactory.createSimpleMainActivity("MainActivity_" + applicationName);

		TextView textView = androidApplicationFactory.createTextView("TextView_" + applicationName);

		OWLDataProperty hasText = Vocabulary.DATA_PROPERTY_HASTEXT;
		OWLDataPropertyAssertionAxiom hasTextDataPropertyAssertion = manager.getOWLDataFactory().getOWLDataPropertyAssertionAxiom(hasText, textView.getOwlIndividual(), (String) applicationInformationFile.getValue("application.main.view.tv_text"));
		manager.addAxiom(androidApplicationOntology, hasTextDataPropertyAssertion);

		EditText edit1 = androidApplicationFactory.createEditText("FirstName_" + applicationName);
		OWLDataProperty hasHint1 = Vocabulary.DATA_PROPERTY_HASHINT;
		OWLDataPropertyAssertionAxiom hasHint1DataPropertyAssertion = manager.getOWLDataFactory().getOWLDataPropertyAssertionAxiom(hasHint1, edit1.getOwlIndividual(), (String) applicationInformationFile.getValue("application.main.view.et_surname"));
		manager.addAxiom(androidApplicationOntology, hasHint1DataPropertyAssertion);

		EditText edit2 = androidApplicationFactory.createEditText("LastName_" + applicationName);
		OWLDataProperty hasHint2 = Vocabulary.DATA_PROPERTY_HASHINT;
		OWLDataPropertyAssertionAxiom hasHint2DataPropertyAssertion = manager.getOWLDataFactory().getOWLDataPropertyAssertionAxiom(hasHint2, edit2.getOwlIndividual(), (String) applicationInformationFile.getValue("application.main.view.et_name"));
		manager.addAxiom(androidApplicationOntology, hasHint2DataPropertyAssertion);

		Button button = androidApplicationFactory.createButton("Button_" + applicationName);
		OWLDataProperty hasTextButton = Vocabulary.DATA_PROPERTY_HASTEXT;
		OWLDataPropertyAssertionAxiom hasTextButtonDataPropertyAssertion = manager.getOWLDataFactory().getOWLDataPropertyAssertionAxiom(hasTextButton, button.getOwlIndividual(), (String) applicationInformationFile.getValue("application.main.view.btn_action"));
		manager.addAxiom(androidApplicationOntology, hasTextButtonDataPropertyAssertion);

		OnClickListener onClickButtonListener = androidApplicationFactory.createOnClickListener("OnClickListener_" + applicationName);

		button.addHasListeners(onClickButtonListener);

		simpleMainActivity.addHasStructure(textView);
		simpleMainActivity.addHasStructure(edit1);
		simpleMainActivity.addHasStructure(edit2);
		simpleMainActivity.addHasStructure(button);

		application.addHasMainActivity(simpleMainActivity);

		try {
			androidApplicationFactory.saveOwlOntology();
		} catch (OWLOntologyStorageException e) {
			e.printStackTrace();
			System.out.println("Generation error");
			return;
		}

		System.out.println("Generation success");

	}
}
