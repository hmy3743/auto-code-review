package com.myhan.autocodereview;

import lombok.Getter;
import org.eclipse.egit.github.core.PullRequest;
import org.eclipse.egit.github.core.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Component
public class GithubAPIRepository {
    WebClient mWebClient;
    @Getter private PullRequest pullRequest;

    @Autowired
    GithubAPIRepository(WebClient webClient) {
        mWebClient = webClient;
    }

    public class PullRequest {
        void approve(Repository repo, org.eclipse.egit.github.core.PullRequest pr, String body) {
            Map<String, String> req = Map.of(
                    "body", body,
                    "event", "APPROVE"
            );
            //TODO approve review
//            mWebClient.post().uri(uriBuilder -> {
//                uriBuilder
//                        .path("/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}/events")
//                        .build(repo.getOwner().getLogin(), repo.getName(), pr.getNumber(), )
//            })
        }
    }
}
