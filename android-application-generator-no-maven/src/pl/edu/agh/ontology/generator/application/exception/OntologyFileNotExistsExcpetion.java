package pl.edu.agh.ontology.generator.application.exception;

import java.io.File;

public class OntologyFileNotExistsExcpetion extends ApplicationGeneratorException {

	private File f;

	public OntologyFileNotExistsExcpetion(File f) {
		super();
		this.f = f;
	}

	public OntologyFileNotExistsExcpetion(File f, String message, Throwable cause) {
		super(message, cause);
		this.f = f;
	}

	public OntologyFileNotExistsExcpetion(File f, String message) {
		super(message);
		this.f = f;
	}

	public OntologyFileNotExistsExcpetion(File f, Throwable cause) {
		super(cause);
		this.f = f;
	}

	@Override
	public String toString() {
		String s = super.toString();
		s = s + "\n File: " + f.getAbsolutePath();
		return s;
	}

}
