package com.omni.aurora.core.services.impl;

import java.util.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.omni.aurora.core.dto.FarmMessageData;
import com.omni.aurora.core.model.AbstractAudit;
import com.omni.aurora.core.model.CustomPagingAndSortingRepository;
import com.omni.aurora.core.services.EntityCatalogService;
import com.omni.aurora.core.services.EntityRepositoryIndex;

import com.omni.aurora.core.strategy.EntityRepositoryStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

@Slf4j
@Component("entityCatalogService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DefaultEntityCatalogService implements EntityCatalogService {

    @Autowired
    private ApplicationContext context;
    private final EntityRepositoryStrategy entityRepositoryStrategy;
    private final EntityManager entityManager;
    private final static String MODEL_PACKAGE = "com.store.catalog.models.";

    @Override
    public Set<EntityType<?>> getEntityStructures() {
        final Metamodel meta = this.entityManager.getMetamodel();
        if (Objects.nonNull(meta)) {
            return meta.getEntities();
        }
        return null;
    }

    @Override
    public List<Object> getEntityRepository(final String entityName) {
        final CrudRepository repository = (CrudRepository) this.context.getBean(EntityRepositoryIndex.product);
        final Iterable items = repository.findAll();
        if (items.iterator().hasNext()) {
            final List<Object> target = new ArrayList<>();
            items.iterator().forEachRemaining(target::add);
            return target;
        }
        return null;
    }

    @Override
    public long countEntityRepository(final String entityName) {

        final CrudRepository repository =
                (CrudRepository) this.context.getBean(EntityRepositoryIndex.product);
        return repository.count();
    }

    @Override
    public void saveEntity(final FarmMessageData farmMessageData) throws ClassNotFoundException {
        final String entityCode = farmMessageData.getType();
        final String entityString = farmMessageData.getContent();
        final Optional<Object> repository = this.entityRepositoryStrategy.findRepository(entityCode);
        repository.ifPresent(instance -> {
            try {
                final AbstractAudit entity = this.getEntity(entityCode, entityString);
                final String code = entity.getCode();
                final Optional<Object> entityInstance = ((CustomPagingAndSortingRepository) instance).findOneByCode(code);
                if (!entityInstance.isPresent()) {
                    ((CrudRepository) instance).save(entity);
                }
                else {
                    entity.setId(((AbstractAudit) entityInstance.get()).getId());
                    ((CrudRepository) instance).save(entity);
                }
            }
            catch (ClassNotFoundException | JsonProcessingException e) {
                log.error(e.getMessage());
            }
        });
    }

    @Override
    public void deleteEntity(final FarmMessageData farmMessageData) {
        try {
            final String entityCode = farmMessageData.getType();
            final String entityString = farmMessageData.getContent();
            final AbstractAudit entityType = this.getEntity(entityCode, entityString);
            final Optional<Object> repository = this.entityRepositoryStrategy.findRepository(entityCode);
            repository.ifPresent(repo -> {
                final String code = entityType.getCode();
                final Optional entity = ((CustomPagingAndSortingRepository) repo).findOneByCode(code);
                entity.ifPresent(((CustomPagingAndSortingRepository) repo)::delete);
            });
        }
        catch (ClassNotFoundException | JsonProcessingException e) {
            log.error(e.getMessage());
        }
    }

    private AbstractAudit getEntity(final String entityCode, final String entityString)
            throws ClassNotFoundException, JsonProcessingException {
        final String entityName = MODEL_PACKAGE + StringUtils.capitalize(entityCode);
        final Class<?> type = Class.forName(entityName);
        final ObjectMapper mapper = new ObjectMapper();
        return (AbstractAudit) mapper.readValue(entityString, type);
    }
}