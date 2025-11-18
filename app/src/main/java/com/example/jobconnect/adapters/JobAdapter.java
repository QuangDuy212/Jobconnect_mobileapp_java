package com.example.jobconnect.adapters; // (Hoặc tên package của bạn)


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jobconnect.R;
import com.example.jobconnect.models.Job;

import java.util.ArrayList;
import java.util.List;

// Giả sử tên file layout item là item_jobcard.xml
// Và các ID bên trong là:
// tvJobTitle, tvCompanyName, tvLocation, tvSalary, ivLogo

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.JobViewHolder> {

    private List<Job> jobList = new ArrayList<>();
    private Context context;
    private OnItemClickListener listener;

    // Interface để xử lý click
    public interface OnItemClickListener {
        void onItemClick(Job job);
    }

    public JobAdapter(Context context, OnItemClickListener listener) {
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public JobViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_jobcard, parent, false);
        return new JobViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JobViewHolder holder, int position) {
        Job currentJob = jobList.get(position);
        holder.bind(currentJob, listener);
    }

    @Override
    public int getItemCount() {
        return jobList.size();
    }

    // Hàm để cập nhật danh sách công việc
    public void setJobs(List<Job> jobs) {
        this.jobList = jobs;
        notifyDataSetChanged(); // Thông báo cho Adapter có dữ liệu mới
    }

    // --- ViewHolder ---
    static class JobViewHolder extends RecyclerView.ViewHolder {
        private TextView tvJobTitle, tvCompanyName, tvLocation, tvSalary;
        // private ImageView ivLogo; // Nếu bạn dùng thư viện Glide/Picasso để load ảnh

        public JobViewHolder(@NonNull View itemView) {
            super(itemView);
            // Ánh xạ View từ layout item_jobcard.xml
            tvJobTitle = itemView.findViewById(R.id.tv_job_title_item); // Cần kiểm tra ID này
            tvCompanyName = itemView.findViewById(R.id.tv_company_name); // Cần kiểm tra ID này
            tvLocation = itemView.findViewById(R.id.tv_location_item); // Cần kiểm tra ID này
            tvSalary = itemView.findViewById(R.id.tv_salary_item); // Cần kiểm tra ID này
            // ivLogo = itemView.findViewById(R.id.ivLogo);
        }

        public void bind(final Job job, final OnItemClickListener listener) {
            tvJobTitle.setText(job.getJobTitle());
            tvCompanyName.setText(job.getCompanyName());
            tvLocation.setText(job.getLocation());
            tvSalary.setText(job.getSalary());

            // (Tùy chọn) Load ảnh logo công ty
            // Ví dụ dùng Glide:
            // Glide.with(itemView.getContext())
            //      .load(job.getCompanyLogoUrl())
            //      .placeholder(R.drawable.company_logo_placeholder) // ảnh placeholder
            //      .into(ivLogo);

            // Gán sự kiện click
            itemView.setOnClickListener(v -> listener.onItemClick(job));
        }
    }
}