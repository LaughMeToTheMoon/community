package com.sjl.communitytest.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * @description: 封装github授权所需要的参数对象
 * @author: Mr.S
 * @create: 2019-08-04 16:40
 **/
@Getter
@Setter
public class AccessTokenProvider
{
    /**在github申请授权所给的 client_id
     */
    private String client_id;
    /**在github申请授权所给的  client_secret
     */
    private String client_secret;
    /**github回调给的codeId
     */
    private String code;
    /**请求github后，所回调的url
     */
    private String redirect_uri;
    /**一个随机字符串
     */
    private String state;
}
