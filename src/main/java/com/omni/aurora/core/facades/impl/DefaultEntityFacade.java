package com.omni.aurora.core.facades.impl;

import com.omni.aurora.core.converters.EntityStructureConverter;
import com.omni.aurora.core.dto.EntityStructureData;
import com.omni.aurora.core.facades.EntityFacade;

import com.omni.aurora.core.services.EntityCatalogService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.metamodel.EntityType;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Component("entityFacade")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DefaultEntityFacade implements EntityFacade {
    private final EntityCatalogService entityCatalogService;
    private final EntityStructureConverter entityStructureConverter;

    @Override
    public List<EntityStructureData> getEntityStructures() {
        final Set<EntityType<?>> structures = this.entityCatalogService.getEntityStructures();
        final List<EntityType<?>> list = new LinkedList<>(structures);
        return this.entityStructureConverter.convertAll(list);
    }
}