package com.makinmakin;

/**
 * Sebuah interface yang menggambarkan fitur-fitur aplikasi ini adalah CRUD.
 */
interface CRUD {

    /**
     * Menampilkan semua akun.
     */
    void printAccounts();

    /**
     * Menampilkan semua akun yang dicari.
     */
    void searchAccounts();

    /**
     * Menambah sebuah akun.
     */
    void addAccount();

    /**
     * Memperbarui data akun.
     */
    void updateAccount();

    /**
     * Menghapus akun.
     */
    void deleteAccount();

}
