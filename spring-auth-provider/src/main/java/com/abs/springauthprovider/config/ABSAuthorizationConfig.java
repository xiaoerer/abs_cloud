package com.abs.springauthprovider.config;

import com.abs.springabscommon.constant.CommonConstant;
import com.abs.springabscommon.constant.SecurityConstants;
import com.abs.springauthprovider.component.ABSWebResponseExceptionTranslator;
import com.abs.springauthprovider.service.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * @author:XieZhengJun
 * @description:
 * @date: Created in 17:59 2018/7/6
 *
 * https://blog.csdn.net/mingli686/article/details/80604165
 *
 * 认证服务器逻辑实现
 **/
@Configuration
@Order(Integer.MIN_VALUE)
@EnableAuthorizationServer  //声明一个认证服务器
public class ABSAuthorizationConfig extends AuthorizationServerConfigurerAdapter {

    //授权信息配置类
    @Autowired
    private AuthServerConfig authServerConfig;

    @Autowired
    public AuthenticationManager authenticationManager;


    //访问令牌用来加载认证
    @Autowired
    private UserDetailServiceImpl userDetailsService;

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    //异常信息转换
    @Autowired
    private ABSWebResponseExceptionTranslator absWebResponseExceptionTranslator;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()  //使用in-memory存储
                //该配置为SSO客户端配置所需的
                .withClient(authServerConfig.getClientId()) //client_id
                .secret(authServerConfig.getClientSecret()) //client_secret
                //该client允许的授权类型
                .authorizedGrantTypes(SecurityConstants.REFRESH_TOKEN, SecurityConstants.PASSWORD,SecurityConstants.AUTHORIZATION_CODE)
                .scopes(authServerConfig.getScope());   //允许的授权范围
    }

    /**
     * 声明安全约束,哪些允许访问,哪些不允许访问
     * @param security
     * @throws Exception
     *
     * 配置oauth认证安全策略 从表单提交经过OAuth认证
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                .allowFormAuthenticationForClients()
                .tokenKeyAccess("isAuthenticated()")
                .checkTokenAccess("permitAll()");
    }

    /**
     * 声明授权和token的端点以及token的服务的一些配置信息,比如采用什么存储方式,token的有效期等
     *
     *token存储方式共有三种分别是：
     *
     * （1）InMemoryTokenStore：存放内存中，不会持久化
     *
     * （2）JdbcTokenStore：存放数据库中
     *
     * （3）Jwt: json web token
     *
     *
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .tokenStore(new RedisTokenStore(redisConnectionFactory))
                .accessTokenConverter(jwtAccessTokenConverter())
                .authenticationManager(authenticationManager)
                .exceptionTranslator(absWebResponseExceptionTranslator)
                .reuseRefreshTokens(false)
                .userDetailsService(userDetailsService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 认证转换器配置使用@Bean的方式注入到当前方法中
     * @return
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setSigningKey(CommonConstant.SIGN_KEY);
        return jwtAccessTokenConverter;
    }


}
