package model;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.notepadapp.R;

import java.util.List;

import io.realm.Realm;

public class Notepad_Adapter extends RecyclerView.Adapter<Notepad_Adapter.ViewHolder> {
    Realm realm = Realm.getDefaultInstance();
    private List<NotepadModel> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    public Notepad_Adapter(Context context, List<NotepadModel> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
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

        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            day = itemView.findViewById(R.id.day);
            time = itemView.findViewById(R.id.time);
            deleteBtn = itemView.findViewById(R.id.del_btn);

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
