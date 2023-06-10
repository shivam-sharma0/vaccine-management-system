package com.example.vaccineManagementSystem.DTO;

public class UpdateEmail {
    private int userId;
    private String emailId;

    public UpdateEmail(int userId, String emailId) {
        this.userId = userId;
        this.emailId = emailId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
}
