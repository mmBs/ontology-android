package pl.edu.agh.ontology.generator.application.domain.impl;

import pl.edu.agh.ontology.generator.application.domain.*;

import java.util.Collection;

import org.protege.owl.codegeneration.WrappedIndividual;
import org.protege.owl.codegeneration.impl.WrappedIndividualImpl;

import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLOntology;


/**
 * Generated by Protege (http://protege.stanford.edu).<br>
 * Source Class: DefaultApplication <br>
 * @version generated on Thu May 30 23:34:55 CEST 2013 by skywalker
 */
public class DefaultApplication extends WrappedIndividualImpl implements Application {

    public DefaultApplication(OWLOntology ontology, IRI iri) {
        super(ontology, iri);
    }





    /* ***************************************************
     * Object Property http://www.agh.edu.pl/eaiib/android/ontology#hasMainActivity
     */
     
    public Collection<? extends MainActivity> getHasMainActivity() {
        return getDelegate().getPropertyValues(getOwlIndividual(),
                                               Vocabulary.OBJECT_PROPERTY_HASMAINACTIVITY,
                                               DefaultMainActivity.class);
    }

    public boolean hasHasMainActivity() {
	   return !getHasMainActivity().isEmpty();
    }

    public void addHasMainActivity(MainActivity newHasMainActivity) {
        getDelegate().addPropertyValue(getOwlIndividual(),
                                       Vocabulary.OBJECT_PROPERTY_HASMAINACTIVITY,
                                       newHasMainActivity);
    }

    public void removeHasMainActivity(MainActivity oldHasMainActivity) {
        getDelegate().removePropertyValue(getOwlIndividual(),
                                          Vocabulary.OBJECT_PROPERTY_HASMAINACTIVITY,
                                          oldHasMainActivity);
    }


}
