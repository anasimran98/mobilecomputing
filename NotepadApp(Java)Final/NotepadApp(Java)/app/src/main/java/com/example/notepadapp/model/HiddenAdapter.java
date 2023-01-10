package com.example.notepadapp.model;

import android.annotation.SuppressLint;
        import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.TextView;

        import androidx.annotation.NonNull;
        import androidx.fragment.app.FragmentActivity;
        import androidx.recyclerview.widget.RecyclerView;

        import com.example.notepadapp.Fragments.FavoriteModel;
        import com.example.notepadapp.Fragments.NotepadModel;
        import com.example.notepadapp.R;

        import java.util.List;

        import io.realm.Realm;

public class HiddenAdapter extends RecyclerView.Adapter<HiddenAdapter.ViewHolder> {
    Realm realm = Realm.getDefaultInstance();
    private List<FavoriteModel> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    Context context;

    public HiddenAdapter(Context context,List<FavoriteModel> mData) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = mData;
        this.context = context;
    }

    @NonNull
    @Override
    public HiddenAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.rowfavoritedesign, parent, false);
        return new HiddenAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HiddenAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.description.setText(mData.get(position).getDescription());
        holder.day.setText(mData.get(position).getDay());
        holder.time.setText(mData.get(position).getTime());
        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                realm.beginTransaction();
                FavoriteModel timeTableModel = realm.where(FavoriteModel.class)
                        .equalTo("id", mData.get(position).getId()).findFirst();
                timeTableModel.deleteFromRealm();
                realm.commitTransaction();
                notifyDataSetChanged();
            }
        });
    }
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
    @Override
    public int getItemCount() {
        return mData.size();
    }

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
}
