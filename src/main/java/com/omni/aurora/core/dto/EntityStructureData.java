package com.omni.aurora.core.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EntityStructureData {
    private String name;
    private String code;
    private List<EntityStructureEntryData> entries;
}