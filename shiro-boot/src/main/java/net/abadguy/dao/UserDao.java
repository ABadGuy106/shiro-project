package net.abadguy.dao;

import net.abadguy.entity.Pers;
import net.abadguy.entity.Role;
import net.abadguy.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {

    void save(User user);

    User findByUserName(String userName);

    User getRolesfindByUserName(String userName);

    //根据角色id查询权限信息
    List<Pers> findPermsByRoleId(Integer id);
}
