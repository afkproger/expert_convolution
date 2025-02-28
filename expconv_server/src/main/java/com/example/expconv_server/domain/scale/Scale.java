package com.example.expconv_server.domain.scale;

import com.example.expconv_server.domain.task.Task;
import jakarta.persistence.*;

@Entity
@Table(name = "scale")
public class Scale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 255, nullable = false)
    private String grade;

    @Column(length = 255, nullable = false)
    private String weight;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Scale{" +
                "id=" + id +
                ", grade='" + grade + '\'' +
                ", weight='" + weight + '\'' +
                ", task=" + task +
                '}';
    }
}
