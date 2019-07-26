package edu.seu.model;

import edu.seu.base.AuthorityEnum;

/**
 * 未登陆用户
 */
public class Vistor extends User {
    public Vistor()
    {
        setId(-1);
        setLevel(AuthorityEnum.VISITOR.getValue());
    }
}
