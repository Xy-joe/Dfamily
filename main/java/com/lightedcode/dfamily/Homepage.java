package com.lightedcode.dfamily;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.net.Uri;
import com.kosalgeek.android.photoutil.CameraPhoto;
import com.kosalgeek.android.photoutil.GalleryPhoto;
import com.kosalgeek.android.photoutil.ImageLoader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by joebuntu on 11/16/16.
 */

public class Homepage extends AppCompatActivity implements View.OnClickListener {
    CameraPhoto cameraPhoto;
    GalleryPhoto galleryPhoto;
    final int Camera_resquest = 1333;
    final int Gallery_resquest = 1334;
    Intent caller;
    Toolbar tool;
    ImageView cov;
    boolean in_action_mode = false;
    RecyclerView recyclerView;
    TextView countertv, phonetv;
    FloatingActionButton fb;

    HomeAdapter adapter;
    RecyclerView.LayoutManager lm;
    int[] photo = new int[]{R.drawable.nd, R.raw.mum, R.raw.album3, R.drawable.dee, R.drawable.jj, R.drawable.hom, R.drawable.ose, R.raw.akho, R.raw.paul,
            R.raw.oseo, R.drawable.vv, R.raw.holy};

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
        phonetv = (TextView) findViewById(R.id.phoneid);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        cameraPhoto = new CameraPhoto(getApplicationContext());
        galleryPhoto = new GalleryPhoto(Homepage.this);
        lm = new GridLayoutManager(this, 2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(lm);
        recyclerView.addItemDecoration(new RecyclereviewGridSpacing(2, dpToPx(10), true));
        countertv = (TextView) findViewById(R.id.tooltext);
        countertv.setVisibility(View.GONE);
        cov = (ImageView) findViewById(R.id.backdrop);
        cov.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showpicturePopup(cov);
            }
        });

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
        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        caller = new Intent(Intent.ACTION_CALL, Uri.parse("+2348102548962"));
        caller.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        if (getIntent().getBooleanExtra("EXit", false)) {
            finish();
        }

        fb = (FloatingActionButton)findViewById(R.id.float1);
        fb.setOnClickListener(this);
    }

    public void showpicturePopup(View view) {
        PopupMenu popup = new PopupMenu(Homepage.this, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.picture_menu, popup.getMenu());
        popup.setOnMenuItemClickListener(new PictureItemClickListener());
        popup.show();
    }

    @Override
    public void onClick(View view) {
        int currentpos = 0;
        Data pc = cont1.get(currentpos);
        if (view == fb){
            additem(currentpos, pc);
        }
    }

    class PictureItemClickListener implements PopupMenu.OnMenuItemClickListener {
        public PictureItemClickListener() {

        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.camera:
                    try {
                        startActivityForResult(cameraPhoto.takePhotoIntent(), Camera_resquest);
                        cameraPhoto.addToGallery();
                    } catch (IOException e) {
                        Toast.makeText(Homepage.this, " Cannot access your camera", Toast.LENGTH_SHORT).show();
                    }
                    return true;
                case R.id.gallery:
                    try {
                        startActivityForResult(galleryPhoto.openGalleryIntent(), Gallery_resquest);
                    } catch (Exception e) {
                        Toast.makeText(Homepage.this, " Cannot access your media", Toast.LENGTH_SHORT).show();
                    }
                    return true;
                default:
            }
            return false;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == Camera_resquest) {
                String photopat = cameraPhoto.getPhotoPath();
                try {
                    Bitmap bitmap = ImageLoader.init().from(photopat).requestSize(512, 512).getBitmap();
                    cov.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    Toast.makeText(Homepage.this, "Failed", Toast.LENGTH_LONG).show();
                }

            } else if (requestCode == Gallery_resquest) {
                Uri uri = data.getData();
                galleryPhoto.setPhotoUri(uri);
                String photopat = galleryPhoto.getPath();
                try {
                    Bitmap bitmap = ImageLoader.init().from(photopat).requestSize(512, 512).getBitmap();
                    cov.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    Toast.makeText(Homepage.this, "Failed", Toast.LENGTH_LONG).show();
                }
            }
        }
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
    public MenuInflater getMenuInflater() {
        return super.getMenuInflater();
    }

    public void showPopupMenu(View view) {
        //Inflate Menu
        PopupMenu popup = new PopupMenu(Homepage.this, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.profile_menu, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
        popup.show();
    }

    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {
        public MyMenuItemClickListener() {

        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.call:
                    startActivity(caller);
                    return true;
                case R.id.msg:
                    Toast.makeText(Homepage.this, "W'll attend to you soon", Toast.LENGTH_LONG).show();
                    return true;
                case R.id.view:
                    try {
                        Intent profile = new Intent(Homepage.this, UserActivity.class);
                        startActivity(profile);
                    } catch (Exception e) {
                        Toast.makeText(Homepage.this, "Something went wrong", Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                    return true;
                case R.id.remove:
                    int currentpos = 0;
                    Data pc = cont1.get(currentpos);
                    removeitem(pc);
                    Toast.makeText(Homepage.this, "W'll attend to you soon", Toast.LENGTH_LONG).show();
                    return true;
                default:
            }
            return false;


        }

    }



    public void onLongClick(View view) {
        CollapsingToolbarLayout cb = (CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);
        cb.setTitleEnabled(false);
        tool.getMenu().clear();
        tool.inflateMenu(R.menu.longclick_menu);
        countertv.setVisibility(View.VISIBLE);
        in_action_mode = true;

        adapter.notifyDataSetChanged();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class RecyclereviewGridSpacing extends RecyclerView.ItemDecoration{
        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public RecyclereviewGridSpacing(int spanCount, int spacing, boolean includeEdge){
            this.spacing = spacing;
            this.spanCount = spanCount;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
        }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Homepage.this, Login.class);
        startActivity(intent);
    }
    private void additem(int position, Data data) {
        cont1.add(position, data);
      adapter.notifyItemInserted(position);
    }
    private void removeitem(Data data) {
        int position = cont1.indexOf(data);
        cont1.remove(position);
        adapter.notifyItemRemoved(position);
    }
}




