package net.abadguy.realms;

import lombok.extern.slf4j.Slf4j;
import net.abadguy.entity.Pers;
import net.abadguy.entity.Role;
import net.abadguy.entity.User;
import net.abadguy.salt.MyByteSource;
import net.abadguy.service.Userservice;
import net.abadguy.utils.ApplicationContexUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Slf4j
public class CustomerRealm extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //获取身份信息
        String principal= (String) principals.getPrimaryPrincipal();
        //根据主身份信息获取角色和权限信息
        Userservice userservice= (Userservice) ApplicationContexUtils.getBean("userService");
        User user = userservice.getRolesfindByUserName(principal);
        List<Role> roles = user.getRoles();
        if(!CollectionUtils.isEmpty(roles)){
            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
            roles.forEach(role -> {
                simpleAuthorizationInfo.addRole(role.getName());
                //权限信息
                List<Pers> perms = userservice.findPermsByRoleId(role.getId());
                if(!CollectionUtils.isEmpty(perms)){
                    perms.forEach(pers -> simpleAuthorizationInfo.addStringPermission(pers.getName()));
                }
            });
            return simpleAuthorizationInfo;
        }

        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.info("--------开始用户认证----------");
        String principal = (String) token.getPrincipal();
        SimpleAuthenticationInfo simpleAuthenticationInfo = null;
        //在工厂中获取service对象
        Userservice userservice= (Userservice) ApplicationContexUtils.getBean("userService");
        User user = userservice.findByUserName(principal);
        if(!ObjectUtils.isEmpty(user)){
            log.info("查询到的用户信息：{}",user);
            return new SimpleAuthenticationInfo(user.getUserName(),user.getPassword(),new MyByteSource(user.getSalt()),this.getName());
        }
        return simpleAuthenticationInfo;
    }
}
