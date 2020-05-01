package com.omni.aurora.core.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntityStructureEntryData {
    private String name;
    private boolean optional;
    private String type;
}