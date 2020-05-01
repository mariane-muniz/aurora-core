package com.omni.aurora.core.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FarmMessageData {
    private String content;
    private String type;
    private String action;
}