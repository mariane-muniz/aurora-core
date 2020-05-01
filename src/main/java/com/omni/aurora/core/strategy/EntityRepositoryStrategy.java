package com.omni.aurora.core.strategy;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.support.Repositories;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EntityRepositoryStrategy {

    private final static String MODEL_PACKAGE = "com.store.catalog.models.";
    private final Repositories repositories;

    public Optional<Object> findRepository(final String entityCode) throws ClassNotFoundException {
        final String entityName = MODEL_PACKAGE + StringUtils.capitalize(entityCode);
        final Class<?> type = Class.forName(entityName);
        return this.repositories.getRepositoryFor(type);
    }
}