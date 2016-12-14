package com.lightedcode.dfamily;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.kosalgeek.android.photoutil.CameraPhoto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by joebuntu on 11/17/16.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ContactViewHolder> {

  CameraPhoto cameraPhoto;
    final int CAMERA_REQUEST = 12232;
    ArrayList<Data> adapter_list = new ArrayList<>();
     MainActivity homepage;
    MainActivity mainActivity;
    Intent in;
    Context ctx;
    Homepage home; CheckBox cb;
    public  class ContactViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener{
        ImageView img, imgicon;
        TextView name, email;
        CheckBox cb;
        MainActivity homepage1;
        CardView cardView;


        public ContactViewHolder(View itemview, MainActivity homepage1) {
            super(itemview);
            img = (ImageView) itemview.findViewById(R.id.thumbnail);
            img.setOnLongClickListener(this);
            name = (TextView) itemview.findViewById(R.id.name);
            imgicon = (ImageView) itemview.findViewById(R.id.overflow);
            cb = (CheckBox) itemview.findViewById(R.id.checkbox);
            cb.setVisibility(View.GONE);
            this.homepage1 = homepage1;
            cardView = (CardView) itemview.findViewById(R.id.card_view);
            cameraPhoto = new CameraPhoto(ctx);

        }

        @Override
        public boolean onLongClick(View view) {
            cb.setVisibility(View.VISIBLE);
            home.onLongClick(view);

            return false;
        }
    }
    public HomeAdapter(ArrayList<Data> adapter_list, Context ctx){

        this.ctx = ctx;
        this.adapter_list = adapter_list;
        home = (Homepage) ctx;
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_albums_activity, parent, false);
        ContactViewHolder cvh = new ContactViewHolder(view, homepage);
        return cvh;
    }

    @Override
    public void onBindViewHolder(final ContactViewHolder holder, int position) {

        holder.img.setImageResource(adapter_list.get(position).getImgid());
        holder.name.setText(adapter_list.get(position).getName());
       // holder.email.setText(adapter_list.get(position).getEmail());

        int currentposition = position;
      final   Data pc = adapter_list.get(position);

        Glide .with(ctx).load(adapter_list.get(position).getImgid()).into(holder.img);


        holder.imgicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               home.showPopupMenu(holder.imgicon);

            }
        });




    }



    /**
     * Showing popup menu when tapping on 3 dots
     */
    private void showPopupMenu(View view){
        //Inflate Menu
        PopupMenu popup = new PopupMenu(ctx, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.profile_menu, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
        popup.show();
    }


    /**
     * click Listener for popup menu items
     */
    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {
        public MyMenuItemClickListener() {

        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.call:

                    Toast.makeText(ctx, "W'll attend to you soon", Toast.LENGTH_LONG).show();
                    return true;
                case R.id.msg:
                    Toast.makeText(ctx, "W'll attend to you soon", Toast.LENGTH_LONG).show();
                    return true;
                case R.id.view:
                    Toast.makeText(ctx, "W'll attend to you soon", Toast.LENGTH_LONG).show();
                    return true;
                case R.id.remove:
                    Toast.makeText(ctx, "W'll attend to you soon", Toast.LENGTH_LONG).show();
                    return true;
                default:
            }
            return false;

        }

    }



    @Override
    public int getItemCount() {
        return adapter_list.size();
    }






    public void updateAdapter(ArrayList<Data> list){
        for (Data contact : list){
            adapter_list.remove(contact);
        }
        notifyDataSetChanged();
    }
    public void onCheckboxClicked(){
        if (cb.isClickable()){
            cb.setChecked(true);
        }else {
            cb.setChecked(false);
        }
    }
    private void additem(int position, Data data) {
        adapter_list.add(position, data);
        notifyItemInserted(position);
    }


}
