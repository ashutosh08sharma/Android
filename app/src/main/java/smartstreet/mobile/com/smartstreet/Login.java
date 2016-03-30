package smartstreet.mobile.com.smartstreet;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.facebook.FacebookSdk;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final DatabaseHandler databaseHandler = new DatabaseHandler(this); // instance to handle db
        final EditText usernameText = (EditText) findViewById(R.id.idText);
        final EditText passwordText =(EditText) findViewById(R.id.pwdText);
        Button  signBttn = (Button) findViewById(R.id.loginBttn);
        signBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userName = usernameText.getText().toString();
                String password = passwordText.getText().toString();

                String  storedPassword = databaseHandler.getPassword(userName);

                if(password.equals(storedPassword))
                {
                    Toast.makeText(getApplicationContext(), "Welcome", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK | intent.FLAG_ACTIVITY_CLEAR_TASK);
                    //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }

                else
                {
                    Toast.makeText(getApplicationContext(), "User Name or Password does not match", Toast.LENGTH_LONG).show();
                }
            }
        });




    }


            private void viewMain() {

                Intent loginIntent = new Intent(this, MainActivity.class);
                startActivity(loginIntent);
            }

            private void registerView(){

                Intent registerIntent = new Intent(this, Register.class);
                startActivity(registerIntent);

            }


            public void onClick(View view) {
                switch (view.getId()) {
                case R.id.loginBttn:
                viewMain();
                break;

                 case R.id.regbttn:
                    registerView();
                    break;

                    case R.id.skipText:
                        viewMain();
                        break;


        }
    }
}