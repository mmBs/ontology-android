package pl.edu.agh.ontology.generator.application.api.exception;

public class PropertiesFileNotFoundException extends Exception {

	public PropertiesFileNotFoundException() {
		super();
	}

	public PropertiesFileNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public PropertiesFileNotFoundException(String message) {
		super(message);
	}

	public PropertiesFileNotFoundException(Throwable cause) {
		super(cause);
	}

}
