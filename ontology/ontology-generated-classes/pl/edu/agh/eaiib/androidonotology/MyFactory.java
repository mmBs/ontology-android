package pl.edu.agh.eaiib.androidonotology;

import pl.edu.agh.eaiib.androidonotology.impl.*;

import java.util.Collection;

import org.protege.owl.codegeneration.CodeGenerationFactory;
import org.protege.owl.codegeneration.WrappedIndividual;
import org.protege.owl.codegeneration.impl.FactoryHelper;
import org.protege.owl.codegeneration.impl.ProtegeJavaMapping;
import org.protege.owl.codegeneration.inference.CodeGenerationInference;
import org.protege.owl.codegeneration.inference.SimpleInference;

import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;

/**
 * A class that serves as the entry point to the generated code providing access
 * to existing individuals in the ontology and the ability to create new individuals in the ontology.<p>
 * 
 * Generated by Protege (http://protege.stanford.edu).<br>
 * Source Class: MyFactory<br>
 * @version generated on Sun May 19 20:09:37 CEST 2013 by Michal
 */
public class MyFactory implements CodeGenerationFactory {
    private OWLOntology ontology;
    private ProtegeJavaMapping javaMapping = new ProtegeJavaMapping();
    private FactoryHelper delegate;
    private CodeGenerationInference inference;

    public MyFactory(OWLOntology ontology) {
	    this(ontology, new SimpleInference(ontology));
    }
    
    public MyFactory(OWLOntology ontology, CodeGenerationInference inference) {
        this.ontology = ontology;
        this.inference = inference;
        javaMapping.initialize(ontology, inference);
        delegate = new FactoryHelper(ontology, inference);
    }

    public OWLOntology getOwlOntology() {
        return ontology;
    }
    
    public void saveOwlOntology() throws OWLOntologyStorageException {
        ontology.getOWLOntologyManager().saveOntology(ontology);
    }
    
    public void flushOwlReasoner() {
        delegate.flushOwlReasoner();
    }
    
    public boolean canAs(WrappedIndividual resource, Class<? extends WrappedIndividual> javaInterface) {
    	return javaMapping.canAs(resource, javaInterface);
    }
    
    public  <X extends WrappedIndividual> X as(WrappedIndividual resource, Class<? extends X> javaInterface) {
    	return javaMapping.as(resource, javaInterface);
    }
    
    public Class<?> getJavaInterfaceFromOwlClass(OWLClass cls) {
        return javaMapping.getJavaInterfaceFromOwlClass(cls);
    }
    
    public OWLClass getOwlClassFromJavaInterface(Class<?> javaInterface) {
	    return javaMapping.getOwlClassFromJavaInterface(javaInterface);
    }
    
    public CodeGenerationInference getInference() {
        return inference;
    }

    /* ***************************************************
     * Class http://www.semanticweb.org/michal/ontologies/2013/4/untitled-ontology-15#Activity
     */

    {
        javaMapping.add("http://www.semanticweb.org/michal/ontologies/2013/4/untitled-ontology-15#Activity", Activity.class, DefaultActivity.class);
    }

    /**
     * Creates an instance of type Activity.  Modifies the underlying ontology.
     */
    public Activity createActivity(String name) {
		return delegate.createWrappedIndividual(name, Vocabulary.CLASS_ACTIVITY, DefaultActivity.class);
    }

    /**
     * Gets an instance of type Activity with the given name.  Does not modify the underlying ontology.
     * @param name the name of the OWL named individual to be retrieved.
     */
    public Activity getActivity(String name) {
		return delegate.getWrappedIndividual(name, Vocabulary.CLASS_ACTIVITY, DefaultActivity.class);
    }

    /**
     * Gets all instances of Activity from the ontology.
     */
    public Collection<? extends Activity> getAllActivityInstances() {
		return delegate.getWrappedIndividuals(Vocabulary.CLASS_ACTIVITY, DefaultActivity.class);
    }


    /* ***************************************************
     * Class http://www.semanticweb.org/michal/ontologies/2013/4/untitled-ontology-15#Application
     */

    {
        javaMapping.add("http://www.semanticweb.org/michal/ontologies/2013/4/untitled-ontology-15#Application", Application.class, DefaultApplication.class);
    }

    /**
     * Creates an instance of type Application.  Modifies the underlying ontology.
     */
    public Application createApplication(String name) {
		return delegate.createWrappedIndividual(name, Vocabulary.CLASS_APPLICATION, DefaultApplication.class);
    }

