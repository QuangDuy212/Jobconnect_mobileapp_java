

package com.example.jobconnect;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jobconnect.adapters.CompanyAdapter;
import com.example.jobconnect.models.CompanyModel;

import java.util.ArrayList;
import java.util.List;

public class CompanyListActivity extends AppCompatActivity {

    private RecyclerView rvCompanyList;
    private EditText etSearch;
    private ImageView btnRefresh;
    private TextView tvAll, tvIt, tvEdu, tvFinance, tvTrade;
    private CompanyAdapter adapter;
    private List<CompanyModel> companyList, allCompanies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_list);

        // Ánh xạ view
        rvCompanyList = findViewById(R.id.rv_company_list);
        etSearch = findViewById(R.id.et_search_company);
        btnRefresh = findViewById(R.id.btn_refresh);
        tvAll = findViewById(R.id.tv_filter_all);
        tvIt = findViewById(R.id.tv_filter_it);
        tvEdu = findViewById(R.id.tv_filter_edu);
        tvFinance = findViewById(R.id.tv_filter_finance);
        tvTrade = findViewById(R.id.tv_filter_trade);

        // Khởi tạo dữ liệu
        allCompanies = createSampleCompanies();
        companyList = new ArrayList<>(allCompanies);

        // Gắn adapter cho RecyclerView
        adapter = new CompanyAdapter(companyList, company -> {
            Intent intent = new Intent(CompanyListActivity.this, CompanyDetailActivity.class);
            intent.putExtra("company_data", company);
            startActivity(intent);
        });

        rvCompanyList.setLayoutManager(new LinearLayoutManager(this));
        rvCompanyList.setAdapter(adapter);

        // 🔄 Nút refresh
        btnRefresh.setOnClickListener(v -> {
            Animation rotate = AnimationUtils.loadAnimation(this, R.anim.rotate_refresh);
            v.startAnimation(rotate);

            companyList.clear();
            companyList.addAll(allCompanies);
            adapter.updateList(companyList);
            etSearch.setText(""); // Xóa ô tìm kiếm
            Toast.makeText(this, "Đã làm mới danh sách!", Toast.LENGTH_SHORT).show();
        });

        // 🔍 Tìm kiếm real-time
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String keyword = s.toString().trim();
                if (keyword.isEmpty()) {
                    adapter.updateList(allCompanies);
                } else {
                    searchCompany(keyword);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        // 🔘 Bộ lọc theo lĩnh vực
        tvAll.setOnClickListener(v -> filterByCategory("all"));
        tvIt.setOnClickListener(v -> filterByCategory("Công nghệ"));
        tvEdu.setOnClickListener(v -> filterByCategory("Giáo dục"));
        tvFinance.setOnClickListener(v -> filterByCategory("Tài chính"));
        tvTrade.setOnClickListener(v -> filterByCategory("Thương mại"));
    }

    // --- Tạo dữ liệu mẫu ---
    private List<CompanyModel> createSampleCompanies() {
        List<CompanyModel> list = new ArrayList<>();
        list.add(new CompanyModel("FPT Software", "Công nghệ", "0101234567",
                "Tòa nhà FPT, Cầu Giấy, Hà Nội", "024-1234-5678", "contact@fpt.com.vn",
                "Cung cấp dịch vụ CNTT, chuyển đổi số toàn cầu.", "AI, IoT, Cloud"));
        list.add(new CompanyModel("Viettel Group", "Công nghệ", "0107654321",
                "Số 1 Giang Văn Minh, Hà Nội", "024-5678-9999", "info@viettel.com.vn",
                "Tập đoàn viễn thông lớn nhất Việt Nam.", "Viễn thông, An ninh mạng"));
        list.add(new CompanyModel("TopCV Vietnam", "Công nghệ", "0312456789",
                "Quận Cầu Giấy, Hà Nội", "0987-654-321", "hello@topcv.vn",
                "Nền tảng tuyển dụng hàng đầu Việt Nam.", "Website tuyển dụng"));
        list.add(new CompanyModel("Hanoi Academy", "Giáo dục", "0123456789",
                "Dịch Vọng Hậu, Hà Nội", "024-8888-9999", "info@hanoiacademy.edu.vn",
                "Trường song ngữ quốc tế.", "Giảng dạy Anh-Việt"));
        list.add(new CompanyModel("VNU University", "Giáo dục", "0112233445",
                "Xuân Thủy, Cầu Giấy, Hà Nội", "024-1234-0000", "contact@vnu.edu.vn",
                "Đại học Quốc gia Hà Nội.", "Đào tạo, Nghiên cứu"));
        list.add(new CompanyModel("Techcombank", "Tài chính", "0109988776",
                "191 Bà Triệu, Hà Nội", "024-2222-8888", "support@techcombank.com.vn",
                "Ngân hàng thương mại hàng đầu Việt Nam.", "Dịch vụ ngân hàng"));
        list.add(new CompanyModel("Vietcombank", "Tài chính", "0102233445",
                "198 Trần Quang Khải, Hà Nội", "024-3824-5730", "info@vietcombank.com.vn",
                "Ngân hàng quốc doanh Việt Nam.", "Tín dụng, đầu tư"));
        list.add(new CompanyModel("VinCommerce", "Thương mại", "0106543210",
                "Times City, Hà Nội", "024-8888-6666", "info@vincommerce.vn",
                "Chuỗi siêu thị VinMart.", "Bán lẻ, hàng tiêu dùng"));
        list.add(new CompanyModel("Shopee Vietnam", "Thương mại", "0314567890",
                "TP.HCM", "028-9999-8888", "contact@shopee.vn",
                "Sàn thương mại điện tử phổ biến.", "Mua bán online"));
        return list;
    }

    // --- Hàm tìm kiếm theo tên hoặc lĩnh vực ---
    private void searchCompany(String keyword) {
        List<CompanyModel> filteredList = new ArrayList<>();
        for (CompanyModel c : allCompanies) {
            if (c.getName().toLowerCase().contains(keyword.toLowerCase())
                    || c.getField().toLowerCase().contains(keyword.toLowerCase())) {
                filteredList.add(c);
            }
        }

        if (filteredList.isEmpty()) {
            Toast.makeText(this, "Không tìm thấy công ty phù hợp!", Toast.LENGTH_SHORT).show();
        }

        adapter.updateList(filteredList);
    }

    // --- Hàm lọc theo lĩnh vực ---
    private void filterByCategory(String category) {
        List<CompanyModel> filtered = new ArrayList<>();
        if (category.equals("all")) {
            filtered.addAll(allCompanies);
        } else {
            for (CompanyModel c : allCompanies) {
                if (c.getField().equalsIgnoreCase(category)) {
                    filtered.add(c);
                }
            }
        }

        adapter.updateList(filtered);
        Toast.makeText(this, "Đã lọc theo: " + category, Toast.LENGTH_SHORT).show();
    }
}

