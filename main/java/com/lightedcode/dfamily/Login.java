package com.lightedcode.dfamily;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class Login extends Activity implements View.OnClickListener{
    Button log, sign;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.login_activity);
      //  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setActionBar(toolbar);
        log = (Button) findViewById(R.id.log);
        sign = (Button) findViewById(R.id.sign);
        progressDialog= new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() != null){
            finish();
            startActivity(new Intent(getApplicationContext(),Homepage.class));

        }
        log.setOnClickListener(this);
        sign.setOnClickListener(this);


    }

            public void Logon(){

                EditText uusenam = (EditText) findViewById(R.id.use);
                EditText userpass = (EditText) findViewById(R.id.pass);

                String email = uusenam.getText().toString();
                String   pass= userpass.getText().toString();


                if (TextUtils.isEmpty(email)){
                    Toast.makeText(this,"Please enter your email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(pass)) {
                    Toast.makeText(this, "Please enter your email", Toast.LENGTH_SHORT).show();
                  return;
                }

                    progressDialog.setMessage("Logging in.....");
                    progressDialog.show();
                    firebaseAuth.signInWithEmailAndPassword(email, pass)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressDialog.dismiss();
                                    if (task.isSuccessful()) {

                                        finish();
                                        startActivity(new Intent(getApplicationContext(), Homepage.class));

                                    } else {
                                        Toast.makeText(Login.this, "Could not establish connection please try again", Toast.LENGTH_LONG).show();

                                    }
                                }

                                });
            }
    @Override
    public void onBackPressed() {


    }

    @Override
    public void onClick(View view) {
                    if (view == log) {
                        Logon();
                    }
                        if (view == sign){
                            startActivity(new Intent(Login.this, Signin.class));
                        }else{
                            Toast.makeText(this, "Somthing went wrong",Toast.LENGTH_SHORT).show();
                        }


                }
                }