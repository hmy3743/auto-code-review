package com.myhan.autocodereview;

import com.myhan.autocodereview.model.Config;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.IOException;

@Configuration
public class BeanRepository {
    private GitHubClient mGitHubClient;
    private IssueService mIssueService;
    private Config mConfig;

    BeanRepository() throws IOException {
        String OAuth = System.getenv("github-oauth");

        mGitHubClient = new GitHubClient();
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
