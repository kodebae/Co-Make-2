package com.lambdaschool.comake.repository;

import com.lambdaschool.comake.models.Issue;
import org.springframework.data.repository.CrudRepository;

public interface IssueRepository
        extends CrudRepository<Issue, Long> {
}
