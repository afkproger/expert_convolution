package com.example.expconv_server.domain.task;

import com.example.expconv_server.domain.indicator.Indicator;
import com.example.expconv_server.domain.scale.Scale;
import com.example.expconv_server.domain.user.User;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime expirationDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "task" , cascade = CascadeType.ALL, orphanRemoval = true )
    @JsonManagedReference
    private List<Indicator> indicators;

    @OneToMany(mappedBy = "task" , cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Scale> scale;

    public List<Scale> getScale() {
        return scale;
    }

    public void setScale(List<Scale> scale) {
        this.scale = scale;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Indicator> getIndicators() {
        return indicators;
    }

    public void setIndicators(List<Indicator> indicators) {
        this.indicators = indicators;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", expirationDate=" + expirationDate +
                ", user=" + user +
                ", indicators=" + indicators +
                ", scales=" + scale +
                '}';
    }
}
