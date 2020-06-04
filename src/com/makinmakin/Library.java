package com.makinmakin;

import java.io.Console;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

import com.json.JSONArray;
import com.makinmakin.components.account.Account;
import com.makinmakin.components.account.Accounts;
import com.makinmakin.components.table.Table;
import com.makinmakin.utils.AccountComponentsInvalidException;

import static com.makinmakin.utils.Utils.*;
import static java.lang.String.format;

public class Library implements CRUD {

    // /* --> Constructors <-- */

    // public Library(File path) {

    // }

    /* --> Fields <-- */
    
    public static final String MESSAGE_DATABASE_IS_EMPTY = "Database kosong.";
    public static final String MESSAGE_DATA_IS_NOT_FOUND = "Data tidak ditemukan.";
    public static final String MESSAGE_DATA_IS_ADDED     = "Data berhasil ditambahkan.";
    public static final String MESSAGE_DATA_IS_CHANGED   = "Data berhasil dirubah.";

    private Table table = null;
    private Console console = System.console();

    @Override
    public void printAccounts() {
        Accounts accounts = new Accounts(DATABASE);
        if (accounts.isEmpty()) {
            System.out.println(Library.MESSAGE_DATABASE_IS_EMPTY);
            return;
        }
        table = new Table(accounts);
        System.out.printf("Data yang ditemukan ada %d, sebagai berikut:\n", accounts.size());
        table.printTable();
    }

    @Override
    public void searchAccounts() {
        Accounts accounts = new Accounts(DATABASE);
        if (accounts.isEmpty()) {
            System.out.println(Library.MESSAGE_DATABASE_IS_EMPTY);
            return;
        }

        //  Input keywords.
        String[] keywords = console.readLine("Masukkan keywords yang akan dicari (delimiter spasi): ").split("\\s");
        accounts.filter(keywords);
        if (accounts.isEmpty()) {
            System.out.println(Library.MESSAGE_DATA_IS_NOT_FOUND);
            return;
        }

        table = new Table(accounts);
        System.out.printf("\nData yang ditemukan dengan keywords %s ada %d, sebagai berikut:\n", Arrays.toString(keywords), accounts.size());
        table.printTable();
    }

