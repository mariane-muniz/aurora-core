package com.omni.aurora.core.model;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface CustomPagingAndSortingRepository<S, T> extends PagingAndSortingRepository<S, T> {
    Optional<Object> findOneByCode(final String code);
}