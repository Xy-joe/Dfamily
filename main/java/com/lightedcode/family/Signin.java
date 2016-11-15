package com.lightedcode.family;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by JOE on 11/15/2016.
 */
public class Signin extends Activity {
    TextView log; Button signin;

    Database helper = new Database(this);
    @Override
    protected void onCreate(Bundle user){
        super.onCreate(user);
        setContentView(R.layout.signup_activity);
        log = (TextView) findViewById(R.id.logtv);
        signin = (Button) findViewById(R.id.sign1);
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent login = new Intent("com.practice.joe.practiceapp.LOGINACTIVITY");
                startActivity(login);
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.equals(signin)){
                    EditText fulname = (EditText)findViewById(R.id.fullname);
                    EditText username = (EditText)findViewById(R.id.unameid);
                    EditText passsword = (EditText)findViewById(R.id.passid2);
                    EditText conf = (EditText)findViewById(R.id.confpass3);
                    EditText gender= (EditText)findViewById(R.id.genderid);
                    EditText phone = (EditText)findViewById(R.id.phoneid);
                    EditText email = (EditText)findViewById(R.id.emailid);

                    String namestr = fulname.getText().toString();
                    String unamestr = username.getText().toString();
                    String passestr = passsword.getText().toString();
                    String constr = conf.getText().toString();
                    String genestr = gender.getText().toString();
                    String phonestr = phone.getText().toString();
                    String emailstr = email.getText().toString();


                    if (!passestr.equals(constr)){
                        //Display message
                        Toast pas1 = Toast.makeText(Signin.this, "Password are not the same", Toast.LENGTH_SHORT);
                        pas1.show();
                    }else{
                        // insert the details in Database
                        DataInfo di = new DataInfo();
                        di.setEmail(emailstr);
                        di.setGender(genestr);
                        di.setName(namestr);
                        di.setPassword(passestr);
                        di.setPhonee(phonestr);
                        di.setUsername(unamestr);

                        helper.insertContact(di);

                    }

            }}
        });
    }
}
