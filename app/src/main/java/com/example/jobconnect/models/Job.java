package com.example.jobconnect.models; // (Hoặc tên package của bạn)

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "jobs") // Tên bảng giống trong ảnh database của bạn
public class Job {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "job_id") // Tên cột giống trong ảnh
    private int jobId;

    @ColumnInfo(name = "job_title")
    private String jobTitle;

    @ColumnInfo(name = "company_name")
    private String companyName;

    @ColumnInfo(name = "location")
    private String location;

    @ColumnInfo(name = "salary")
    private String salary;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "requirements")
    private String requirements;

    @ColumnInfo(name = "post_date")
    private String postDate;

    @ColumnInfo(name = "company_logo_url") // Giả sử bạn có cột này
    private String companyLogoUrl;

    // --- Constructor ---
    // Room cần một constructor rỗng
    public Job() {}

    // Bạn có thể giữ constructor cũ để tạo đối tượng (ví dụ: để chèn dữ liệu mẫu)
    public Job(String jobTitle, String companyName, String location, String salary, String description, String requirements, String postDate, String companyLogoUrl) {
        this.jobTitle = jobTitle;
        this.companyName = companyName;
        this.location = location;
        this.salary = salary;
        this.description = description;
        this.requirements = requirements;
        this.postDate = postDate;
        this.companyLogoUrl = companyLogoUrl;
    }

    // --- Getters ---
    public int getJobId() { return jobId; }
    public String getJobTitle() { return jobTitle; }
    public String getCompanyName() { return companyName; }
    public String getLocation() { return location; }
    public String getSalary() { return salary; }
    public String getDescription() { return description; }
    public String getRequirements() { return requirements; }
    public String getPostDate() { return postDate; }
    public String getCompanyLogoUrl() { return companyLogoUrl; }

    // --- Setters ---
    // Room cần setters để gán giá trị khi đọc từ database
    public void setJobId(int jobId) { this.jobId = jobId; }
    public void setJobTitle(String jobTitle) { this.jobTitle = jobTitle; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }
    public void setLocation(String location) { this.location = location; }
    public void setSalary(String salary) { this.salary = salary; }
    public void setDescription(String description) { this.description = description; }
    public void setRequirements(String requirements) { this.requirements = requirements; }
    public void setPostDate(String postDate) { this.postDate = postDate; }
    public void setCompanyLogoUrl(String companyLogoUrl) { this.companyLogoUrl = companyLogoUrl; }
}
