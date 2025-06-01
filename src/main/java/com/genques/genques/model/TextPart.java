package com.genques.genques.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TextPart {
    private String text;

    public TextPart() {}
    public TextPart(String text) {
        this.text = text;
    }

}
