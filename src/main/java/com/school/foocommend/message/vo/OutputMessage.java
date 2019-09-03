package com.school.foocommend.message.vo;

import lombok.Data;

@Data
public class OutputMessage {

    private String from;
    private String text;
    private String time;
    private String profileImg;

    public OutputMessage(final String from, final String text, final String time, final String profileImg) {

        this.from = from;
        this.text = text;
        this.time = time;
        this.profileImg = profileImg;
    }

    // Constructor
    // Getters and Setters
}
