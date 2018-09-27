package cn.merryyou.sso.resource;

import org.apache.catalina.filters.RequestDumperFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * Created on 2018/1/27 0027.
 *
 * @author zlf
 * @email i@merryyou.cn
 * @since 1.0
 */
@Configuration
@EnableResourceServer
public class SsoResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/**").access("#oauth2.hasScope('read')")
                .antMatchers(HttpMethod.POST, "/api/**").access("#oauth2.hasScope('write')");
    }//资源服务器，使用@EnableResourceServer和yml配置连接认证服务器，获取访问资源的已认证客户端权限信息，
//在继承了ResourceServerConfigurerAdapter的Bean中配置认证信息对应的权限，决定已认证用户是否有权访问这里的某个资源
//这里直接访问需认证资源会返回401无权限访问，即/api/**，但没有配置认证的资源可正常访问，如/resource/**

    @Profile("!cloud")
    @Bean
    RequestDumperFilter requestDumperFilter() {
        return new RequestDumperFilter();
    }

}
