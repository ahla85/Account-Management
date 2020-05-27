package com.makinmakin.main;

public enum About {
    NAME("Account Management"),
    VERSION("3.1"),
    ABOUT(" Aplikasi pengatur akun-akun supaya dapat dikategorikan lebih baik dengan menggunakan" +
        "bahasa pemrograman Java 11.");
    
    private Object value;
    About(Object value) {
        this.value = value;
    }
    Object getValue() {
        return this.value;
    }
}