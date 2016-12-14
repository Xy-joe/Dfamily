package com.lightedcode.dfamily;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.kosalgeek.android.photoutil.CameraPhoto;
import com.kosalgeek.android.photoutil.GalleryPhoto;
import com.kosalgeek.android.photoutil.ImageLoader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by joebuntu on 11/20/16.
 */

public class UserActivity extends AppCompatActivity implements View.OnClickListener{
    Toolbar tol;
    TextView name, email, phone, profilename;
CardView view1, view2;
    final int camera_request = 988885;  GalleryPhoto galleryPhoto;
    final int gallery_request = 2453;
    CameraPhoto cameraphoto;
    final int Gallery_request = 73737;
    RecyclerView recycler;
    RecyclerView.LayoutManager layoutManager;
    ProfileAdapter adapter;
    ArrayList<ProfileContent> result = new ArrayList();
   ImageView cover, goption, options, coverdp, dp;
    EditText stat;Button share, cancel;
    int[] img1 = {R.drawable.ic_list };
    int[] img2 = { R.raw.naru};
    int[] img3 = { R.drawable.ic_crop_original_black_24dp};
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userlayout);
        tol  = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(tol);
        initCollapsingToolbar();


        cameraphoto = new CameraPhoto(UserActivity.this);
        galleryPhoto = new GalleryPhoto(UserActivity.this);
        final  LinearLayout lay1 = (LinearLayout)findViewById(R.id.profile_layout);
        final LinearLayout lay2 = (LinearLayout) findViewById(R.id.about_view);
        lay2.setVisibility(View.GONE);
        Button probtn = (Button) findViewById(R.id.timebtn);
        Button abbtn = (Button) findViewById(R.id.aboutbtn);
        probtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lay2.setVisibility(View.GONE);
                lay1.setVisibility(View.VISIBLE);
            }
        });
        abbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lay2.setVisibility(View.VISIBLE);
                lay1.setVisibility(View.GONE);
            }
        });
        name = (TextView)findViewById(R.id.name);
        email = (TextView)findViewById(R.id.email);
        phone = (TextView)findViewById(R.id.ppho);
        share = (Button)findViewById(R.id.sharebutton);
        cancel = (Button)findViewById(R.id.cancelbutton);
        goption = (ImageView)findViewById(R.id.goption);
        options = (ImageView)findViewById(R.id.optio);
        stat = (EditText)findViewById(R.id.post);
        cover = (ImageView)findViewById(R.id.upload);

        // Setting up the Adapter for the first view
        TextView title = (TextView)findViewById(R.id.msgtitle);
        String msg = title.getText().toString();String message = msg;
        recycler = (RecyclerView) findViewById(R.id.recycler_view_for_profile);
        layoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(layoutManager);
        recycler.setHasFixedSize(true);
        String post = stat.getText().toString();String po = post;


         ProfileContent profileContent = new ProfileContent(message,po );
           result.add(profileContent);
        adapter = new ProfileAdapter(UserActivity.this, result);
        recycler.setAdapter(adapter);
        // Adapter for the second view
        String eemail = email.getText().toString();String ema = eemail;
        String pphone = phone.getText().toString();String phone = pphone;
        String nameid = name.getText().toString();String na = nameid;
        int img1 = R.drawable.common_full_open_on_phone;
        int img2 = R.drawable.ic_more_options;
        HomeConent homeConent = new HomeConent(na, ema, phone,img1,img2);
        view1 = (CardView)findViewById(R.id.view1);
        view2 = (CardView)findViewById(R.id.card_view2);









    }

    public void showpicturePopup(View view){
        PopupMenu popup = new PopupMenu(UserActivity.this, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.picture_menu, popup.getMenu());
        popup.setOnMenuItemClickListener(new PictureItemClickListener());
        popup.show();
    }

    @Override
    public void onClick(View view) {
        if (view == goption){
            showpicturePopup(view);
        }
        if (view == coverdp){
            showpicturePopup(view);
        }
        if (view == dp){
            showpicturePopup(view);
        }
    }

    class  PictureItemClickListener implements PopupMenu.OnMenuItemClickListener{
        public PictureItemClickListener(){

        }
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()){
                case R.id.camera:
                    try{
                        startActivityForResult(cameraphoto.takePhotoIntent(), camera_request);
                        cameraphoto.addToGallery();
                    }catch (IOException e){
                        Toast.makeText(UserActivity.this," Cannot access your camera", Toast.LENGTH_SHORT).show();
                    }
                    return true;
                case R.id.gallery:
                    try{
                        startActivityForResult(galleryPhoto.openGalleryIntent(), gallery_request);
                    }catch (Exception e){
                        Toast.makeText(UserActivity.this," Cannot access your media", Toast.LENGTH_SHORT).show();
                    }
                    return true;
                default:
            }
            return false;

        }
    }

        /**private void setupDrawerContent(NavigationView navigationView){
    navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){
        @Override
        public boolean onNavigationItemSelected(MenuItem menuItem){
            selectedDrawerItem(menuItem);
            return true;
        }
    });
}
    public void selectedDrawerItem(MenuItem menuItem){
        Fragment fragment = null;
        Class fragmentClass;
        switch (menuItem.getItemId()){
            case R.id.import_op:
             fragmentClass = FirstFragment.class;
                break;
            case R.id.gallery:
                fragmentClass = SecondFragment.class;
                break;
            default:
                fragmentClass = UserActivity.class;

        }
        try{
            fragment = (Fragment)fragmentClass.newInstance();

        }catch (Exception e){
            e.printStackTrace();
        }

        // Insert the fragments by replacing any existing fragments
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.main, fragment).commit();

        //HighLight the selected item that has been done by navigation View
        menuItem.setChecked(true);
        // Set action Bar title
        setTitle(menuItem.getTitle());
        // Close the navigation Drawer
        drawerLayout.closeDrawers();
    }

    public void galleryClicked(){

        try {
            startActivityForResult(galleryPhoto.openGalleryIntent(), Gallery_request);
        }catch (Exception e){
            Toast.makeText(UserActivity.this," Cannot access your media", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

    }*/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       if (requestCode== Gallery_request){
            Uri uri = data.getData();
            galleryPhoto.setPhotoUri(uri);
            String photopat = galleryPhoto.getPath();
            try{
                Bitmap bitmap = ImageLoader.init().from(photopat).requestSize(512,512).getBitmap();
                cover.setImageBitmap(bitmap);
            }catch (FileNotFoundException e){
                Toast.makeText(UserActivity.this,"Failed",Toast.LENGTH_LONG).show();
            }
        }
    }
    public void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collaps);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.scroll);
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
                    collapsingToolbar.setTitle(getString(R.string.Homepage));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close


        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
