package pl.edu.agh.eaiib.androidonotology.impl;

import pl.edu.agh.eaiib.androidonotology.*;

import java.util.Collection;

import org.protege.owl.codegeneration.WrappedIndividual;
import org.protege.owl.codegeneration.impl.WrappedIndividualImpl;

import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLOntology;


/**
 * Generated by Protege (http://protege.stanford.edu).<br>
 * Source Class: DefaultActivity <br>
 * @version generated on Tue May 28 22:55:10 CEST 2013 by Michal
 */
public class DefaultActivity extends WrappedIndividualImpl implements Activity {

    public DefaultActivity(OWLOntology ontology, IRI iri) {
        super(ontology, iri);
    }





    /* ***************************************************
     * Object Property http://www.agh.edu.pl/eaiib/android/ontology#hasMainActivity
     */
     
    public Collection<? extends Activity> getHasMainActivity() {
        return getDelegate().getPropertyValues(getOwlIndividual(),
                                               Vocabulary.OBJECT_PROPERTY_HASMAINACTIVITY,
                                               DefaultActivity.class);
    }

    public boolean hasHasMainActivity() {
	   return !getHasMainActivity().isEmpty();
    }

    public void addHasMainActivity(Activity newHasMainActivity) {
        getDelegate().addPropertyValue(getOwlIndividual(),
                                       Vocabulary.OBJECT_PROPERTY_HASMAINACTIVITY,
                                       newHasMainActivity);
    }

    public void removeHasMainActivity(Activity oldHasMainActivity) {
        getDelegate().removePropertyValue(getOwlIndividual(),
                                          Vocabulary.OBJECT_PROPERTY_HASMAINACTIVITY,
                                          oldHasMainActivity);
    }


}
