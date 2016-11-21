package com.lightedcode.dfamily;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by joebuntu on 11/16/16.
 */

public class Homepage extends AppCompatActivity{
    Toolbar tool;
    boolean in_action_mode = false;
    RecyclerView recyclerView;
    TextView countertv;
    HomeAdapter adapter;
    RecyclerView.LayoutManager lm;
    int[] photo = new int[]{R.raw.album1, R.raw.album2, R.raw.album3, R.raw.album4, R.raw.album5, R.raw.album6, R.raw.album7, R.raw.album8, R.raw.album9,
            R.raw.album10, R.raw.album11, R.raw.cover};

    ArrayList<Contact> selectionlist = new ArrayList<>();
    ArrayList<Contact> cont = new ArrayList<>();
    ArrayList<Data> cont1 = new ArrayList<>();
    int val = 0;

    @Override
    protected void onCreate(Bundle bb) {
        super.onCreate(bb);
        setContentView(R.layout.home_activity);
        tool = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(tool);
        initCollapsingToolbar();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        lm = new GridLayoutManager(this, 2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(lm);
        countertv = (TextView) findViewById(R.id.tooltext);
        countertv.setVisibility(View.GONE);


        String[] name;
        name = getResources().getStringArray(R.array.name);
        // This is to save the img_id, name, and email in the form of  an object of the contact class
        int i = 0;
        for (String Name : name) {
            Data stuff = new Data(photo[i], Name);
            cont1.add(stuff);
            i++;
        }
        adapter = new HomeAdapter(cont1, Homepage.this);
        recyclerView.setAdapter(adapter);

    }
    public void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.toool);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(getString(R.string.app_name));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }

}


