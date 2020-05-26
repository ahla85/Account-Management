package com.makinmakin.utils;

/**
 *  Sebuah kelas eksespsi sebuah akun yang melanggar aturan kelas Account.
 */
public class AccountComponentsInvalidException extends Exception {
    public AccountComponentsInvalidException(String message) {
        super(message);
    }
}