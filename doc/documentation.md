ontology-android
================

ontology-android
================

####Application

---

Generates AndroidManifest.xml:

- set android:allowBackup
- set icon (android:icon)
- set label (android:label=”@string/address_to_string”
- set theme android:theme
- set intent-filter

#####Generates also:

- res folder which contains:
 - drawable-mdpi, hdpi, xhdpi, xxhdpi folders
 - layout folder with hardcoded name activity_main.xml
 - menu folder
 - values folder where we generate:
 
     - dimens.xml
     - styles.xml
     - strings.xml
 - values-sw600dp folder (includes dimens.xml)
 - values-sw720dp folder (includes dimens.xml)
 - values-v11 folder (includes style.xml)
 - values-v14 folder (includes style.xml)

- src/main/java/(maven) folder

###MainActivity

---

generates:

    <ScrollView> + close tags
    <LinerarLayout> + close tags
    onCreateOptionsMenu(Menu menu) method

###TextView

---

generates

- string tv_text "default text"


###EditText

---

generates:

- string et_name i et_surname

###Button

---

generates

- string btn_action