    @Override
    public void addAccount() {
        // Variabel untuk menampung accounts dari database untuk dikelola.
        Accounts accounts = new Accounts(DATABASE);

        //  Variabel untuk menampung objek Account baru.
        Account newAccount = new Account();
        try {
            newAccount.setUserName(console.readLine("Masukkan username: "));
            newAccount.setDescription(console.readLine("Masukkan deskripsi (maks 50 chars): "));
            newAccount.setEmail(console.readLine("Masukkan email: "));
            if (accounts.containsKey(newAccount.getEmail())) {
                clearScreen();
                System.out.println("\nMaaf akun yang Anda masukkan sudah ada, sebagai berikut:");
                System.out.println("-".repeat(100));
                accounts.get(newAccount.getEmail()).print();
                return;
            }
            ArrayList<String> layanan = new ArrayList<>();
            for (String service : console.readLine("Masukkan services (delimiter ,): ").split(",")) {
                layanan.add(service);
            }
            newAccount.setServices(layanan);
            newAccount.setRecoveryAccount(console.readLine("Masukkan account recovery: "));
            newAccount.setPassword(console.readLine("Masukkan password: "));
            newAccount.setNumberPhone(console.readLine("Masukkan numberphone: "));
            newAccount.setBorn(console.readLine("Masukkan tanggal/bulan/tahun lahir: "));
        } catch (AccountComponentsInvalidException e) {
            System.err.println(e);
            return;
        }
        
        clearScreen();
        System.out.println("\nData yang Anda masukkan adalah sebagai berikut:");
        System.out.println("-".repeat(100));
        newAccount.print();

        if (!getYesOrNo(console, "\nApakah Anda ingin menyimpan data tersebut [y/n]? "))
            return;

        accounts.put(newAccount.getEmail(), newAccount);
        try {
            JSONArray.writeJSONString((ArrayList<Account>) accounts.values(), new FileWriter(DATABASE), true);
            System.out.println(Library.MESSAGE_DATA_IS_ADDED);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateAccount() {
        //  Variabel untuk menampung accounts dari database untuk dikelola.
        Accounts accounts = new Accounts(DATABASE);
        
        //  Variabel untuk menampung accounts sementara utuk dipilih.
        Accounts filterAccounts = (Accounts) accounts.clone();
        if (accounts.isEmpty()) {
            System.out.println(Library.MESSAGE_DATABASE_IS_EMPTY);
            return;
        }

        //  Input keywords.
        String[] keywords = console.readLine("Masukkan keywords yang akan dicari (delimiter spasi): ").split("\\s");
        filterAccounts.filter(keywords);
        if (filterAccounts.isEmpty()) {
            System.out.println(Library.MESSAGE_DATA_IS_NOT_FOUND);
            return;
        }

        table = new Table(accounts);
        System.out.printf("\nData yang ditemukan dengan keywords %s ada %d, sebagai berikut:\n", Arrays.toString(keywords), filterAccounts.size());
        table.printTable();

        String email = console.readLine("\nMasukkan email yang akan diperbarui: ");
        clearScreen();
        //  Untuk menampilkan pesan apakah akun dengan email tertentu ditemukan.
        AtomicBoolean isFound = new AtomicBoolean(false);
        filterAccounts.forEach((K, V) -> {
            if (V.getEmail().equals(email)) {
                isFound.set(true);
                System.out.println("\nData ditemukan!");
                System.out.println("-".repeat(100));
                V.print();

                if (getYesOrNo(console, "\nApakah Anda ingin merubah data tersebut [y/n]? ")) {                    
                    //  Update perkomponen.
                    V.forEach((keyAccount, valueAccount) -> {
                        if (getYesOrNo(console, format("\nApakah Anda ingin merubah %s [y/n]? ", keyAccount)))
                            try {
                                V.set(keyAccount, console.readLine("Masukkan %s baru: ", keyAccount));
                            } catch (AccountComponentsInvalidException e) {
                                System.err.println(e);
                            }
                    });

                    if (getYesOrNo(console, "\nApakah Anda ingin menyimpan pembaruan tersebut [y/n]? "))
                        try {
                            // Menyimpan pada databse.
                            accounts.replace(K, V);
                            JSONArray.writeJSONString((ArrayList<Account>) accounts.values(), new FileWriter(DATABASE),
                                    true);
                            System.out.println("Berhasil update data!");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return;
                }
            }
        });

        if (!isFound.get())
            System.out.printf("Akun dengan email %s tidak ditemukan!\n", email);
    }

    @Override
    public void deleteAccount() {
        Accounts accounts = new Accounts(DATABASE);
        Accounts filterAccounts = (Accounts) accounts.clone();
        if (accounts.isEmpty()) {
            System.out.println(Library.MESSAGE_DATABASE_IS_EMPTY);
            return;
        }

        //  Input keywords.
        String[] keywords = console.readLine("Masukkan keywords yang akan dicari (delimiter spasi): ").split("\\s");
        accounts.filter(keywords);
        if (accounts.isEmpty()) {
            System.out.println("Data tidak ditemukan!");
            return;
        }

        table = new Table(accounts);
        System.out.printf("\nData yang ditemukan dengan keywords %s ada %d, sebagai berikut:\n",
                Arrays.toString(keywords), accounts.size());
        table.printTable();
		
		//  Input email yang akan dihapus.
        String email = console.readLine("\nMasukkan email yang akan dihapus: ");
        clearScreen();
        filterAccounts.forEach((K, V) -> {
            if (V.getEmail().equals(email)) {
                System.out.println("\nData ditemukan!");
                System.out.println("-".repeat(100));
                V.print();

                if (getYesOrNo(console, "\nApakah Anda ingin menghapus data tersebut [y/n]? "))
                    try {
                        // Menyimpan pada databse.
                        accounts.remove(K);
                        JSONArray.writeJSONString((ArrayList<Account>) accounts.values(), new FileWriter(DATABASE),
                                true);
                        System.out.println("Berhasil hapus data!");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

            }
        });
    }

    /* --> Another methods <-- */

    public void printByServices() {
        Accounts accounts = new Accounts(DATABASE);
        if (accounts.isEmpty()) {
            System.out.println(Library.MESSAGE_DATABASE_IS_EMPTY);
            return;
        }

        HashMap<String, ArrayList<Account>> accountsByService = new HashMap<>();
        ArrayList<Account> list = new ArrayList<>();
        accounts.forEach((K, V) -> {
            for (String service : V.getServices()) {
                if (accountsByService.containsKey(service)) {
                    list.add(V);
                } else {

                }
            }
        });

    }

    /**
     *  Menampilkan akun-akun yang memuat keywords secara detail (verbose mode).
     */
    public void searchVerboseAccount() {
        Accounts accounts = new Accounts(DATABASE);
        if (accounts.isEmpty()) {
            System.out.println(Library.MESSAGE_DATABASE_IS_EMPTY);
            return;
        }

        //  Input keywords.
        String[] keywords = console.readLine("Masukkan keywords yang akan dicari (delimiter spasi): ").split("\\s");
        accounts.filter(keywords);
        if (accounts.isEmpty()) {
            System.out.println("Data tidak ditemukan!");
            return;
        }

        ArrayList<Account> list = new ArrayList<>(accounts.values());
        for (int i = 0; i < list.size(); i++) {
            clearScreen();
            System.out.printf("\nData yang ditemukan dengan keywords %s ada %d, sebagai berikut:\n", Arrays.toString(keywords), accounts.size());
            System.out.println("\nData ke " + (1+i));
            System.out.println("-".repeat(100));
            list.get(i).print();
            
            if (!getYesOrNo(console, "\nLanjutkan [y/n]? "))
                return;
        }
    }
}