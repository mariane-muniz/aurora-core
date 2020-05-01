package com.omni.aurora.core.services;

import com.omni.aurora.core.dto.FarmMessageData;

import javax.persistence.metamodel.EntityType;
import java.util.List;
import java.util.Set;

public interface EntityCatalogService {

    Set<EntityType<?>> getEntityStructures();
    List<Object> getEntityRepository(final String entityName);
    long countEntityRepository(final String entityName);
    void saveEntity(FarmMessageData farmMessageData) throws ClassNotFoundException;
    void deleteEntity(FarmMessageData farmMessageData);
}