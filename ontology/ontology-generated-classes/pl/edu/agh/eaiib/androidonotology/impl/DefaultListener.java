package pl.edu.agh.eaiib.androidonotology.impl;

import pl.edu.agh.eaiib.androidonotology.*;

import java.util.Collection;

import org.protege.owl.codegeneration.WrappedIndividual;
import org.protege.owl.codegeneration.impl.WrappedIndividualImpl;

import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLOntology;


/**
 * Generated by Protege (http://protege.stanford.edu).<br>
 * Source Class: DefaultListener <br>
 * @version generated on Tue May 28 22:55:10 CEST 2013 by Michal
 */
public class DefaultListener extends WrappedIndividualImpl implements Listener {

    public DefaultListener(OWLOntology ontology, IRI iri) {
        super(ontology, iri);
    }





    /* ***************************************************
     * Object Property http://www.agh.edu.pl/eaiib/android/ontology#hasListeners
     */
     
    public Collection<? extends Listener> getHasListeners() {
        return getDelegate().getPropertyValues(getOwlIndividual(),
                                               Vocabulary.OBJECT_PROPERTY_HASLISTENERS,
                                               DefaultListener.class);
    }

    public boolean hasHasListeners() {
	   return !getHasListeners().isEmpty();
    }

    public void addHasListeners(Listener newHasListeners) {
        getDelegate().addPropertyValue(getOwlIndividual(),
                                       Vocabulary.OBJECT_PROPERTY_HASLISTENERS,
                                       newHasListeners);
    }

    public void removeHasListeners(Listener oldHasListeners) {
        getDelegate().removePropertyValue(getOwlIndividual(),
                                          Vocabulary.OBJECT_PROPERTY_HASLISTENERS,
                                          oldHasListeners);
    }


}
