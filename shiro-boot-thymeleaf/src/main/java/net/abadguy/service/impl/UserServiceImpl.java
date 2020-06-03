package net.abadguy.service.impl;

import lombok.extern.slf4j.Slf4j;
import net.abadguy.dao.UserDao;
import net.abadguy.entity.Pers;
import net.abadguy.entity.User;
import net.abadguy.service.Userservice;
import net.abadguy.utils.SaltUtil;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("userService")
@Transactional
@Slf4j
public class UserServiceImpl implements Userservice {

    @Resource
    private UserDao userDao;


    @Override
    public void register(User user) {
        String salt= SaltUtil.getSalt(20);
        Md5Hash md5Hash = new Md5Hash(user.getPassword(), salt, 1024);
        user.setPassword(md5Hash.toHex());
        user.setSalt(salt);
        userDao.save(user);
    }

    @Override
    public User findByUserName(String userName) {
        log.info("查询数据库");
        return userDao.findByUserName(userName);
    }

    @Override
    public User getRolesfindByUserName(String userName) {
        log.info("查询数据库");
        return userDao.getRolesfindByUserName(userName);
    }

    @Override
    public List<Pers> findPermsByRoleId(Integer id) {
        log.info("查询数据库");
        return userDao.findPermsByRoleId(id);
    }
}
