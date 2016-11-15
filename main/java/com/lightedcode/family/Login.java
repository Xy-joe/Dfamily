package com.lightedcode.family;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by JOE on 11/15/2016.
 */
public class Login extends Activity {
    Database helper = new Database(this);

    @Override
    protected void onCreate(Bundle login) {
        super.onCreate(login);
        setContentView(R.layout.login_activity);
    }

    public void onButtonClicked(View v) {
        EditText uusenam = (EditText) findViewById(R.id.use);
        EditText userpass = (EditText) findViewById(R.id.pass);

        if (v.getId() == R.id.log) {
            String uname = uusenam.getText().toString();
            String pas = userpass.getText().toString();

            String password = helper.searchPass(uname);
            if (pas.equals(password)) {
                Intent i = new Intent(Login.this, Homepage.class);
                i.putExtra("Username", uname);
                startActivity(i);
            } else {
                Toast temp = Toast.makeText(Login.this, "Username and password don't Match", Toast.LENGTH_LONG);
                temp.show();
            }


        }
        if (v.getId() == R.id.sign) {
            Intent i = new Intent(Login.this, Signin.class);
            startActivity(i);
        }
    }
}