package com.sjl.communitytest.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sjl.communitytest.dto.AccessTokenProvider;
import com.sjl.communitytest.dto.GitHubUser;
import com.sjl.communitytest.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @description: 用户使用github授权登录
 * @author: Mr.S
 * @create: 2019-08-04 17:41
 **/
@Controller
public class OauthController
{
    @Autowired
    private GithubProvider provider;
    /**github申请的
     */
    @Value("${github.client.id}")
    private String clientId;
    /**github申请的
     */
    @Value("${github.client.secret}")
    private String clientSecret;
    /**github返回的code的回调
     */
    @Value("${github.Redirect.uri}")
    private String redirectUri;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state)
    {
        AccessTokenProvider tokenProvider = new AccessTokenProvider();
        tokenProvider.setClient_id(clientId);
        tokenProvider.setClient_secret(clientSecret);
        tokenProvider.setCode(code);
        tokenProvider.setRedirect_uri(redirectUri);
        tokenProvider.setState(state);

        try {
            String accessToken = provider.getAccessToken(tokenProvider);

            GitHubUser user = provider.getUser(accessToken);

            System.out.println(user.getName());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "index";
    }
}
