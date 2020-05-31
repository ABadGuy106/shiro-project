package net.abadguy.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

/**
 * 使用自定义realm 加入MD5 +salt +hash
 */
public class MyMD5Realm extends AuthorizingRealm {
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取身份信息
        String principal = (String) token.getPrincipal();
        if("zhangsan".equals(principal)){
            //密码md5加密
//            return new  SimpleAuthenticationInfo("zhangsan", "e10adc3949ba59abbe56e057f20f883e",this.getName());
            //密码md5加密+盐
            //参数1：正确的用户名
            //参数2：数据库中的密码
            //参数3：加密盐
            //参数4：提供realm的名字，this.getName()
//            return new  SimpleAuthenticationInfo("zhangsan", "9f4205367924ad57599fb477047026ed", ByteSource.Util.bytes("qazwsx"),this.getName());
            //密码md5加密+盐+hash散列
            return new  SimpleAuthenticationInfo("zhangsan", "6be1fb5d725efc5d623044d32bb370a2", ByteSource.Util.bytes("qazwsx"),this.getName());
        }
        return null;
    }
}
