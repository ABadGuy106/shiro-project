package net.abadguy.service;

import net.abadguy.entity.Pers;
import net.abadguy.entity.User;

import java.util.List;

public interface Userservice {
    void register(User user);


    User findByUserName(String userName);

    User getRolesfindByUserName(String userName);

    List<Pers> findPermsByRoleId(Integer id);
}
