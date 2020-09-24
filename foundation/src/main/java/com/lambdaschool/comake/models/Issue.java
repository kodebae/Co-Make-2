package com.lambdaschool.comake.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;



@Entity
@Table(name = "issue") // this creates a table
@JsonIgnoreProperties(value = "hasvalueforcopy")
public class Issue
        extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long issueid;

    private String description;

    @ManyToOne
    @JoinColumn(name = "issueid")
    @JsonIgnoreProperties("issues")
    private Issue issue;

    @OneToMany(mappedBy = "issue",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonIgnoreProperties("issue")
    private Set<Issue> Issues = new HashSet<>();

    public Issue()
    {
    }

    public void Issue(String description
            )
    {
        this.description = description;

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    public Set<Issue> getIssues() {
        return Issues;
    }

    public void setIssues(Set<Issue> issues) {
        Issues = issues;
    }
}