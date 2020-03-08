package com.myhan.autocodereview.controller;

import com.myhan.autocodereview.model.GithubWebHook;
import org.eclipse.egit.github.core.CommitComment;
import org.eclipse.egit.github.core.RepositoryId;
import org.eclipse.egit.github.core.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;

@Controller
@RequestMapping("/webhook")
@ResponseStatus(HttpStatus.OK)
public class WebhookController {
    private IssueService mIssueService;

    @Autowired
    WebhookController(IssueService issueService){
        mIssueService = issueService;
    }

    @RequestMapping("github")
    public void github(@RequestBody GithubWebHook body) throws IOException {
        System.out.println(body.getAction());
        if (!(
                body.getAction().equals("reopened") || body.getAction().equals("opened")
        ))
            return;

        GithubWebHook.PullRequest.Head.Repo repo = body.getPull_request().getHead().getRepo();

        RepositoryId repoId = new RepositoryId(repo.getOwner().getLogin(), repo.getName());
        System.out.println(repoId.toString());
        mIssueService.createComment(repoId, body.getNumber(), "LGTM");
    }
}
