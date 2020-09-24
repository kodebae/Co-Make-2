package com.lambdaschool.comake.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "description") // creates a table
public class Description
        extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long descriptionid;

    private String name;

    @OneToMany(mappedBy = "description")
    @JsonIgnoreProperties("description")
    private Set<Issue> issues = new HashSet<>();

    public Description() {
    }

    public Description(String name) {
        this.name = name;
    }

    public long getDescriptionid() {
        return descriptionid;
    }

    public void setSectionid(long descriptionid) {
        this.descriptionid = descriptionid;
    }

    public Set<Issue> getIssues() {
        return issues;
    }

    public void setBooks(Set<Issue> issues) {
        this.issues = issues;
    }
}