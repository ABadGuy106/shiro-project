package net.abadguy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User implements Serializable {
    private Integer id;
    private String userName;
    private String password;
    private String salt;
    //定义角色集合
    private List<Role> roles;


}
