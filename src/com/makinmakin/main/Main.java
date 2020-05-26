package com.makinmakin.main;

import java.io.Console;
import java.io.File;

import com.makinmakin.Library;
import static com.makinmakin.utils.Utils.*;

public class Main {

    public static final File PATH = new File("C://ProgramData//makinmakin");

    public Main() {
        initAccountManagement(PATH);
    }

    public static void main(String[] args) {
        
        new Main();

        boolean isNext = true;
        String inputUser = null;
        Console console = System.console();
        Library library = new Library();
        while (isNext) {

            clearScreen();
            System.out.println("Account Management");
            System.out.println("-".repeat(30));
            System.out.println("1. Menampilkan semua akun");
            System.out.println("2. Mencari akun");
            System.out.println("3. Menambah akun");
            System.out.println("4. Merubah akun");
            System.out.println("5. Menghapus akun");
            System.out.println("-".repeat(30));
            
            inputUser = console.readLine("\nMasukkan pilihan Anda: ");
            switch (inputUser) {
                case "1":
                    clearScreen();
                    library.printAccounts();
                    break;
                case "2":
                    clearScreen();
                    library.searchAccounts();
                    break;
                case "3":
                    clearScreen();
                    library.addAccount();
                    break;
                case "4":
                    clearScreen();
                    library.updateAccount();
                    break;
                case "5":
                    clearScreen();
                    library.deleteAccount();
                    break;
                case "2 -v":
                    clearScreen();
                    library.searchVerboseAccount();
                    break;
                default:
                    System.err.println("Input yang Anda masukan tidak valid!");
            }

            isNext = getYesOrNo(console, "\nApakah Anda yakin ingin melanjutkan [y/n]? ");
            
        }
    }
}