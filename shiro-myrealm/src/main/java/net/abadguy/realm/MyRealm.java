package net.abadguy.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * 自定义Realm 将认证授权的数据来源
 */
public class MyRealm extends AuthorizingRealm {


    //授权
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }
    //认证
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //在token中获取用户名
        String principal= (String) token.getPrincipal();
        System.out.println("从token中获得的用户名 "+principal);
        //根据身份信息使用jdbc mybatis查询相关数据库
        if("zhangsan".equals(principal)){
            //参数1：正确的用户名
            //参数2：数据库中的密码
            //参数3：提供realm的名字，this.getName()
            SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(principal, "123456",this.getName());
            return authenticationInfo;
        }
        return null;
    }
}
