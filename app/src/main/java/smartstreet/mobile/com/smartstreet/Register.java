package smartstreet.mobile.com.smartstreet;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/*
 registration of user
 */

public class Register extends AppCompatActivity {

    DatabaseHandler databaseHandler; // instance of DatabaseHandler

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
          databaseHandler= new DatabaseHandler(this);

        final EditText textUserName=(EditText)findViewById(R.id.nameText);
        final EditText textPassword=(EditText)findViewById(R.id.passwordText);
        final EditText textEmailid=(EditText)findViewById(R.id.emailText);
        final EditText textAge=(EditText)findViewById(R.id.ageText);
        final EditText phoneText =(EditText) findViewById(R.id.phoneText);
        Button submitBttn = (Button) findViewById(R.id.registerButton);
        submitBttn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                /*
                    setting value from EditText to Variable
                 */
                String Name = textUserName.getText().toString();
                String Password = textPassword.getText().toString();
                String Email = textEmailid.getText().toString();
                String Age = textAge.getText().toString();
                String PhoneNo = phoneText.getText().toString();

                // validation all the fields are required
                if (Name.equals("") || Email.equals("") || Password.equals("")) {
                    Toast.makeText(getApplicationContext(), "Field Required", Toast.LENGTH_LONG).show(); // displaying message
                    return;
                } else {

                    databaseHandler.insertUser(Name, Password,Email, Age, PhoneNo);
                    Toast.makeText(getApplicationContext(), "Account Successfully Created ", Toast.LENGTH_LONG).show();


                }
                Intent intent = new Intent(getApplicationContext(), Login.class);
                // To clear android stack when user goes back to register/login page
                intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK | intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();


            }
        });


    }

}
