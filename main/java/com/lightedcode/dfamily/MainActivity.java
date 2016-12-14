package com.lightedcode.dfamily;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.TextView;

import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Toolbar tool;
    ArrayList<Contact> selectionlist = new ArrayList<>();
    ArrayList<Data> cont1 = new ArrayList<>();
    ArrayList<Data> cont2 = new ArrayList<>();
    int val = 0;
    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);
        final Thread load = new Thread(){
            public void run(){
                try{
                    sleep(2500);
                }catch(InterruptedException es){
                    es.printStackTrace();

                }finally {
                    Intent openlogin = new Intent(MainActivity.this, Login.class);
                    startActivity(openlogin);

                }

            }
        };load.start();


    }

      @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
      int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /**    if (id == R.id.action_settings) {
            return true;
        }
        if (item.getItemId()==R.id.delete){
            HomeAdapter contacts = (HomeAdapter) new Homepage().adapter;
            contacts.updateAdapter(new Homepage().cont1);
            cleaActionMode();
        }**/
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed(){
       super.onBackPressed();
        }
    }

