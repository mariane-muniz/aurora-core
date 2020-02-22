package com.omni.aurora.core.repository;

import com.omni.aurora.core.model.ApplicationUser;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "aurora", itemResourceRel = "auroras")
public interface ApplicationUserRepository extends PagingAndSortingRepository<ApplicationUser, Long> {
    ApplicationUser findByUsername(final String username);
}