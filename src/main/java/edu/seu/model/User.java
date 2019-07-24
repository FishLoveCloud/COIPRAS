package edu.seu.model;

import edu.seu.base.AuthorityEnum;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

/**
 * @Author :lec
 * @Date :2019-7-24 16:30
 */
@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
public class User {
    private Integer id;
    private String name;
    private String password;
    private String email;
    /**
     * @see AuthorityEnum
     * 0:注册用户，1：管理员
     */
    private Integer level;
    private Timestamp createTime;
    private Timestamp updateTime;
    private String phoneNumber;
    private String sex;
    private String company;
    private String address;
    private Integer active;

    /**
     * 0:未激活 ，1：激活
     */

    public User() {

    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", level=" + level +
                ", sex=" + sex +
                ", phone=" + phoneNumber +
                ", company=" + company +
                ", address=" + address +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

}
