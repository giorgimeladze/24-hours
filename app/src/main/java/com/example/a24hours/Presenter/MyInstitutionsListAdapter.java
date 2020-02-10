package com.example.a24hours.Presenter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a24hours.Model.Institution;
import com.example.a24hours.R;

import java.util.ArrayList;
import java.util.List;

public class MyInstitutionsListAdapter extends RecyclerView.Adapter<MyInstitutionsListAdapter.InstitutionHolder> {

    private List<Institution> list = new ArrayList<>();
    private OnInstitutionClickListener onInstitutionClickListener;


    @NonNull
    @Override
    public MyInstitutionsListAdapter.InstitutionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.key_institutions_recycler_layout, parent, false);
        return new InstitutionHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyInstitutionsListAdapter.InstitutionHolder holder, int position) {
        Institution institution = list.get(position);
        holder.setData(institution);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addInstitutions(ArrayList<Institution> arrayList) {
        if (!list.isEmpty()) list.clear();

        list.addAll(arrayList);
    }

    class InstitutionHolder extends RecyclerView.ViewHolder {

        private TextView txtView;
        private View view;

        public InstitutionHolder(@NonNull View itemView) {
            super(itemView);
            txtView = itemView.findViewById(R.id.institution_name_txt_id);
            view = itemView.findViewById(R.id.institution_card_view_id);
        }

        public void setData(final Institution institution) {
            String name;
            if (institution.getName().length() > 24) {
                name = institution.getName().substring(0, 24) + "...";
                txtView.setText(name);
            } else
                txtView.setText(institution.getName());
            view.setOnClickListener(v -> onInstitutionClickListener.onInstitutionClicked(institution));
        }
    }

    public interface OnInstitutionClickListener {
        void onInstitutionClicked(Institution institution);
    }

    public void setOnDistrictClickListener(OnInstitutionClickListener onInstitutionClickListener) {
        this.onInstitutionClickListener = onInstitutionClickListener;
    }
}
