package com.example.a24hours.Presenter;

import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a24hours.R;
import java.util.ArrayList;
import java.util.List;

public class MyDistrictsListAdapter extends RecyclerView.Adapter<MyDistrictsListAdapter.KeysHolder> {

    private List<String> districtsList = new ArrayList<>();
    private OnDistrictClickListener onDistrictClickListener;
    private Typeface typeface;

    @NonNull
    @Override
    public MyDistrictsListAdapter.KeysHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.key_district_items_layout,parent,false);
        return new KeysHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyDistrictsListAdapter.KeysHolder holder, int position) {
        String string = districtsList.get(position);
        holder.setData(string);
    }

    @Override
    public int getItemCount() {
        return districtsList.size();
    }

    public void addDistricts(ArrayList<String> list) {
        if(!districtsList.isEmpty()){
            districtsList.clear();
        }
        districtsList.addAll(list);
    }

    class KeysHolder extends RecyclerView.ViewHolder {

        private TextView districtTxtView;
        private View rootView;

        public KeysHolder(@NonNull View itemView) {
            super(itemView);
            districtTxtView = itemView.findViewById(R.id.district_name_id);
            rootView = itemView.findViewById(R.id.district_card_view_id);
        }

        public void setData(final String string) {
            districtTxtView.setText(string);

            rootView.setOnClickListener(v -> onDistrictClickListener.onDistrictClick(string));
        }
    }

    public interface OnDistrictClickListener {
        void onDistrictClick(String district);
    }

    public void setOnDistrictClickListener(OnDistrictClickListener onDistrictClickListener) {
        this.onDistrictClickListener = onDistrictClickListener;
    }
}
