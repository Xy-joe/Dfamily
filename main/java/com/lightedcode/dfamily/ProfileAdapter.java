package com.lightedcode.dfamily;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joebuntu on 11/24/16.
 */

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.MiViewHolder> {
     Context proctx;
    ArrayList<ProfileContent> result = new ArrayList<>();
    UserActivity userActivity;
    public  static class MiViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView status, msgtitle;
        public ImageView opti,upload,galery, dp;
        public EditText stat;
        Button share, cancel;
        CardView cardView;
        UserActivity userActivity;



        public MiViewHolder(View itemView, UserActivity userActivity) {
            super(itemView);
            msgtitle = (TextView)itemView.findViewById(R.id.msgtitle);
            dp = (ImageView)itemView.findViewById(R.id.upload);
            share = (Button)itemView.findViewById(R.id.sharebutton);
            cancel = (Button) itemView.findViewById(R.id.cancelbutton);
            galery = (ImageView)itemView.findViewById(R.id.goption);
            stat = (EditText)itemView.findViewById(R.id.post);
            opti = (ImageView) itemView.findViewById(R.id.optio);
            cardView = (CardView)itemView.findViewById(R.id.card_view2);
            dp.setVisibility(View.VISIBLE);
            msgtitle.setVisibility(View.VISIBLE);
            galery.setVisibility(View.GONE);
            opti.setVisibility(View.GONE);
            share.setVisibility(View.GONE);
            cancel.setVisibility(View.GONE);
            stat.setVisibility(View.VISIBLE);
            this.userActivity = userActivity;



        }

        @Override
        public void onClick(View view) {
            if (view == stat){
                stat.setVisibility(View.VISIBLE);
                dp.setVisibility(View.GONE);
                msgtitle.setVisibility(View.VISIBLE);
                galery.setVisibility(View.VISIBLE);
                opti.setVisibility(View.VISIBLE);
                share.setVisibility(View.VISIBLE);
                cancel.setVisibility(View.VISIBLE);
            }


            }



    }
    public ProfileAdapter(Context proctx, ArrayList<ProfileContent> result){

        this.proctx = proctx;
        this.result = result;
        userActivity = (UserActivity) proctx;

    }

    @Override
    public MiViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View ite = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_content, parent, false);
        MiViewHolder mvh = new MiViewHolder(ite, userActivity);
        return mvh;
    }

    @Override
    public void onBindViewHolder( MiViewHolder holder, int position) {
        holder.msgtitle.setText(result.get(position).getTitle());
        holder.stat.setText(result.get(position).getPost());
        holder.galery.setImageResource(result.get(position).getGallery());
        holder.upload.setImageResource(result.get(position).getUpload());



    }

    @Override
    public int getItemCount() {
        return result.size();
    }
}
