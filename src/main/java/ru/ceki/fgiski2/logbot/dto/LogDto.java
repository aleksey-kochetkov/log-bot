package ru.ceki.fgiski2.logbot.dto;

import java.sql.Timestamp;

public class LogDto {
    private Long id;
    private Timestamp createdAt;
    private String userStr;
    private String description;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getUserStr() {
        return this.userStr;
    }

    public void setUserStr(String userStr) {
        this.userStr = userStr;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
