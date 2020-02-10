package com.example.a24hours.Presenter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a24hours.Model.Key;
import com.example.a24hours.R;

import java.util.ArrayList;
import java.util.List;

public class MyKeysGridAdapter extends RecyclerView.Adapter<MyKeysGridAdapter.KeysHolder> {

    private List<Key> keyList = new ArrayList<>();
    private OnKeyClickListener keysListener;

    @NonNull
    @Override
    public KeysHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.institution_key_items_layout,parent,false);
        return new KeysHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KeysHolder holder, int position) {
        Key key = keyList.get(position);
        holder.setData(key);
    }

    @Override
    public int getItemCount() {
        return keyList.size();
    }

    public void addKeyItems(ArrayList<Key> listOfKeys) {
        if (keyList.size() != 0) {
            keyList.clear();
        }

        keyList.addAll(listOfKeys);
    }

    class KeysHolder extends RecyclerView.ViewHolder {

        private ImageView keyImageView;
        private TextView keyNameTxtView;
        private View rootView;

        public KeysHolder(@NonNull View itemView) {
            super(itemView);
            keyImageView = itemView.findViewById(R.id.key_title_icon_id);
            keyNameTxtView = itemView.findViewById(R.id.key_title_txt_id);
            rootView = itemView.findViewById(R.id.key_card_title_id);
        }

        public void setData(final Key key) {
            keyNameTxtView.setText(key.getTextTitle());
            keyImageView.setImageResource(key.getTitleIcon());

            rootView.setOnClickListener(v -> keysListener.onKeyClick(key));
        }
    }

    public interface OnKeyClickListener {
        void onKeyClick(Key key);
    }

    public void setOnKeyClickListener(OnKeyClickListener keyClickListener) {
        keysListener = keyClickListener;
    }
}
