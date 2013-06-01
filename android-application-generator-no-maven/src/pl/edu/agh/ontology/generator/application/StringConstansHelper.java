package pl.edu.agh.ontology.generator.application;

public interface StringConstansHelper {

	public final static String MAIN_ACTIVITY_CLASS = "package com.ontology.android;\n" + "import android.view.View; import android.widget.Button; import android.widget.EditText; import android.widget.Toast;"
			+ " import com.ontology.android.R; import android.app.Activity; import android.os.Bundle;" + " import android.util.Log; import android.view.Menu; \n" +

			"public class MainActivity extends Activity { \n" +

			"private EditText etName, etSurname; \n" + "private Button btnPress;\n" +

			"    @Override\n" + "   public void onCreate(Bundle savedInstanceState) {\n" + "      super.onCreate(savedInstanceState);\n" + "     setContentView(R.layout.activity_main);\n" +

			"    etName = (EditText) findViewById(R.id.et_name);\n" + "   etSurname = (EditText) findViewById(R.id.et_surname);\n" + "  btnPress = (Button) findViewById(R.id.btn_action);\n" +

			" btnPress.setOnClickListener(new View.OnClickListener() {\n" + "    @Override\n" + "   public void onClick(View view) {\n" + "      if (etName.getText().length() != 0 && etSurname.getText().length() !=0) {\n"
			+ "         Toast toast = Toast.makeText(getApplicationContext(), etName.getText().toString() + \" \" + etSurname.getText().toString(), Toast.LENGTH_LONG);\n" + "        toast.show();\n" +

			"   }\n" + "}\n" + "});\n" + "}\n" +

			" @Override\n" + "public boolean onCreateOptionsMenu(Menu menu) {\n" + "    // Inflate the menu; this adds items to the action bar if it is present.\n" + "    getMenuInflater().inflate(R.menu.main, menu);\n" + "   return true;\n" + " }\n" +

			"}\n";

	public final static String POM = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + "<project xmlns=\"http://maven.apache.org/POM/4.0.0\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n"
			+ "	xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd\">\n" + "	<modelVersion>4.0.0</modelVersion>\n" + "	<groupId>com.ontology.android</groupId>\n" + "	<artifactId>android-ontology-app</artifactId>\n"
			+ "	<version>1.0-SNAPSHOT</version>\n" + "	<packaging>apk</packaging>\n" + "	<name>android-ontology-app</name>\n" + "\n" + "	<properties>\n" + "		<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>\n" + "		<platform.version> 4.1.1.4\n"
			+ "			</platform.version>\n" + "		<android.plugin.version>3.6.0</android.plugin.version>\n" + "	</properties>\n" + "\n" + "	<dependencies>\n" + "		<dependency>\n" + "			<groupId>com.google.android</groupId>\n" + "			<artifactId>android</artifactId>\n"
			+ "			<version>${platform.version}</version>\n" + "			<scope>provided</scope>\n" + "		</dependency>\n" + "	</dependencies>\n" + "	<build>\n" + "		<finalName>${project.artifactId}</finalName>\n" + "		<pluginManagement>\n" + "			<plugins>\n" + "				<plugin>\n"
			+ "					<groupId>com.jayway.maven.plugins.android.generation2</groupId>\n" + "					<artifactId>android-maven-plugin</artifactId>\n" + "					<version>${android.plugin.version}</version>\n" + "					<extensions>true</extensions>\n" + "				</plugin>\n"
			+ "			</plugins>\n" + "		</pluginManagement>\n" + "		<plugins>\n" + "			<plugin>\n" + "				<groupId>com.jayway.maven.plugins.android.generation2</groupId>\n" + "				<artifactId>android-maven-plugin</artifactId>\n" + "				<configuration>\n" + "					<sdk>\n"
			+ "						<platform>16</platform>\n" + "					</sdk>\n" + "									</configuration>\n" + "			</plugin>\n" + "		</plugins>\n" + "	</build>\n" + "</project>";

