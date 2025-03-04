package com.example.expconv_server.web.dto.indicator;

public class IndicatorDto {
    private String indicator;
    private String question;

    @Override
    public String toString() {
        return "IndicatorDto{" +
                "indicator='" + indicator + '\'' +
                ", question='" + question + '\'' +
                '}';
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
