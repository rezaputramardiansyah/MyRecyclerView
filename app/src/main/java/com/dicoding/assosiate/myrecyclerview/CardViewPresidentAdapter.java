package com.dicoding.assosiate.myrecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CardViewPresidentAdapter extends RecyclerView.Adapter<CardViewPresidentAdapter.CardViewViewHolder> {
    private ArrayList<President>listPresident;
    private Context context;

    public CardViewPresidentAdapter(Context context){
        this.context = context;
    }

    public ArrayList<President>getListPresident(){
        return listPresident;
    }

    public void setListPresident(ArrayList<President> listPresident){
        this.listPresident = listPresident;
    }

    @Override
    public CardViewViewHolder onCreateViewHolder(ViewGroup perent, int viewType){
        View view = LayoutInflater.from(perent.getContext()).inflate(R.layout.item_cardview_preseident, perent, false);
        CardViewViewHolder viewHolder = new CardViewViewHolder(view);
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(CardViewViewHolder holder, int position){
        President p = getListPresident().get(position);

        Glide.with(context).load(p.getPhoto()).into(holder.imgPhoto);

        holder.tvName.setText(p.getName());
        holder.tvRemarks.setText(p.getRemarks());

        holder.btnFavorite.setOnClickListener(new CustomOnItemClickListener(position, new CustomOnItemClickListener.OnItemClickCallback(){
            @Override
            public void onItemClicked(View view,int position){
                Toast.makeText(context,"Favorite " + getListPresident().get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        }));

        holder.btnShere.setOnClickListener(new CustomOnItemClickListener(position,new CustomOnItemClickListener.OnItemClickCallback(){
            @Override
            public void onItemClicked(View view, int position){
                Toast.makeText(context,"Shere " + getListPresident().get(position).getName(),Toast.LENGTH_SHORT).show();
            }
        }));
    }

    @Override
    public int getItemCount(){
        return getListPresident().size();
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder{
        ImageView imgPhoto;
        TextView tvName, tvRemarks;
        Button btnFavorite, btnShere;
        public CardViewViewHolder(View itemView){
            super(itemView);
            imgPhoto = (ImageView)itemView.findViewById(R.id.img_item_photo);
            tvName = (TextView)itemView.findViewById(R.id.tv_item_name);
            tvRemarks = (TextView)itemView.findViewById(R.id.tv_item_remarks);
            btnFavorite = (Button)itemView.findViewById(R.id.btn_set_favorite);
            btnShere = (Button)itemView.findViewById(R.id.btn_set_shere);
        }
    }

    public static class CustomOnItemClickListener implements View.OnClickListener{
        private int position;
        private OnItemClickCallback onItemClickCallback;
        public CustomOnItemClickListener(int position, OnItemClickCallback onItemClickCallback){
            this.position =position;
            this.onItemClickCallback = onItemClickCallback;
        }

        @Override
        public void onClick(View view){
            onItemClickCallback.onItemClicked(view, position);
        }
        public interface OnItemClickCallback{
            void onItemClicked(View view,int position);
        }
    }
}
