package com.sjl.communitytest.provider;

import com.alibaba.fastjson.JSON;
import com.sjl.communitytest.dto.AccessTokenProvider;
import com.sjl.communitytest.dto.GitHubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @description: 使用okHttp的post请求获取Token
 * @author: Mr.S
 * @create: 2019-08-04 16:50
 **/
@Component
public class GithubProvider
{
     /** 获取要使用github关联社区用户的token信息
     * @param accessTokenProvider Github需要的信息
     * @return
     * @throws Exception
     */
     public String getAccessToken(AccessTokenProvider accessTokenProvider) throws Exception
     {
         MediaType mediaType = MediaType.get("application/json; charset=utf-8");

         OkHttpClient client = new OkHttpClient();

             RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenProvider));
             Request request = new Request.Builder()
                     .url("https://github.com/login/oauth/access_token")
                     .post(body)
                     .build();
             try (Response response = client.newCall(request).execute())
             {
                 String string = response.body().string();
                 String token = string.split("&")[0].split("=")[1];

                 return token;
             }catch (Exception e)
             {
                 e.printStackTrace();
             }
             return null;
     }

    /** 利用token获取用户信息
     * @param accessToken
     * @return
     */
     public GitHubUser getUser(String accessToken)
     {
         OkHttpClient client = new OkHttpClient();

         Request request = new Request.Builder()
                 .url("https://api.github.com/user?access_token=" + accessToken)
                 .build();
         try (Response response = client.newCall(request).execute())
         {
             String string = response.body().string();
             return JSON.parseObject(string, GitHubUser.class);
         } catch (IOException e) {
             e.printStackTrace();
         }
         return null;
     }
}
