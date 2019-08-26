package com.school.foocommend.message.vo;

import lombok.Data;

@Data
public class OutputMessage {

    private String from;
    private String text;
    private String time;

    public OutputMessage(final String from, final String text, final String time) {

        this.from = from;
        this.text = text;
        this.time = time;
    }

    // Constructor
    // Getters and Setters
}