    /**
     * Gets an instance of type Application with the given name.  Does not modify the underlying ontology.
     * @param name the name of the OWL named individual to be retrieved.
     */
    public Application getApplication(String name) {
		return delegate.getWrappedIndividual(name, Vocabulary.CLASS_APPLICATION, DefaultApplication.class);
    }

    /**
     * Gets all instances of Application from the ontology.
     */
    public Collection<? extends Application> getAllApplicationInstances() {
		return delegate.getWrappedIndividuals(Vocabulary.CLASS_APPLICATION, DefaultApplication.class);
    }


    /* ***************************************************
     * Class http://www.semanticweb.org/michal/ontologies/2013/4/untitled-ontology-15#Application1
     */

    {
        javaMapping.add("http://www.semanticweb.org/michal/ontologies/2013/4/untitled-ontology-15#Application1", Application1.class, DefaultApplication1.class);
    }

    /**
     * Creates an instance of type Application1.  Modifies the underlying ontology.
     */
    public Application1 createApplication1(String name) {
		return delegate.createWrappedIndividual(name, Vocabulary.CLASS_APPLICATION1, DefaultApplication1.class);
    }

    /**
     * Gets an instance of type Application1 with the given name.  Does not modify the underlying ontology.
     * @param name the name of the OWL named individual to be retrieved.
     */
    public Application1 getApplication1(String name) {
		return delegate.getWrappedIndividual(name, Vocabulary.CLASS_APPLICATION1, DefaultApplication1.class);
    }

    /**
     * Gets all instances of Application1 from the ontology.
     */
    public Collection<? extends Application1> getAllApplication1Instances() {
		return delegate.getWrappedIndividuals(Vocabulary.CLASS_APPLICATION1, DefaultApplication1.class);
    }


    /* ***************************************************
     * Class http://www.semanticweb.org/michal/ontologies/2013/4/untitled-ontology-15#Button
     */

    {
        javaMapping.add("http://www.semanticweb.org/michal/ontologies/2013/4/untitled-ontology-15#Button", Button.class, DefaultButton.class);
    }

    /**
     * Creates an instance of type Button.  Modifies the underlying ontology.
     */
    public Button createButton(String name) {
		return delegate.createWrappedIndividual(name, Vocabulary.CLASS_BUTTON, DefaultButton.class);
    }

    /**
     * Gets an instance of type Button with the given name.  Does not modify the underlying ontology.
     * @param name the name of the OWL named individual to be retrieved.
     */
    public Button getButton(String name) {
		return delegate.getWrappedIndividual(name, Vocabulary.CLASS_BUTTON, DefaultButton.class);
    }

    /**
     * Gets all instances of Button from the ontology.
     */
    public Collection<? extends Button> getAllButtonInstances() {
		return delegate.getWrappedIndividuals(Vocabulary.CLASS_BUTTON, DefaultButton.class);
    }


    /* ***************************************************
     * Class http://www.semanticweb.org/michal/ontologies/2013/4/untitled-ontology-15#EditText
     */

    {
        javaMapping.add("http://www.semanticweb.org/michal/ontologies/2013/4/untitled-ontology-15#EditText", EditText.class, DefaultEditText.class);
    }

    /**
     * Creates an instance of type EditText.  Modifies the underlying ontology.
     */
    public EditText createEditText(String name) {
		return delegate.createWrappedIndividual(name, Vocabulary.CLASS_EDITTEXT, DefaultEditText.class);
    }

    /**
     * Gets an instance of type EditText with the given name.  Does not modify the underlying ontology.
     * @param name the name of the OWL named individual to be retrieved.
     */
    public EditText getEditText(String name) {
		return delegate.getWrappedIndividual(name, Vocabulary.CLASS_EDITTEXT, DefaultEditText.class);
    }

    /**
     * Gets all instances of EditText from the ontology.
     */
    public Collection<? extends EditText> getAllEditTextInstances() {
		return delegate.getWrappedIndividuals(Vocabulary.CLASS_EDITTEXT, DefaultEditText.class);
    }


    /* ***************************************************
     * Class http://www.semanticweb.org/michal/ontologies/2013/4/untitled-ontology-15#Listener
     */

    {
        javaMapping.add("http://www.semanticweb.org/michal/ontologies/2013/4/untitled-ontology-15#Listener", Listener.class, DefaultListener.class);
    }

