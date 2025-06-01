package com.genques.genques.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContentRequest {
    private List<PartWrapper> contents;

    public ContentRequest() {}
    public ContentRequest(List<PartWrapper> contents) {
        this.contents = contents;
    }

}
