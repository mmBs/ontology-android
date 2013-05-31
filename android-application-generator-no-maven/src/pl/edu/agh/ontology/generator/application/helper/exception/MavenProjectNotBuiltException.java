package pl.edu.agh.ontology.generator.application.helper.exception;

public class MavenProjectNotBuiltException extends MavenProjectException {

	public MavenProjectNotBuiltException() {
		super();
	}

	public MavenProjectNotBuiltException(String message, Throwable cause) {
		super(message, cause);
	}

	public MavenProjectNotBuiltException(String message) {
		super(message);
	}

	public MavenProjectNotBuiltException(Throwable cause) {
		super(cause);
	}

}
