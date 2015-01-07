package com.mycompany.data.exceptions;

public class AlreadyHitException extends Exception
{

    public AlreadyHitException()
    {
    }

    public AlreadyHitException(String string)
    {
        super(string);
    }

    private static final long serialVersionUID = 7953091634683770541L;
}
