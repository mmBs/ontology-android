package pl.edu.agh.ontology.generator.application.helper;

import java.io.IOException;

import pl.edu.agh.ontology.generator.application.helper.exception.MavenProjectException;
import pl.edu.agh.ontology.generator.application.helper.exception.MavenProjectNotBuiltException;

public class MavenProjectBuilder {

	// public static void main(String[] args) {
	// File file = new File("android-ontology-app/pom.xml");
	// try {
	// buildProject(file.getAbsolutePath());
	// } catch (MavenProjectException e) {
	// e.printStackTrace();
	// }
	// }

	public static void buildProject(String pomPath) throws MavenProjectException {

		System.out.println(pomPath);

		Runtime rt = Runtime.getRuntime();
		try {
			Process pr = rt.exec("mvn -f \"" + pomPath + "\" clean package");

			Thread inputStdStreamThread = new Thread(new StreamGobbler(pr.getInputStream()));
			Thread errorStdStreamThread = new Thread(new StreamGobbler(pr.getErrorStream()));
			inputStdStreamThread.start();
			errorStdStreamThread.start();

			int exitCode = pr.waitFor();
			inputStdStreamThread.join();
			errorStdStreamThread.join();

			System.out.println("Exit CODE = " + exitCode);
			if (exitCode != 0) {
				throw new MavenProjectNotBuiltException();
			}

		} catch (IOException e) {
			System.out.println("MavenProjectBuilder.IOException");
			throw new MavenProjectException(e);
		} catch (InterruptedException e) {
			System.out.println("MavenProjectBuilder.InterruptedException");
			throw new MavenProjectException(e);
		}

	}
}
