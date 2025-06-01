package com.genques.genques.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PartWrapper {
    private List<TextPart> parts;

    public PartWrapper() {}
    public PartWrapper(List<TextPart> parts) {
        this.parts = parts;
    }


}
