package com.example.jobconnect.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button; // <-- THÊM MỚI
import android.widget.ImageView; // <-- THÊM MỚI
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast; // <-- THÊM MỚI

import androidx.appcompat.app.AppCompatActivity;

// THÊM MỚI để dùng Glide
import com.bumptech.glide.Glide;

import com.example.jobconnect.R;
import com.example.jobconnect.database.AppDatabase; // (Kiểm tra lại đường dẫn)
import com.example.jobconnect.models.Job;           // (Kiểm tra lại đường dẫn)

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JobDetailActivity extends AppCompatActivity {

    // Khai báo các View trong layout
    private TextView tvJobTitleDetail, tvCompanyNameDetail, tvLocationDetail, tvSalaryDetail, tvDescriptionDetail, tvRequirementsDetail, tvPostDateDetail;
    private ProgressBar progressBarDetail;
    private ImageView ivCompanyLogoDetail; // <-- THÊM MỚI
    private Button btnApplyNow; // <-- THÊM MỚI

    private AppDatabase db;
    private int jobId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_jobdetail);

        // Lấy ID công việc từ Intent
        jobId = getIntent().getIntExtra("JOB_ID", -1);
        if (jobId == -1) {
            finish();
            return;
        }

        // Ánh xạ Views (Khớp với file XML đã sửa)
        tvJobTitleDetail = findViewById(R.id.tvJobTitleDetail);
        tvCompanyNameDetail = findViewById(R.id.tvCompanyNameDetail);
        tvLocationDetail = findViewById(R.id.tvLocationDetail);
        tvSalaryDetail = findViewById(R.id.tvSalaryDetail);
        tvDescriptionDetail = findViewById(R.id.tvDescriptionDetail);
        tvRequirementsDetail = findViewById(R.id.tvRequirementsDetail);
        tvPostDateDetail = findViewById(R.id.tvPostDateDetail);
        progressBarDetail = findViewById(R.id.progressBarDetail);

        // Ánh xạ các View mới
        ivCompanyLogoDetail = findViewById(R.id.ivCompanyLogoDetail); // <-- THÊM MỚI
        btnApplyNow = findViewById(R.id.btn_apply_now); // <-- THÊM MỚI

        // Lấy instance Database
        db = AppDatabase.getInstance(this);

        loadJobDetails();

        // (Tùy chọn) Thêm sự kiện click cho nút Ứng tuyển
        btnApplyNow.setOnClickListener(v -> {
            Toast.makeText(JobDetailActivity.this, "Đã ứng tuyển!", Toast.LENGTH_SHORT).show();
            // Thêm logic ứng tuyển của bạn ở đây
        });
    }

    private void loadJobDetails() {
        progressBarDetail.setVisibility(View.VISIBLE); // Hiện loading

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            // Truy vấn database (background thread)
            Job job = db.jobDao().getJobById(jobId);

            // Cập nhật UI (Main thread)
            runOnUiThread(() -> {
                progressBarDetail.setVisibility(View.GONE); // Ẩn loading
                if (job != null) {
                    populateUI(job);
                } else {
                    tvJobTitleDetail.setText("Không tìm thấy công việc");
                }
            });
        });
    }

    private void populateUI(Job job) {
        tvJobTitleDetail.setText(job.getJobTitle());
        tvCompanyNameDetail.setText(job.getCompanyName());
        tvLocationDetail.setText(job.getLocation());
        tvSalaryDetail.setText(job.getSalary());
        tvDescriptionDetail.setText(job.getDescription());
        tvRequirementsDetail.setText(job.getRequirements());
        tvPostDateDetail.setText("Đăng ngày: " + job.getPostDate());

        // <-- THÊM MỚI: Dùng Glide để load ảnh logo -->
        Glide.with(this) // Context (Activity này)
                .load(job.getCompanyLogoUrl()) // Lấy đường link ảnh
                .placeholder(R.drawable.company_logo_placeholder) // Ảnh chờ
                .error(R.drawable.company_logo_placeholder) // Ảnh khi lỗi
                .into(ivCompanyLogoDetail); // Đặt ảnh vào ImageView
    }
}