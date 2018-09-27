package cn.merryyou.sso.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on 2017/12/26.
 *
 * @author zlf
 * @since 1.0
 */
@SpringBootApplication
@RestController
@EnableOAuth2Sso
public class SsoClient1Application {

    @Autowired
    OAuth2RestTemplate oAuth2RestTemplate;
//SSO客户端实际上是将一个认证体系配置在了yml指定的认证服务器，所有资源都需要登录认证服务器认证成功后访问，
//包括其自身资源和在这里使用OAuth2RestTemplate访问的资源，OAuth2RestTemplate封装了其认证、权限信息，
//可访问使用同一个认证服务器配置认证访问的资源服务器的资源
    @GetMapping("/user")
    public Authentication user(Authentication user) {
        return user;
    }

    @Value("${messages.url}/resource/api")
    String messagesUrl;

    public static void main(String[] args) {
        SpringApplication.run(SsoClient1Application.class, args);
    }

    @RequestMapping("/api")
    String home(Model model) {
        String result = oAuth2RestTemplate.getForObject(messagesUrl + "/2", String.class);
        return result;
    }

    @Bean
    OAuth2RestTemplate oAuth2RestTemplate(OAuth2ClientContext oAuth2ClientContext, OAuth2ProtectedResourceDetails details){
        return new OAuth2RestTemplate(details,oAuth2ClientContext);
    }
}
