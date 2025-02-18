package com.example.expconv_server.domain.indicator;


import com.example.expconv_server.domain.scale.Scale;
import jakarta.persistence.*;

@Entity
@Table(name = "indicators")
public class Indicators {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 255, nullable = false)
    private String indicator;

    @Column(length = 255, nullable = false)
    private String question;

    @ManyToOne
    @JoinColumn(name = "scale_id")
    private Scale scale;

    public Scale getScale() {
        return scale;
    }

    public void setScale(Scale scale) {
        this.scale = scale;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIndicator() {
        return indicator;
    }

    public void setIndicator(String indicator) {
        this.indicator = indicator;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
