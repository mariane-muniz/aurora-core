package com.omni.aurora.core.repository;

import com.omni.aurora.core.model.Aurora;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "aurora", itemResourceRel = "auroras")
public interface AuroraRepository extends PagingAndSortingRepository<Aurora, Long> {
    List<Aurora> findAllByCode(final String code);
}
