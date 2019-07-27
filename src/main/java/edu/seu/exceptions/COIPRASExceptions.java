package edu.seu.exceptions;


import edu.seu.base.CodeEnum;

public class COIPRASExceptions extends Exception{
    CodeEnum codeEnum;

    public CodeEnum getCodeEnum(){return codeEnum;}

    public void setCodeEnum(CodeEnum codeEnum){
        this.codeEnum = codeEnum;
    }
    public COIPRASExceptions(){
    }
    public COIPRASExceptions(CodeEnum codeEnum,String msg)
    {
        super(msg);
        this.codeEnum = codeEnum;
    }
    public COIPRASExceptions(String msg)
    {
        super(msg);
    }

}
