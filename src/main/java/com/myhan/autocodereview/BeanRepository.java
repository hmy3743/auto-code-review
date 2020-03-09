package com.myhan.autocodereview;

import com.myhan.autocodereview.model.Config;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;

@Configuration
public class BeanRepository {
    private GitHubClient mGitHubClient;
    private UserService mUserService;
    private OrganizationService mOrganizationService;
    private RepositoryService mRepositoryService;
    private PullRequestService mPullRequestService;
    private IssueService mIssueService;
    private Config mConfig;
    private WebClient mWebClient;

    BeanRepository() throws IOException {
        String OAuth = System.getenv("github_oauth");
        System.out.println(String.format("OAuth: %s", OAuth));
        String hostname = System.getenv("github_host_url");
        System.out.println(String.format("Hostname: %s", hostname));
        String organization = System.getenv("github_organization");
        System.out.println(String.format("Organization: %s", organization));

        mConfig = new Config();
        mConfig.setOauth(OAuth);
        mConfig.setHostname(hostname);
        mConfig.setOrganization(organization);

        if(hostname == null)
            mGitHubClient = new GitHubClient();
        else
            mGitHubClient = new GitHubClient(hostname);

        mGitHubClient.setOAuth2Token(
                OAuth
        );

        mUserService = new UserService(mGitHubClient);
        mOrganizationService = new OrganizationService(mGitHubClient);
        mRepositoryService = new RepositoryService(mGitHubClient);
        mPullRequestService = new PullRequestService(mGitHubClient);
        mIssueService = new IssueService(mGitHubClient);

        mWebClient = WebClient
                .builder()
                .baseUrl("https://api.github.sec.samsung.net")
                .defaultHeader("Authorization", String.format("token %s", mConfig.getOauth()))
                .build();
    }

    @Bean
    public UserService userService() {
        return mUserService;
    }
    @Bean
    public OrganizationService organizationService() {
        return mOrganizationService;
    }
    @Bean
    public RepositoryService repositoryService() {
        return mRepositoryService;
    }
    @Bean
    public PullRequestService pullRequestService() {
        return mPullRequestService;
    }
    @Bean
    public IssueService issueService() {
        return mIssueService;
    }
    @Bean
    public Config config() { return mConfig; }
    @Bean
    public WebClient webClient() {
        return mWebClient;
    }
}
