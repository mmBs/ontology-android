package com.ontology.android;
import android.view.View; import android.widget.Button; import android.widget.EditText; import android.widget.Toast; import com.ontology.android.R; import android.app.Activity; import android.os.Bundle; import android.util.Log; import android.view.Menu; 
public class MainActivity extends Activity { 
private EditText etName, etSurname; 
private Button btnPress;
    @Override
   public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
     setContentView(R.layout.activity_main);
    etName = (EditText) findViewById(R.id.et_name);
   etSurname = (EditText) findViewById(R.id.et_surname);
  btnPress = (Button) findViewById(R.id.btn_action);
 btnPress.setOnClickListener(new View.OnClickListener() {
    @Override
   public void onClick(View view) {
      if (etName.getText().length() != 0 && etSurname.getText().length() !=0) {
         Toast toast = Toast.makeText(getApplicationContext(), etName.getText().toString() + " " + etSurname.getText().toString(), Toast.LENGTH_LONG);
        toast.show();
   }
}
});
}
 @Override
public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
   return true;
 }
}