	public final static String ACTIVITY_MAIN = "<ScrollView xmlns:android=\"http://schemas.android.com/apk/res/android\" \n" + "        android:id=\"@+id/form\"\n" + "        android:layout_width=\"match_parent\"\n" + "        android:layout_height=\"match_parent\">\n"
			+ "        		\n" + "    <LinearLayout style=\"@style/FormContainer\"\n" + "        android:orientation=\"vertical\">\n" + "        		\n" + "        <TextView\n" + "                android:id=\"@+id/tv_description\"\n"
			+ "                android:layout_width=\"match_parent\"\n" + "                android:layout_height=\"wrap_content\"\n" + "                android:text=\"@string/tv_text\"\n" + "                android:gravity=\"center\"\n"
			+ "                android:layout_marginBottom=\"@dimen/activity_vertical_margin\"\n" + "                android:layout_marginTop=\"@dimen/activity_vertical_margin\" />\n" + "                		\n" + "        <EditText\n"
			+ "                android:id=\"@+id/et_name\"\n" + "                android:singleLine=\"true\"\n" + "                android:maxLines=\"1\"\n" + "                android:layout_width=\"match_parent\"\n" + "                android:layout_height=\"wrap_content\"\n"
			+ "                android:hint=\"@string/et_name\" />\n" + "                		\n" + "        <EditText\n" + "                android:id=\"@+id/et_surname\"\n" + "                android:singleLine=\"true\"\n" + "                android:maxLines=\"1\"\n"
			+ "                android:layout_width=\"match_parent\"\n" + "                android:layout_height=\"wrap_content\"\n" + "                android:hint=\"@string/et_surname\" />\n" + "                		\n" + "        <Button android:id=\"@+id/btn_action\"\n"
			+ "                android:layout_width=\"wrap_content\"\n" + "                android:layout_height=\"wrap_content\"\n" + "                android:layout_marginTop=\"@dimen/activity_vertical_margin\"\n" + "                android:text=\"@string/btn_action\"\n"
			+ "                android:paddingLeft=\"32dp\"\n" + "                android:paddingRight=\"32dp\"\n" + "                android:layout_gravity=\"center\" />\n" + "                		\n" + "    </LinearLayout>\n" + "    \n" + "</ScrollView>";

	public final static String RES_MENU_MAIN = "<menu xmlns:android=\"http://schemas.android.com/apk/res/android\" >\n" + "\n" + "    <item\n" + "        android:id=\"@+id/action_settings\"\n" + "        android:orderInCategory=\"100\"\n"
			+ "        android:showAsAction=\"never\"\n" + "        android:title=\"@string/action_settings\"/>\n" + "\n" + "</menu>";

	public final static String VALUES_DIMENS = "<resources>\n" + "\n" + "    <dimen name=\"activity_horizontal_margin\">16dp</dimen>\n" + "    <dimen name=\"activity_vertical_margin\">16dp</dimen>\n" + "\n" + "</resources>";

	public final static String VALUES_STYLES = "<resources>\n" + "\n" + "\n" + "  <style name=\"AppBaseTheme\" parent=\"android:Theme.Light\">\n" + " </style>\n" + "\n" + "    <style name=\"AppTheme\" parent=\"AppBaseTheme\">\n"
			+ "        <!-- All customizations that are NOT specific to a particular API-level can go here. -->\n" + "    </style>\n" + "\n" + "    <style name=\"FormContainer\">\n" + "        <item name=\"android:layout_width\">match_parent</item>\n"
			+ "        <item name=\"android:layout_height\">wrap_content</item>\n" + "        <item name=\"android:padding\">@dimen/activity_vertical_margin</item>\n" + "    </style>\n" + "\n" + "</resources>";

	public final static String VALUES_SW600DP_DIMENS = "<resources></resources>";

	public final static String VALUES_SW720DP_DIMENS = "<resources><dimen name=\"activity_horizontal_margin\">128dp</dimen></resources>";

	public final static String VALUES_V11_STYLES = "<resources><style name=\"AppBaseTheme\" parent=\"android:Theme.Holo.Light\"></style></resources>";

	public final static String VALUES_V14_STYLES = "<resources><style name=\"AppBaseTheme\" parent=\"android:Theme.Holo.Light.DarkActionBar\"></style></resources>";

	public final static String ANDROID_MANIFEST = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" + "<manifest xmlns:android=\"http://schemas.android.com/apk/res/android\"\n" + "    package=\"com.ontology.android\"\n" + "    android:versionCode=\"1\"\n"
			+ "    android:versionName=\"1.0-SNAPSHOT\" >\n" + "\n" + "   <uses-sdk\n" + "        android:minSdkVersion=\"8\"\n" + "        android:targetSdkVersion=\"16\" />\n" + "\n" + "    <application\n" + "        android:allowBackup=\"true\"\n"
			+ "        android:icon=\"@drawable/ic_launcher\"\n" + "        android:label=\"@string/app_name\"\n" + "        android:theme=\"@style/AppTheme\">\n" + "        <activity android:name=\".MainActivity\" >\n" + "            <intent-filter>\n"
			+ "                <action android:name=\"android.intent.action.MAIN\" />\n" + "                <category android:name=\"android.intent.category.LAUNCHER\" />\n" + "            </intent-filter>\n" + "        </activity>\n" + "    </application>\n" + "\n"
			+ "</manifest>";

}
