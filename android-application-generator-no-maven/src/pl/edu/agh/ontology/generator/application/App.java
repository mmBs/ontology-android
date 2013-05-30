package pl.edu.agh.ontology.generator.application;

import java.io.File;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLDataPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;

import pl.edu.agh.ontology.generator.application.domain.AndroidApplicationFactory;
import pl.edu.agh.ontology.generator.application.domain.Application;
import pl.edu.agh.ontology.generator.application.domain.Button;
import pl.edu.agh.ontology.generator.application.domain.EditText;
import pl.edu.agh.ontology.generator.application.domain.OnClickListener;
import pl.edu.agh.ontology.generator.application.domain.SimpleMainActivity;
import pl.edu.agh.ontology.generator.application.domain.TextView;
import pl.edu.agh.ontology.generator.application.domain.Vocabulary;

/**
 * 
 */
public class App {
	private static final String ANDROID_APPLICATION_IRI = "android-ontology.owl";
	private static final String APP_NAME = "_FIRST";

	public static void main(String[] args) {

		OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
		File file = new File(ANDROID_APPLICATION_IRI);
		OWLOntology androidApplicationOntology = null;

		try {
			androidApplicationOntology = manager.loadOntologyFromOntologyDocument(file);
		} catch (OWLOntologyCreationException e) {
			e.printStackTrace();
		}

		AndroidApplicationFactory androidApplicationFactory = new AndroidApplicationFactory(androidApplicationOntology);

		Application application = androidApplicationFactory.createApplication("Application" + APP_NAME);

		OWLDataProperty hasLabel = Vocabulary.DATA_PROPERTY_HASLABEL;
		OWLDataProperty hasIcon = Vocabulary.DATA_PROPERTY_HASICON;
		OWLDataProperty hasTheme = Vocabulary.DATA_PROPERTY_HASTHEME;

		OWLDataPropertyAssertionAxiom hasLabelDataPropertyAssertion = manager.getOWLDataFactory().getOWLDataPropertyAssertionAxiom(hasLabel, application.getOwlIndividual(), "hasLabel-VALUE");
		manager.addAxiom(androidApplicationOntology, hasLabelDataPropertyAssertion);

		OWLDataPropertyAssertionAxiom hasIconDataPropertyAssertion = manager.getOWLDataFactory().getOWLDataPropertyAssertionAxiom(hasIcon, application.getOwlIndividual(), "hasIcon-VALUE");
		manager.addAxiom(androidApplicationOntology, hasIconDataPropertyAssertion);

		OWLDataPropertyAssertionAxiom hasThemeDataPropertyAssertion = manager.getOWLDataFactory().getOWLDataPropertyAssertionAxiom(hasTheme, application.getOwlIndividual(), "hasTheme-VALUE");
		manager.addAxiom(androidApplicationOntology, hasThemeDataPropertyAssertion);

		// set for application: - set android:allowBackup
		// - set icon (android:icon)
		// - set label (android:label=”@string/address_to_string”
		// - set theme android:theme
		// - set intent-filter

		/*
		 * - res folder which contains: - drawable-mdpi, hdpi, xhdpi, xxhdpi folders - layout folder with hardcoded name activity_main.xml - menu folder - values folder where we generate:
		 * 
		 * - dimens.xml - styles.xml - strings.xml - values-sw600dp folder (includes dimens.xml) - values-sw720dp folder (includes dimens.xml) - values-v11 folder (includes style.xml) - values-v14 folder (includes style.xml)
		 * 
		 * - src/main/java/(maven) folder
		 */

		SimpleMainActivity simpleMainActivity = androidApplicationFactory.createSimpleMainActivity("Simple" + APP_NAME);
		/*
		 * 
		 * generates:
		 * 
		 * <ScrollView> + close tags <LinerarLayout> + close tags onCreateOptionsMenu(Menu menu) method
		 */

		TextView textView = androidApplicationFactory.createTextView("Introduction" + APP_NAME);

		OWLDataProperty hasText = Vocabulary.DATA_PROPERTY_HASTEXT;
		OWLDataPropertyAssertionAxiom hasTextDataPropertyAssertion = manager.getOWLDataFactory().getOWLDataPropertyAssertionAxiom(hasText, textView.getOwlIndividual(), "hasText-textView-VALUE");
		manager.addAxiom(androidApplicationOntology, hasTextDataPropertyAssertion);
		/*
		 * 
		 * generates
		 * 
		 * - string tv_text "default text"
		 */

		EditText edit1 = androidApplicationFactory.createEditText("First_Name" + APP_NAME);
		OWLDataProperty hasHint1 = Vocabulary.DATA_PROPERTY_HASHINT;
		OWLDataPropertyAssertionAxiom hasHint1DataPropertyAssertion = manager.getOWLDataFactory().getOWLDataPropertyAssertionAxiom(hasHint1, edit1.getOwlIndividual(), "hasHint1-edit1-VALUE");
		manager.addAxiom(androidApplicationOntology, hasHint1DataPropertyAssertion);

		EditText edit2 = androidApplicationFactory.createEditText("Last_Name" + APP_NAME);
		OWLDataProperty hasHint2 = Vocabulary.DATA_PROPERTY_HASHINT;
		OWLDataPropertyAssertionAxiom hasHint2DataPropertyAssertion = manager.getOWLDataFactory().getOWLDataPropertyAssertionAxiom(hasHint2, edit2.getOwlIndividual(), "hasHint2-edit2-VALUE");
		manager.addAxiom(androidApplicationOntology, hasHint2DataPropertyAssertion);
		/*
		 * 
		 * generates:
		 * 
		 * - string et_name i et_surname
		 */
		Button button = androidApplicationFactory.createButton("Show" + APP_NAME);
		OWLDataProperty hasTextButton = Vocabulary.DATA_PROPERTY_HASTEXT;
		OWLDataPropertyAssertionAxiom hasTextButtonDataPropertyAssertion = manager.getOWLDataFactory().getOWLDataPropertyAssertionAxiom(hasTextButton, button.getOwlIndividual(), "hasTextButton-VALUE");
		manager.addAxiom(androidApplicationOntology, hasTextButtonDataPropertyAssertion);
		/*
		 * generates
		 * 
		 * - string btn_action
		 */

		OnClickListener onClickButtonListener = androidApplicationFactory.createOnClickListener("PRESS_BUTTON" + APP_NAME);
		/*
		 * Wiadomo
		 */

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
		}
		
		System.out.println("END");
	}
}
