package com.example.jobconnect; // (Hoặc tên package của bạn)

import java.io.Serializable;

// Cần implements Serializable để có thể gửi đối tượng Job qua Intent
public class Job implements Serializable {

    // Các trường này dựa trên 2 file layout của bạn
    private long id;
    private String title;
    private String companyName;
    private String salary;
    private String location;
    private String jobType;
    private String description;
    private String requirements;
    private String benefits;
    private int companyLogoResId; // Dùng int để tham chiếu đến 1 ảnh trong drawable

    // Constructor để tạo dữ liệu mẫu
    public Job(long id, String title, String companyName, String salary, String location, String jobType, String description, String requirements, String benefits, int companyLogoResId) {
        this.id = id;
        this.title = title;
        this.companyName = companyName;
        this.salary = salary;
        this.location = location;
        this.jobType = jobType;
        this.description = description;
        this.requirements = requirements;
        this.benefits = benefits;
        this.companyLogoResId = companyLogoResId;
    }

    // Getters cho tất cả các trường
    public long getId() { return id; }
    public String getTitle() { return title; }
    public String getCompanyName() { return companyName; }
    public String getSalary() { return salary; }
    public String getLocation() { return location; }
    public String getJobType() { return jobType; }
    public String getDescription() { return description; }
    public String getRequirements() { return requirements; }
    public String getBenefits() { return benefits; }
    public int getCompanyLogoResId() { return companyLogoResId; }
}
