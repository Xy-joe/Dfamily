package com.lightedcode.dfamily;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by joebuntu on 11/16/16.
 */

public class Signin extends Activity implements View.OnClickListener{
    TextView log; Button signin, login;
   ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        signin = (Button) findViewById(R.id.create);
        login = (Button)findViewById(R.id.logtv);
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        signin.setOnClickListener(this);
        login.setOnClickListener(this);



        if (firebaseAuth.getCurrentUser() != null){
            finish();
            startActivity(new Intent(getApplicationContext(),Homepage.class));

        }

    }
            public void Signinto() {
                try {


                    EditText fulname = (EditText) findViewById(R.id.fullname);
                    EditText username = (EditText) findViewById(R.id.unameid);
                    EditText passsword = (EditText) findViewById(R.id.passid2);
                    EditText conf = (EditText) findViewById(R.id.confpass3);
                    EditText gender = (EditText) findViewById(R.id.genderid);
                    EditText phone = (EditText) findViewById(R.id.phoneid);
                    EditText email = (EditText) findViewById(R.id.emailid);

                    String namestr = fulname.getText().toString();
                    String unamestr = username.getText().toString();
                    String passestr = passsword.getText().toString();
                    String constr = conf.getText().toString();
                    String phonestr = phone.getText().toString();
                    String emailstr = email.getText().toString();

                    // insert the details in Database
                    if (TextUtils.isEmpty(namestr)){
                        Toast.makeText(this,"Please enter your name", Toast.LENGTH_SHORT).show();
                        return;
                    } if (TextUtils.isEmpty(unamestr)) {
                        Toast.makeText(this, "Please enter your Username", Toast.LENGTH_SHORT).show();
                        return;
                    }  if (TextUtils.isEmpty(passestr)){
                            Toast.makeText(this,"Please enter your password", Toast.LENGTH_SHORT).show();
                            return;
                        } if
                         (TextUtils.isEmpty(constr)) {
                        Toast.makeText(this, "Please confirm your Password", Toast.LENGTH_SHORT).show();
                        return;
                    }if (TextUtils.isEmpty(phonestr)){
                        Toast.makeText(this,"Please enter your phone number", Toast.LENGTH_SHORT).show();
                        return;
                    }if
                     (TextUtils.isEmpty(emailstr)) {
                        Toast.makeText(this, "Please enter your email", Toast.LENGTH_SHORT).show();
                    }
                    if (!constr.equals(passestr)) {
                        Toast.makeText(this, "Password do not match", Toast.LENGTH_SHORT).show();
                        return;
                    }else {

                        //Toast.makeText(Signin.this, "Account created Succesfully", Toast.LENGTH_SHORT).show();
                        //startActivity(new Intent(Signin.this, Homepage.class));
                       progressDialog.setMessage("Creating Account ......");
                        firebaseAuth.createUserWithEmailAndPassword(emailstr, passestr)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                      progressDialog.dismiss();
                                        if (task.isSuccessful()) {
                                            Toast.makeText(Signin.this, "Account created Succesfully", Toast.LENGTH_SHORT).show();
                                       startActivity(new Intent(Signin.this, Login.class));
                                        } else {
                                            Toast.makeText(Signin.this, "Error!!! Could not create connection", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });

                        }

                } catch (Exception e) {
                    e.printStackTrace();

                }
            }


    @Override
    public void onClick(View view) {
        if (view == signin){
            Signinto();
        }if (view == login){
            Intent openlogin = new Intent(Signin.this, Login.class);
            startActivity(openlogin);

        }
    }
}

