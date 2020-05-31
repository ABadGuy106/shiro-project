package net.abadguy;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;

public class TestAuthenticator {
    public static void main(String[] args) {
        //1、创建安全管理器对象
        DefaultSecurityManager securityManager=new DefaultSecurityManager();
        //2、给安全管理器设置realm
        securityManager.setRealm(new IniRealm("classpath:shiro.ini"));
        //3.SecurityUtils 全局安全工具类   給全局安全工具类设置安全管理器
        SecurityUtils.setSecurityManager(securityManager);
        //4.关键对象 subject主体
        Subject subject = SecurityUtils.getSubject();
        //5.创建令牌
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan","123");
        //用户认证
       try {
           subject.login(token);
           System.out.println("认证状态： "+subject.isAuthenticated());
       }catch (Exception e){
           e.printStackTrace();
       }
    }
}
