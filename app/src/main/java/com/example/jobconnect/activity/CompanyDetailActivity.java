

package com.example.jobconnect.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.jobconnect.R;
import com.example.jobconnect.models.CompanyModel;

public class CompanyDetailActivity extends AppCompatActivity {

    private TextView tvName, tvField, tvTax, tvAddress, tvPhone, tvEmail, tvDescription, tvProducts;
    private ImageView btnBack;
    private Button btnBackList, btnContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_detail);

        // Ánh xạ view
        tvName = findViewById(R.id.tv_company_name);
        tvField = findViewById(R.id.tv_company_field);
        tvTax = findViewById(R.id.tv_company_tax);
        tvAddress = findViewById(R.id.tv_company_address);
        tvPhone = findViewById(R.id.tv_company_phone);
        tvEmail = findViewById(R.id.tv_company_email);
        tvDescription = findViewById(R.id.tv_company_description);
        tvProducts = findViewById(R.id.tv_company_products);
        btnBack = findViewById(R.id.btn_back);
        btnBackList = findViewById(R.id.btn_back_to_list);
        btnContact = findViewById(R.id.btn_contact);

        // Lấy dữ liệu từ Intent
        CompanyModel company = (CompanyModel) getIntent().getSerializableExtra("company_data");
        if (company != null) {
            tvName.setText(company.getName());
            tvField.setText("Lĩnh vực: " + company.getField());
            tvTax.setText("Mã số thuế: " + company.getTax());
            tvAddress.setText("Địa chỉ: " + company.getAddress());
            tvPhone.setText("SĐT: " + company.getPhone());
            tvEmail.setText("Email: " + company.getEmail());
            tvDescription.setText(company.getDescription());
            tvProducts.setText(company.getProducts());
        } else {
            Toast.makeText(this, "Không có dữ liệu công ty!", Toast.LENGTH_SHORT).show();
            finish();
        }

        btnBack.setOnClickListener(v -> onBackPressed());
        btnBackList.setOnClickListener(v -> onBackPressed());

        btnContact.setOnClickListener(v ->
                Toast.makeText(this, "Đã gửi yêu cầu liên hệ đến " + company.getName(), Toast.LENGTH_SHORT).show());
    }
}

