package com.example.jobconnect.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jobconnect.R;
import com.example.jobconnect.models.CompanyModel;

import java.util.List;

public class CompanyAdapter extends RecyclerView.Adapter<CompanyAdapter.ViewHolder> {

    private List<CompanyModel> companyList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(CompanyModel company);
    }

    public CompanyAdapter(List<CompanyModel> companyList, OnItemClickListener listener) {
        this.companyList = companyList;
        this.listener = listener;
    }

    public void updateList(List<CompanyModel> newList) {
        this.companyList = newList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_company_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CompanyModel company = companyList.get(position);
        holder.tvName.setText(company.getName());
        holder.tvField.setText(company.getField());
        holder.tvAddress.setText(company.getAddress());

        holder.itemView.setOnClickListener(v -> listener.onItemClick(company));
    }

    @Override
    public int getItemCount() {
        return companyList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvField, tvAddress;
        CardView card;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_company_name);
            tvField = itemView.findViewById(R.id.tv_company_field);
            tvAddress = itemView.findViewById(R.id.tv_company_address);
            card = itemView.findViewById(R.id.company_card);
        }
    }
}

