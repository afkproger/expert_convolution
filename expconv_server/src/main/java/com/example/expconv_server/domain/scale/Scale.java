package com.example.expconv_server.domain.scale;

public class Scale {
    private Long id;
    private String grade;
    private String weight;

    public Scale(Long id, String grade, String weight) {
        this.id = id;
        this.grade = grade;
        this.weight = weight;
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
}
