package com.example.jobconnect;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

// Triển khai interface OnJobClickListener của Adapter
public class JobListActivity extends AppCompatActivity implements JobAdapter.OnJobClickListener {

    private RecyclerView rvJobList;
    private JobAdapter jobAdapter;
    private List<Job> jobList;

    private ImageView btnNotifications;
    private EditText etSearch;
    private BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Đặt layout là 'activity_joblist.xml'
        setContentView(R.layout.activity_joblist);

        // 1. Ánh xạ Views
        initViews();

        // 2. Cài đặt RecyclerView
        setupRecyclerView();

        // 3. Cài đặt các trình xử lý click khác
        setupClickListeners();
    }

    private void initViews() {
        rvJobList = findViewById(R.id.rv_job_list);
        btnNotifications = findViewById(R.id.btn_notifications);
        etSearch = findViewById(R.id.et_search);
        bottomNavigation = findViewById(R.id.bottom_navigation);
    }

    private void setupRecyclerView() {
        jobList = createMockData(); // Lấy dữ liệu mẫu

        // 'this' ở đây chính là OnJobClickListener, vì Activity đã triển khai (implements) nó
        jobAdapter = new JobAdapter(this, jobList, this);

        rvJobList.setLayoutManager(new LinearLayoutManager(this));
        rvJobList.setAdapter(jobAdapter);
    }

    private void setupClickListeners() {
        // Ví dụ xử lý click cho nút thông báo
        btnNotifications.setOnClickListener(v -> {
            Toast.makeText(this, "Mở màn hình thông báo", Toast.LENGTH_SHORT).show();
        });

        // Ví dụ xử lý cho bottom navigation
        bottomNavigation.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.nav_jobs) {
                Toast.makeText(this, "Bạn đang ở trang Việc làm", Toast.LENGTH_SHORT).show();
                return true;
            } else if (item.getItemId() == R.id.nav_profile) {
                Toast.makeText(this, "Chuyển sang trang Cá nhân", Toast.LENGTH_SHORT).show();
                return true;
            }
            // Thêm các else if cho các item khác
            return false;
        });
    }

    // Đây là nơi tạo dữ liệu mẫu
    private List<Job> createMockData() {
        List<Job> mockList = new ArrayList<>();

        // Job 1 (Dựa trên layout của bạn)
        mockList.add(new Job(1,
                "Java Backend Developer (Spring)",
                "Công ty Công nghệ ABC",
                "Lên đến $2500",
                "TP. Hồ Chí Minh",
                "Full-time",
                "- Tham gia phát triển các hệ thống backend sử dụng Java, Spring Boot.\n- Phân tích yêu cầu, thiết kế và triển khai các API.\n- Làm việc với cơ sở dữ liệu (MySQL, PostgreSQL).\n- Tối ưu hóa hiệu năng và bảo trì hệ thống.",
                "- Ít nhất 2 năm kinh nghiệm với Java Core và Spring Boot.\n- Có kinh nghiệm làm việc với RESTful API, Microservices.\n- Hiểu biết về OOP, Design Patterns.\n- Tư duy logic tốt, chủ động trong công việc.",
                "- Mức lương cạnh tranh, thưởng dự án, lương tháng 13.\n- Môi trường làm việc trẻ, năng động.\n- Được cung cấp trang thiết bị làm việc (Macbook...).",
                R.drawable.company_logo_placeholder)); // Sử dụng ảnh placeholder

        // Job 2
        mockList.add(new Job(2,
                "Android Developer (Kotlin)",
                "Tập đoàn XYZ",
                "Thương lượng",
                "Hà Nội (Remote)",
                "Remote",
                "- Phát triển ứng dụng Android cho dự án JobConnect.\n- Làm việc với team UI/UX và Backend.",
                "- 1 năm kinh nghiệm Kotlin.\n- Đã có sản phẩm trên Google Play là một lợi thế.",
                "- Làm việc remote 100%.\n- Giờ làm việc linh hoạt.",
                R.drawable.company_logo_placeholder));

        // Job 3
        mockList.add(new Job(3,
                "Frontend Developer (ReactJS)",
                "FPT Software",
                "$1000 - $2000",
                "Đà Nẵng",
                "Full-time",
                "- Xây dựng giao diện web cho các sản phẩm của công ty.",
                "- Thành thạo ReactJS, Redux, HTML/CSS.",
                "- Bảo hiểm FPT Care.\n- Lộ trình thăng tiến rõ ràng.",
                R.drawable.company_logo_placeholder));

        return mockList;
    }


    // === Xử lý sự kiện click từ Adapter ===
    @Override
    public void onJobClick(Job job) {
        // Khi một công việc được nhấn, mở JobDetailActivity
        Intent intent = new Intent(JobListActivity.this, JobDetailActivity.class);

        // Gửi toàn bộ đối tượng Job (đã được Serializable) sang Activity chi tiết
        intent.putExtra("JOB_DATA", job);

        startActivity(intent);
    }
}
