package com.example.expconv_server.web.dto.scale;

public class ScaleDto {
    private String grade;
    private String weight;

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
        return "ScaleDto{" +
                "grade='" + grade + '\'' +
                ", weight='" + weight + '\'' +
                '}';
    }
}
