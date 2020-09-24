package com.lambdaschool.comake.services;

import com.lambdaschool.comake.models.Issue;

import java.util.List;

public interface IssueService
{
    List<Issue> findAllIssues();

    Issue findIssuesById(long id);

    void delete(long id);

    Issue save(Issue role);

    Issue update(Issue role,
                long id);

    void deleteAll();
}
