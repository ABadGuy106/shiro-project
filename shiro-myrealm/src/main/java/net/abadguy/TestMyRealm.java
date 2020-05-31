package net.abadguy;

import net.abadguy.realm.MyRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;

/**
 * 使用自定义realm
 */
public class TestMyRealm {
    public static void main(String[] args) {
        //创建securityManager
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        //设置自定义realm
        securityManager.setRealm(new MyRealm());
        //将安全工具类设置安全管理器
        SecurityUtils.setSecurityManager(securityManager);
        //通过安全工具获取subject
        Subject subject = SecurityUtils.getSubject();
        //创建令牌
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "123456");
        //登录
        subject.login(token);
    }
}
