package pl.edu.agh.eaiib.androidonotology;

import java.util.Collection;

import org.protege.owl.codegeneration.WrappedIndividual;

import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntology;

/**
 * 
 * <p>
 * Generated by Protege (http://protege.stanford.edu). <br>
 * Source Class: Button <br>
 * @version generated on Sun May 19 20:09:37 CEST 2013 by Michal
 */

public interface Button extends TextView {

    /* ***************************************************
     * Property http://www.semanticweb.org/michal/ontologies/2013/4/untitled-ontology-15#hasStructure
     */
     
    /**
     * Gets all property values for the hasStructure property.<p>
     * 
     * @returns a collection of values for the hasStructure property.
     */
    Collection<? extends TextView> getHasStructure();

    /**
     * Checks if the class has a hasStructure property value.<p>
     * 
     * @return true if there is a hasStructure property value.
     */
    boolean hasHasStructure();

    /**
     * Adds a hasStructure property value.<p>
     * 
     * @param newHasStructure the hasStructure property value to be added
     */
    void addHasStructure(TextView newHasStructure);

    /**
     * Removes a hasStructure property value.<p>
     * 
     * @param oldHasStructure the hasStructure property value to be removed.
     */
    void removeHasStructure(TextView oldHasStructure);


    /* ***************************************************
     * Common interfaces
     */

    OWLNamedIndividual getOwlIndividual();

    OWLOntology getOwlOntology();

    void delete();

}
