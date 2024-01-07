package com.cashmanager.server.product.exception;

/**
 * Class that will be thrown when an error occur while communicated with the database
 */
public class ProductException extends Exception{
    public ProductException() {
        super();
    }

    public ProductException(String message) {
        super(message);
    }
}
