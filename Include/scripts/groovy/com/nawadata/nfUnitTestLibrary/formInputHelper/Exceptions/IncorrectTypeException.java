package com.nawadata.nfUnitTestLibrary.formInputHelper.Exceptions;

public class IncorrectTypeException extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = -7268745837041516685L;
    public IncorrectTypeException() { super(); }
    public IncorrectTypeException(String message) { super(message); }
    public IncorrectTypeException(String message, Throwable cause) { super(message, cause); }
    public IncorrectTypeException(Throwable cause) { super(cause); }
}