    /**
     * Creates an instance of type Listener.  Modifies the underlying ontology.
     */
    public Listener createListener(String name) {
		return delegate.createWrappedIndividual(name, Vocabulary.CLASS_LISTENER, DefaultListener.class);
    }

    /**
     * Gets an instance of type Listener with the given name.  Does not modify the underlying ontology.
     * @param name the name of the OWL named individual to be retrieved.
     */
    public Listener getListener(String name) {
		return delegate.getWrappedIndividual(name, Vocabulary.CLASS_LISTENER, DefaultListener.class);
    }

    /**
     * Gets all instances of Listener from the ontology.
     */
    public Collection<? extends Listener> getAllListenerInstances() {
		return delegate.getWrappedIndividuals(Vocabulary.CLASS_LISTENER, DefaultListener.class);
    }


    /* ***************************************************
     * Class http://www.semanticweb.org/michal/ontologies/2013/4/untitled-ontology-15#MainActivity
     */

    {
        javaMapping.add("http://www.semanticweb.org/michal/ontologies/2013/4/untitled-ontology-15#MainActivity", MainActivity.class, DefaultMainActivity.class);
    }

    /**
     * Creates an instance of type MainActivity.  Modifies the underlying ontology.
     */
    public MainActivity createMainActivity(String name) {
		return delegate.createWrappedIndividual(name, Vocabulary.CLASS_MAINACTIVITY, DefaultMainActivity.class);
    }

    /**
     * Gets an instance of type MainActivity with the given name.  Does not modify the underlying ontology.
     * @param name the name of the OWL named individual to be retrieved.
     */
    public MainActivity getMainActivity(String name) {
		return delegate.getWrappedIndividual(name, Vocabulary.CLASS_MAINACTIVITY, DefaultMainActivity.class);
    }

    /**
     * Gets all instances of MainActivity from the ontology.
     */
    public Collection<? extends MainActivity> getAllMainActivityInstances() {
		return delegate.getWrappedIndividuals(Vocabulary.CLASS_MAINACTIVITY, DefaultMainActivity.class);
    }


    /* ***************************************************
     * Class http://www.semanticweb.org/michal/ontologies/2013/4/untitled-ontology-15#OnClickListener
     */

    {
        javaMapping.add("http://www.semanticweb.org/michal/ontologies/2013/4/untitled-ontology-15#OnClickListener", OnClickListener.class, DefaultOnClickListener.class);
    }

    /**
     * Creates an instance of type OnClickListener.  Modifies the underlying ontology.
     */
    public OnClickListener createOnClickListener(String name) {
		return delegate.createWrappedIndividual(name, Vocabulary.CLASS_ONCLICKLISTENER, DefaultOnClickListener.class);
    }

    /**
     * Gets an instance of type OnClickListener with the given name.  Does not modify the underlying ontology.
     * @param name the name of the OWL named individual to be retrieved.
     */
    public OnClickListener getOnClickListener(String name) {
		return delegate.getWrappedIndividual(name, Vocabulary.CLASS_ONCLICKLISTENER, DefaultOnClickListener.class);
    }

    /**
     * Gets all instances of OnClickListener from the ontology.
     */
    public Collection<? extends OnClickListener> getAllOnClickListenerInstances() {
		return delegate.getWrappedIndividuals(Vocabulary.CLASS_ONCLICKLISTENER, DefaultOnClickListener.class);
    }


    /* ***************************************************
     * Class http://www.semanticweb.org/michal/ontologies/2013/4/untitled-ontology-15#TextView
     */

    {
        javaMapping.add("http://www.semanticweb.org/michal/ontologies/2013/4/untitled-ontology-15#TextView", TextView.class, DefaultTextView.class);
    }

    /**
     * Creates an instance of type TextView.  Modifies the underlying ontology.
     */
    public TextView createTextView(String name) {
		return delegate.createWrappedIndividual(name, Vocabulary.CLASS_TEXTVIEW, DefaultTextView.class);
    }

    /**
     * Gets an instance of type TextView with the given name.  Does not modify the underlying ontology.
     * @param name the name of the OWL named individual to be retrieved.
     */
    public TextView getTextView(String name) {
		return delegate.getWrappedIndividual(name, Vocabulary.CLASS_TEXTVIEW, DefaultTextView.class);
    }

    /**
     * Gets all instances of TextView from the ontology.
     */
    public Collection<? extends TextView> getAllTextViewInstances() {
		return delegate.getWrappedIndividuals(Vocabulary.CLASS_TEXTVIEW, DefaultTextView.class);
    }


}