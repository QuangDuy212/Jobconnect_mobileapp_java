package com.example.jobconnect;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class JobDetailActivity extends AppCompatActivity {

    // Khai báo các Views từ layout 'acitivity_jobdetail.xml'
    private ImageView btnBack, btnShare, btnBookmark, imgCompanyLogo;
    private Button btnApply;
    private TextView tvJobTitle, tvCompanyName, tvSalary, tvLocation, tvJobType;
    private TextView tvJobDescription, tvJobRequirements, tvJobBenefits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Đặt layout là 'acitivity_jobdetail.xml'
        setContentView(R.layout.acitivity_jobdetail); // Chú ý: tên file của bạn là 'acitivity...'

        // 1. Ánh xạ tất cả các Views
        initViews();

        // 2. Lấy dữ liệu Job được gửi từ JobListActivity
        getIntentData();

        // 3. Cài đặt trình xử lý click
        setupClickListeners();
    }

    private void initViews() {
        // Header
        btnBack = findViewById(R.id.btn_back);
        btnShare = findViewById(R.id.btn_share);
        btnBookmark = findViewById(R.id.btn_bookmark);

        // Footer
        btnApply = findViewById(R.id.btn_apply);

        // Body (Tóm tắt)
        imgCompanyLogo = findViewById(R.id.img_company_logo);
        tvJobTitle = findViewById(R.id.tv_job_title);
        tvCompanyName = findViewById(R.id.tv_company_name);
        tvSalary = findViewById(R.id.tv_salary);
        tvLocation = findViewById(R.id.tv_location);
        tvJobType = findViewById(R.id.tv_job_type);

        // Body (Chi tiết)
        tvJobDescription = findViewById(R.id.tv_job_description);
        tvJobRequirements = findViewById(R.id.tv_job_requirements);
        tvJobBenefits = findViewById(R.id.tv_job_benefits);
    }

    private void getIntentData() {
        // Nhận đối tượng Job từ Intent
        Job job = (Job) getIntent().getSerializableExtra("JOB_DATA");

        if (job != null) {
            // Nếu có dữ liệu, gán vào các Views
            populateUI(job);
        } else {
            // Xử lý trường hợp lỗi (không nhận được dữ liệu)
            Toast.makeText(this, "Lỗi: Không thể tải chi tiết công việc", Toast.LENGTH_SHORT).show();
            finish(); // Đóng Activity nếu không có dữ liệu
        }
    }

    private void populateUI(Job job) {
        // Gán dữ liệu từ đối tượng Job lên UI
        tvJobTitle.setText(job.getTitle());
        tvCompanyName.setText(job.getCompanyName());
        tvSalary.setText(job.getSalary());
        tvLocation.setText(job.getLocation());
        tvJobType.setText(job.getJobType());
        tvJobDescription.setText(job.getDescription());
        tvJobRequirements.setText(job.getRequirements());
        tvJobBenefits.setText(job.getBenefits());
        imgCompanyLogo.setImageResource(job.getCompanyLogoResId());
    }

    private void setupClickListeners() {
        // Nút Back
        btnBack.setOnClickListener(v -> {
            finish(); // Đóng Activity hiện tại và quay lại màn hình danh sách
        });

        // Nút Ứng tuyển
        btnApply.setOnClickListener(v -> {
            Toast.makeText(this, "Ứng tuyển thành công!", Toast.LENGTH_SHORT).show();
            // (Trong tương lai, ở đây sẽ mở màn hình nộp CV)
        });

        // Nút Share
        btnShare.setOnClickListener(v -> {
            Toast.makeText(this, "Chia sẻ công việc...", Toast.LENGTH_SHORT).show();
        });

        // Nút Bookmark
        btnBookmark.setOnClickListener(v -> {
            Toast.makeText(this, "Đã lưu công việc!", Toast.LENGTH_SHORT).show();
        });
    }
}