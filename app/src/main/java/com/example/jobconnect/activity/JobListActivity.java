package com.example.jobconnect.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar; // Thêm ProgressBar để chờ load

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jobconnect.R;
import com.example.jobconnect.adapters.JobAdapter;
import com.example.jobconnect.database.AppDatabase;
import com.example.jobconnect.models.Job;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JobListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private JobAdapter jobAdapter;
    private AppDatabase db;
    private ProgressBar progressBar; // Thêm ProgressBar

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Dùng layout activity_joblist.xml của bạn
        setContentView(R.layout.activity_joblist);

        // Ánh xạ
        // **SỬA Ở ĐÂY:** Dùng ID từ file XML của bạn
        recyclerView = findViewById(R.id.rv_job_list);

        // **XÓA Ở ĐÂY:** Dòng này bị xóa vì ID không tồn tại trong XML
         progressBar = findViewById(R.id.progressBar);

        // Lấy instance của Database
        // Cập nhật đường dẫn nếu bạn đã chuyển file
        db = AppDatabase.getInstance(this);

        setupRecyclerView();
        loadJobsFromDatabase();
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Khởi tạo adapter với listener
        jobAdapter = new JobAdapter(this, job -> {
            // Xử lý khi click vào một item

            // Cập nhật đường dẫn nếu bạn đã chuyển file
            Intent intent = new Intent(JobListActivity.this, JobDetailActivity.class);

            // Gửi ID của công việc qua Activity chi tiết
            intent.putExtra("JOB_ID", job.getJobId());
            startActivity(intent);
        });

        recyclerView.setAdapter(jobAdapter);
    }

    private void loadJobsFromDatabase() {
        // Hiển thị ProgressBar
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);

        // Room yêu cầu chạy truy vấn trên một thread riêng
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            // Lấy dữ liệu từ Room (background thread)
            List<Job> jobList = db.jobDao().getAllJobs();

            // Cập nhật UI trên Main thread
            runOnUiThread(() -> {
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                jobAdapter.setJobs(jobList);
            });
        });
    }


}
