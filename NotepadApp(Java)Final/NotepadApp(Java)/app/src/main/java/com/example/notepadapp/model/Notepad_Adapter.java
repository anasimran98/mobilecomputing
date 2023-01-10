package com.example.notepadapp.model;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notepadapp.Fragments.FavoriteModel;
import com.example.notepadapp.R;
import com.example.notepadapp.Fragments.NotepadModel;


import java.util.List;

import io.realm.Realm;

public class Notepad_Adapter extends RecyclerView.Adapter<Notepad_Adapter.ViewHolder> {
    Realm realm = Realm.getDefaultInstance();
    private List<NotepadModel> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
Context context;
    // data is passed into the constructor
    public Notepad_Adapter(Context context, List<NotepadModel> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.context=context;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.row_notepad, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.description.setText(mData.get(position).getDescription());
        holder.day.setText(mData.get(position).getDay());
        holder.time.setText(mData.get(position).getTime());
        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                realm.beginTransaction();
                NotepadModel timeTableModel = realm.where(NotepadModel.class)
                        .equalTo("id", mData.get(position).getId()).findFirst();
                timeTableModel.deleteFromRealm();
                realm.commitTransaction();
                notifyDataSetChanged();
            }
        });
            holder.imageViewfavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    holder.imageViewfavorite.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.favoritefill));
                    realm.beginTransaction();
                    FavoriteModel favoriteModel = new FavoriteModel();
                    Number newId = realm.where(FavoriteModel.class).max("id");
                    if(newId != null){
                        favoriteModel.setId(newId.intValue()+1);}
                    else{
                        favoriteModel.setId(1);}

                    favoriteModel.setDescription(mData.get(position).getDescription());
                    favoriteModel.setDay(mData.get(position).getDay());
                    favoriteModel.setDate(mData.get(position).getDate());
                    realm.copyToRealmOrUpdate(favoriteModel);
                    realm.commitTransaction();
                }
            });
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name;
        TextView description;
        TextView day;
        TextView time;
        TextView deleteBtn;
        ImageView imageViewfavorite;

        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            day = itemView.findViewById(R.id.day);
            time = itemView.findViewById(R.id.time);
            deleteBtn = itemView.findViewById(R.id.del_btn);
            imageViewfavorite = itemView.findViewById(R.id.imageViewfavorite);

        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
