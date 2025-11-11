package com.example.jobconnect.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.example.jobconnect.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CustomerSupportActivity extends AppCompatActivity {

    private LinearLayout layoutChat, layoutFAQ, layoutContact;
    private Button btnChat, btnFAQ, btnContact;
    private Button btnSendMessage, btnCallHotline, btnSendEmail;
    private EditText edtMessage;
    private LinearLayout chatMessagesContainer;
    private ScrollView chatScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_support);

        // Setup Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(R.string.support_title);
        }

        // Ánh xạ views
        initViews();

        // Mặc định hiển thị Chat
        showSection("chat");

        // Xử lý sự kiện
        setupListeners();
    }

    private void initViews() {
        // Tabs
        btnChat = findViewById(R.id.btnChat);
        btnFAQ = findViewById(R.id.btnFAQ);
        btnContact = findViewById(R.id.btnContact);

        // Sections
        layoutChat = findViewById(R.id.layoutChat);
//        layoutFAQ = findViewById(R.id.layoutFAQ);
//        layoutContact = findViewById(R.id.layoutContact);

        // Chat elements
        edtMessage = findViewById(R.id.edtMessage);
        btnSendMessage = findViewById(R.id.btnSendMessage);
        chatMessagesContainer = findViewById(R.id.chatMessagesContainer);
        chatScrollView = findViewById(R.id.chatScrollView);

        // Contact elements
        btnCallHotline = findViewById(R.id.btnCallHotline);
        btnSendEmail = findViewById(R.id.btnSendEmail);
    }

    private void setupListeners() {
        // Tab buttons
        btnChat.setOnClickListener(v -> showSection("chat"));
        btnFAQ.setOnClickListener(v -> showSection("faq"));
        btnContact.setOnClickListener(v -> showSection("contact"));

        // Chat send message
        btnSendMessage.setOnClickListener(v -> sendMessage());

        // Contact buttons
        btnCallHotline.setOnClickListener(v -> callHotline());
        btnSendEmail.setOnClickListener(v -> sendEmailSupport());

        // FAQ items click listeners
        setupFAQListeners();
    }

    private void showSection(String section) {
        // Ẩn tất cả
        layoutChat.setVisibility(View.GONE);
        layoutFAQ.setVisibility(View.GONE);
        layoutContact.setVisibility(View.GONE);

        // Reset button states
        btnChat.setBackgroundColor(getResources().getColor(R.color.tab_inactive));
        btnFAQ.setBackgroundColor(getResources().getColor(R.color.tab_inactive));
        btnContact.setBackgroundColor(getResources().getColor(R.color.tab_inactive));

        // Hiển thị section được chọn
        switch (section) {
            case "chat":
                layoutChat.setVisibility(View.VISIBLE);
                btnChat.setBackgroundColor(getResources().getColor(R.color.tab_active));
                break;
            case "faq":
                layoutFAQ.setVisibility(View.VISIBLE);
                btnFAQ.setBackgroundColor(getResources().getColor(R.color.tab_active));
                break;
            case "contact":
                layoutContact.setVisibility(View.VISIBLE);
                btnContact.setBackgroundColor(getResources().getColor(R.color.tab_active));
                break;
        }
    }

    private void sendMessage() {
        String message = edtMessage.getText().toString().trim();

        if (message.isEmpty()) {
            Toast.makeText(this, getString(R.string.error_message_empty), Toast.LENGTH_SHORT).show();
            return;
        }

        // Thêm tin nhắn của user vào chat
        addUserMessage(message);

        // Clear input
        edtMessage.setText("");

        // Giả lập phản hồi tự động sau 1 giây
        chatMessagesContainer.postDelayed(() -> {
            addBotMessage("Cảm ơn bạn đã liên hệ! Chúng tôi đã nhận được tin nhắn và sẽ phản hồi trong thời gian sớm nhất.");
        }, 1000);
    }

    private void addUserMessage(String message) {
        View messageView = getLayoutInflater().inflate(R.layout.item_chat_user, chatMessagesContainer, false);
        TextView tvMessage = messageView.findViewById(R.id.tvMessage);
        TextView tvTime = messageView.findViewById(R.id.tvTime);

        tvMessage.setText(message);
        tvTime.setText(getCurrentTime());

        chatMessagesContainer.addView(messageView);
        scrollToBottom();
    }

    private void addBotMessage(String message) {
        View messageView = getLayoutInflater().inflate(R.layout.item_chat_bot, chatMessagesContainer, false);
        TextView tvMessage = messageView.findViewById(R.id.tvMessage);
        TextView tvTime = messageView.findViewById(R.id.tvTime);

        tvMessage.setText(message);
        tvTime.setText(getCurrentTime());

        chatMessagesContainer.addView(messageView);
        scrollToBottom();
    }

    private void scrollToBottom() {
        chatScrollView.post(() -> chatScrollView.fullScroll(View.FOCUS_DOWN));
    }

    private String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
        return sdf.format(new Date());
    }

    private void callHotline() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + getString(R.string.contact_hotline_number)));
        startActivity(intent);
    }

    private void sendEmailSupport() {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:" + getString(R.string.contact_email_address)));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Yêu cầu hỗ trợ khách hàng");
        intent.putExtra(Intent.EXTRA_TEXT, "Xin chào,\n\nTôi cần hỗ trợ về:\n\n");

        try {
            startActivity(Intent.createChooser(intent, "Gửi email qua"));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "Không tìm thấy ứng dụng email", Toast.LENGTH_SHORT).show();
        }
    }

    private void setupFAQListeners() {
        // Có thể thêm expand/collapse cho FAQ items
        CardView faqCard1 = findViewById(R.id.faqCard1);
        CardView faqCard2 = findViewById(R.id.faqCard2);
        CardView faqCard3 = findViewById(R.id.faqCard3);
        CardView faqCard4 = findViewById(R.id.faqCard4);
        CardView faqCard5 = findViewById(R.id.faqCard5);

        // Click để copy câu trả lời hoặc mở rộng
        View.OnClickListener faqClickListener = v -> {
            Toast.makeText(this, "FAQ được chọn", Toast.LENGTH_SHORT).show();
        };

        if (faqCard1 != null) faqCard1.setOnClickListener(faqClickListener);
        if (faqCard2 != null) faqCard2.setOnClickListener(faqClickListener);
        if (faqCard3 != null) faqCard3.setOnClickListener(faqClickListener);
        if (faqCard4 != null) faqCard4.setOnClickListener(faqClickListener);
        if (faqCard5 != null) faqCard5.setOnClickListener(faqClickListener);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}