package pl.edu.agh.ontology.generator.application.writer;

import java.util.HashMap;
import java.util.Map;

public class AndroidStringResources {

	private Map<String, String> strings;

	public AndroidStringResources() {
		strings = new HashMap<String, String>();
	}

	public void addString(String key, String value) {
		if (key != null && value != null) {
			strings.put(key, value);
		}
	}

	public String generateStringsXML() {
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" + "<resources>\n" + "\n");
		for (String key : strings.keySet()) {
			sb.append("<string name=\"" + key + "\">" + strings.get(key) + "</string>\n");
		}
		sb.append("\n" + "</resources>");
		return sb.toString();
	}

}
