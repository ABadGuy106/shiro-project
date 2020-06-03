package net.abadguy.config;

import net.abadguy.cache.RedisCacheManager;
import net.abadguy.realms.CustomerRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    //创建shiroFilter    负责拦截所有请求
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //给filter设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);

        //配置系统受限资源
        Map<String,String> map=new HashMap<>();
        map.put("/login.html","anon"); //anon 设置为公共资源
        map.put("/user/login","anon"); //anon 设置为公共资源
        map.put("/regist.html","anon"); //anon 设置为公共资源
        map.put("/register","anon"); //anon 设置为公共资源



        map.put("/**","authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);



        //默认认证界面路径
        shiroFilterFactoryBean.setLoginUrl("/login");

        return shiroFilterFactoryBean;
    }

    //创建安全管理器
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(Realm realm) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        //给安全管理器设置realm
        defaultWebSecurityManager.setRealm(realm);
        return defaultWebSecurityManager;
    }
    //创建自定义realm
    @Bean
    public Realm getRealm(){
        CustomerRealm customerRealm = new CustomerRealm();
        //修改密码凭证校验匹配器
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        //设置加密算法为md5
        credentialsMatcher.setHashAlgorithmName("MD5");
        //设置散列次数
        credentialsMatcher.setHashIterations(1024);

        customerRealm.setCredentialsMatcher(credentialsMatcher);


        //开启缓存管理
//        customerRealm.setCacheManager(new EhCacheManager());
        customerRealm.setCacheManager(new RedisCacheManager());
        //开启全局缓存
        customerRealm.setCachingEnabled(true);
        //开启认证的缓存
        customerRealm.setAuthenticationCachingEnabled(true);
        //设置缓存名称
        customerRealm.setAuthenticationCacheName("authenticationCache");
        //开启权限的缓存
        customerRealm.setAuthorizationCachingEnabled(true);
        //设置缓存名称
        customerRealm.setAuthorizationCacheName("authorizationCache");

        return customerRealm;
    }
}
