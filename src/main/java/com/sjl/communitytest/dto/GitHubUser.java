package com.sjl.communitytest.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @description: 给github发送token后, 所返回的JSON格式的用户信息
 * @author: Mr.S
 * @create: 2019-08-04 17:23
 **/
@Getter
@Setter
public class GitHubUser
{
    /** 用户姓名
     */
    private String name;
    /**用户id
     */
    private Long id;
    /**用户个性签名
     */
    private String bio;
}
