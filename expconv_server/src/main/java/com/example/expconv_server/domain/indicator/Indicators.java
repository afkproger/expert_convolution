package com.example.expconv_server.domain.indicator;

public class Indicators {
    private Long id;
    private String indicator;
    private String question;

    public Indicators(Long id, String indicator, String question) {
        this.id = id;
        this.indicator = indicator;
        this.question = question;
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
