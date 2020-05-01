package com.omni.aurora.core.facades;

import com.omni.aurora.core.dto.EntityStructureData;

import java.util.List;

public interface EntityFacade {
    List<EntityStructureData> getEntityStructures();
}