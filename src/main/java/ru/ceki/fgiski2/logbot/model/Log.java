package ru.ceki.fgiski2.logbot.model;

import java.sql.Timestamp;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;

@Entity
@Table(name = "logs", schema = "common")
public class Log {
    @Id
    @Column
    private Long id;
    @Column
    private String description;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "created_at")
    private Timestamp createdAt;

    public Log() {
    }

    public Log(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Timestamp getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
