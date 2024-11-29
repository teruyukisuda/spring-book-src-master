package com.example.myapp.shopping.exception;

@SuppressWarnings("serial")
public class StockShortageException extends RuntimeException {
    public StockShortageException(String msg) {
        super(msg);
    }
}
