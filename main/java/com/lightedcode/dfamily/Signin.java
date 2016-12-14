package com.lightedcode.dfamily;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by joebuntu on 11/16/16.
 */

public class Signin extends Activity implements View.OnClickListener{
    TextView log; Button signin, login;
   ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;
    EditText fulname, username, password, conf, gender, phone, email;

    DatabaseReference mRootref, fname;
            ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin_activity);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        signin = (Button) findViewById(R.id.create);
        login = (Button)findViewById(R.id.logtv);

         fulname = (EditText) findViewById(R.id.fullname);
         username = (EditText) findViewById(R.id.unameid);
         password = (EditText) findViewById(R.id.passid2);
         conf = (EditText) findViewById(R.id.confpass3);
         gender = (EditText) findViewById(R.id.genderid);
         phone = (EditText) findViewById(R.id.phoneid);
         email = (EditText) findViewById(R.id.emailid);

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


                    String namestr = fulname.getText().toString();
                    String unamestr = username.getText().toString();
                    String passestr = password.getText().toString();
                    String constr = conf.getText().toString();
                    String phonestr = phone.getText().toString();
                    String emailstr = email.getText().toString();

                    // insert the details in Database
                    if (TextUtils.isEmpty(namestr)){
                        Toast.makeText(this,"Please enter your name", Toast.LENGTH_SHORT).show();
                        return;
                    } if (TextUtils.isEmpty(unamestr)) {
                        Toast.makeText(this, "Please enter your Family Name", Toast.LENGTH_SHORT).show();
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
                        progressDialog.show();
                        firebaseAuth.createUserWithEmailAndPassword(emailstr, passestr)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                      progressDialog.dismiss();
                                        if (task.isSuccessful()) {
                                            mRootref = FirebaseDatabase.getInstance().getReference();
                                            String unamestr = username.getText().toString();
                                            fname = mRootref.child(unamestr);

                                            

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

