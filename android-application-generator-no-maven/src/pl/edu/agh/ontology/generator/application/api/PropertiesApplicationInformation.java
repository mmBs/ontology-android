package pl.edu.agh.ontology.generator.application.api;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesApplicationInformation implements ApplicationInformation {

	Properties properties;

	public PropertiesApplicationInformation() {
		properties = new Properties();
		String defaultPropertiesFilePath = "appProps.properties";
		File file = new File(defaultPropertiesFilePath);
		InputStream is = null;
		try {
			is = new FileInputStream(file);
			properties.load(is);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public PropertiesApplicationInformation(String applicationInformationFilePath) {
		properties = new Properties();
		File file = new File(applicationInformationFilePath);
		InputStream is = null;
		try {
			is = new FileInputStream(file);
			properties.load(is);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Object getValue(String key) {
		return properties.getProperty(key);
	}

}
