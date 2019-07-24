package edu.seu.base;


public enum AuthorityEnum {

    VISITOR(0),

    MEMBER(1),

    ADMIN(2);
    int value;

    AuthorityEnum(int value){ this.value = value; }

    public int getValue(){return value;}
}
