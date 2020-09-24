package com.lambdaschool.comake.controllers;

import com.lambdaschool.comake.models.Issue;
import com.lambdaschool.comake.services.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/issues")
public class IssueController {
    @Autowired
    IssueService issueService;

    // http://localhost:2019/issues/issues
    @GetMapping(value = "/issues",
            produces = {"application/json"})
    public ResponseEntity<?> listAllIssues(HttpServletRequest request) {
        List<Issue> myIssues = IssueService.findAllIssues();
        return new ResponseEntity<>(myIssues,
                HttpStatus.OK);
    }

    // http://localhost:2019/issuess/issue/{issueId}
    @GetMapping(value = "/issue/{issueId}",
            produces = {"application/json"})
    public ResponseEntity<?> getIssueById(HttpServletRequest request,
                                          @PathVariable
                                                  Long issueId) {
        Issue s = issueService.findIssuesById(issueId);
        return new ResponseEntity<>(s,
                HttpStatus.OK);
    }

    // POST http://localhost:2019/issues/issue
    @PostMapping(value = "/issue", consumes = "application/json")
    public ResponseEntity<?> addNewIssue(@Valid @RequestBody Issue newIssue) throws
            URISyntaxException {
        newIssue.setIssueid(0);
        newIssue = issueService.save(newIssue);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newIssueURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{issueid}")
                .buildAndExpand(newIssue.getIssueid())
                .toUri();
        responseHeaders.setLocation(newIssueURI);

        return new ResponseEntity<>(null,
                responseHeaders,
                HttpStatus.CREATED);
    }

    // PUT http://localhost:2019/issues/issue/1
    @PutMapping(value = "/issue/{issueid}",
            consumes = "application/json")
    public ResponseEntity<?> updateFullIssue(
            @Valid
            @RequestBody
                    Issue updateIssue,
            @PathVariable
                    long issueid) {
        updateIssue.setIssueid(issueid);
        issueService.save(updateIssue);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // DELETE http://localhost:2019/issues/issue/1
    @DeleteMapping(value = "/issue/{id}")
    public ResponseEntity<?> deleteIssueById(
            @PathVariable
                    long id) {
        issueService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}