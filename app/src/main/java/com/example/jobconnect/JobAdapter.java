package com.example.jobconnect; // (Hoặc tên package của bạn)

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.JobViewHolder> {

    private List<Job> jobList;
    private Context context;
    private OnJobClickListener onJobClickListener; // Interface để xử lý click

    // Interface để gửi sự kiện click về Activity
    public interface OnJobClickListener {
        void onJobClick(Job job);
    }

    public JobAdapter(Context context, List<Job> jobList, OnJobClickListener listener) {
        this.context = context;
        this.jobList = jobList;
        this.onJobClickListener = listener;
    }

    @NonNull
    @Override
    public JobViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate layout 'item_jobcard.xml'
        View view = LayoutInflater.from(context).inflate(R.layout.item_jobcard, parent, false);
        return new JobViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JobViewHolder holder, int position) {
        Job job = jobList.get(position);

        // Gán dữ liệu từ đối tượng Job vào các View trong item_jobcard
        holder.tvJobTitle.setText(job.getTitle());
        holder.tvCompanyName.setText(job.getCompanyName());
        holder.tvSalary.setText(job.getSalary());
        holder.tvLocation.setText(job.getLocation());
        holder.imgCompanyLogo.setImageResource(job.getCompanyLogoResId());

        // Xử lý khi người dùng nhấn vào một item (một công việc)
        holder.itemView.setOnClickListener(v -> {
            onJobClickListener.onJobClick(job);
        });

        // Xử lý khi nhấn vào nút bookmark (ví dụ: hiển thị Toast)
        holder.btnBookmark.setOnClickListener(v -> {
            Toast.makeText(context, "Đã lưu công việc: " + job.getTitle(), Toast.LENGTH_SHORT).show();
            // Ở đây bạn có thể thay đổi icon bookmark
        });
    }

    @Override
    public int getItemCount() {
        return jobList.size();
    }

    // Lớp ViewHolder để giữ các View của 'item_jobcard.xml'
    public static class JobViewHolder extends RecyclerView.ViewHolder {
        ImageView imgCompanyLogo, btnBookmark;
        TextView tvJobTitle, tvCompanyName, tvSalary, tvLocation;

        public JobViewHolder(@NonNull View itemView) {
            super(itemView);
            // Ánh xạ các ID từ item_jobcard.xml
            imgCompanyLogo = itemView.findViewById(R.id.img_company_logo);
            btnBookmark = itemView.findViewById(R.id.btn_bookmark_item);
            tvJobTitle = itemView.findViewById(R.id.tv_job_title_item);
            tvCompanyName = itemView.findViewById(R.id.tv_company_name_item);
            tvSalary = itemView.findViewById(R.id.tv_salary_item);
            tvLocation = itemView.findViewById(R.id.tv_location_item);
        }
    }
}