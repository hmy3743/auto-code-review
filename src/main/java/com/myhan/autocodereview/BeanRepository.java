package com.myhan.autocodereview;

import com.myhan.autocodereview.model.Config;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.IssueService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class BeanRepository {
    private GitHubClient mGitHubClient;
    private IssueService mIssueService;
    private Config mConfig;

    BeanRepository() throws IOException {
        String OAuth = System.getenv("github_oauth");
        System.out.println(OAuth);
        String hostname = System.getenv("github_host_url");

        if(hostname == null)
            mGitHubClient = new GitHubClient();
        else
            mGitHubClient = new GitHubClient(hostname);

        mGitHubClient.setOAuth2Token(
                OAuth
        );

        mIssueService = new IssueService();
        mIssueService.getClient().setOAuth2Token(
                OAuth
        );
    }

    @Bean
    public IssueService issueService() {
        return mIssueService;
    }
}
