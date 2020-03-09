package com.myhan.autocodereview;

import com.myhan.autocodereview.model.Config;
import org.eclipse.egit.github.core.Comment;
import org.eclipse.egit.github.core.PullRequest;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Random;

@Service
public class PullRequestCheckService {
    private UserService mUserService;
    private OrganizationService mOrganizationService;
    private RepositoryService mRepositoryService;
    private PullRequestService mPullRequestService;
    private IssueService mIssueService;
    private Config mConfig;
    private Random mRandom = new Random();

    private List<String> mComments = List.of(
            "LGTM",
            "ACK",
            "utACK",
            "좋습니다",
            "수고하셨어요"
    );

    @Autowired
    PullRequestCheckService(
            UserService userService,
            OrganizationService organizationService,
            RepositoryService repositoryService,
            PullRequestService pullRequestService,
            IssueService issueService,
            Config config
    ) {
        mUserService = userService;
        mOrganizationService = organizationService;
        mRepositoryService = repositoryService;
        mPullRequestService = pullRequestService;
        mIssueService = issueService;
        mConfig = config;
    }


    @Scheduled(cron = "0 0/5 * * * *")
    public void Check() throws IOException {
        int selfId = mUserService.getUser().getId();

        for(Repository repo: mRepositoryService.getRepositories()) {
            for (PullRequest pr: mPullRequestService.getPullRequests(repo, "open")) {
                long selfCommentCount = mIssueService
                        .getComments(repo, pr.getNumber())
                        .stream()
                        .map((Comment comment) -> { return comment.getUser().getId(); })
                        .filter((Integer id) -> { return id.equals(selfId); })
                        .count();
                if(selfCommentCount == 0) {
                    System.out.println(pr.getHtmlUrl());
                    //TODO approve review
                    mIssueService.createComment(repo, pr.getNumber(), mComments.get(mRandom.nextInt(mComments.size())));
                }
            }
        }
    }
}
