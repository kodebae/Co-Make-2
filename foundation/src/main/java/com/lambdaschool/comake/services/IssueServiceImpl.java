package com.lambdaschool.comake.services;

import com.lambdaschool.comake.exceptions.ResourceNotFoundException;
import com.lambdaschool.comake.models.Issue;
import com.lambdaschool.comake.repository.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.PatternSyntaxException;

@Transactional
@Service(value = "issueService")
public class IssueServiceImpl
        implements IssueService
{
    @Autowired
    UserAuditing userAuditing;

    @Autowired
   private IssueRepository issuerepos;

    @Override
    public List<Issue> findAllIssues()
    {
        List<Issue> list = new ArrayList<>();
        issuerepos.findAll()
                .iterator()
                .forEachRemaining(list::add);
        return list;
    }

    @Override
    public Issue findIssuesById(long id)
    {
        return issuerepos.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Issue with id " + id + " Not Found!"));
    }

    @Transactional
    @Override
    public void delete(long id)
    {
        if (issuerepos.findById(id)
                .isPresent())
        {
            issuerepos.deleteById(id);
        } else
        {
            throw new ResourceNotFoundException("Issue with id " + id + " Not Found!");
        }
    }

    @Transactional
    @Override
    public Issue save(Issue issue)
    {
        Issue newBook = new Issue();

        if (issue.getIssueid() != 0)
        {
            issuerepos.findById(issue.getIssueid())
                    .orElseThrow(() -> new ResourceNotFoundException("Issue id " + issue.getIssueid() + " not found!"));
        }

        newIssue.setDescription(issue.getDescription());
        if (issue.getDescription() != null)
        {
            newBook.setDescription(descriptionService.findDescriptionById(issue.getDescription()
                    .getDescriptionid()));
        }
        return issuerepos.save(newIssue);
    }

    @Transactional
    @Override
    public Issue update(Issue issue,
                       long id)
    {
        Issue currentIssue = findIssueById(id);

        if (issue.getDescription() != null)
        {
            currentIssue.setDescription(descriptionService.findDescriptionById(issue.getDescription()
                    .getDescriptionid()));
        }

        return issuerepos.save(currentIssue);
    }

    @Transactional
    @Override
    public void deleteAll()
    {
        issuerepos.deleteAll();
    }
}
