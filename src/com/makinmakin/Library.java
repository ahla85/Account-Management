package com.makinmakin;

import java.io.Console;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import com.json.JSONArray;
import com.makinmakin.components.account.Account;
import com.makinmakin.components.account.Accounts;
import com.makinmakin.components.table.Table;

import static com.makinmakin.Utils.*;
import static java.lang.String.format;

public class Library implements CRUD {

    // /* --> Constructors <-- */

    // public Library(File path) {

    // }

    private Table table = null;
    private Console console = System.console();

    @Override
    public void printAccounts() {
        Accounts accounts = new Accounts(DATABASE);
        if (accounts.isEmpty()) {
            System.out.println("Database kosong!");
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
            System.out.println("Database kosong!");
            return;
        }

        String[] keywords = console.readLine("Masukkan keywords yang akan dicari (delimiter spasi): ").split("\\s");
        //  Filter objek Accounts.
        accounts.filter(keywords);
        if (accounts.isEmpty()) {
            System.out.println("Data tidak ditemukan!");
            return;
        }

        table = new Table(accounts);
        System.out.printf("\nData yang ditemukan dengan keywords %s ada %d, sebagai berikut:\n", Arrays.toString(keywords), accounts.size());
        table.printTable();
    }

    public void searchVerboseAccount() {
        Accounts accounts = new Accounts(DATABASE);
        if (accounts.isEmpty()) {
            System.out.println("Database kosong!");
            return;
        }

        String[] keywords = console.readLine("Masukkan keywords yang akan dicari (delimiter spasi): ").split("\\s");
        //  Filter objek Accounts.
        accounts.filter(keywords);
        if (accounts.isEmpty()) {
            System.out.println("Data tidak ditemukan!");
            return;
        }

        ArrayList<Account> list = new ArrayList<>(accounts.values());
        for (int i = 0; i < list.size(); i++) {
            clearScreen();
            System.out.printf("\nData yang ditemukan dengan keywords %s ada %d, sebagai berikut:\n", Arrays.toString(keywords), accounts.size());
            System.out.println("\nData ke " + i);
            System.out.println("-".repeat(100));
            list.get(i).print();
            
            if (!getYesOrNo(console, "\nLanjutkan [y/n]? "))
                return;
        }
    }

    @Override
    public void addAccount() {
        Accounts accounts = new Accounts(DATABASE);
        Account newAccount = new Account();
        newAccount.setUserName(console.readLine("Masukkan username: "));
        newAccount.setNickDescription(console.readLine("Masukkan deskripsi pendek (maks 50 chars): "));
        newAccount.setDescription(console.readLine("Masukkan deskripsi: "));
        newAccount.setEmail(console.readLine("Masukkan email: "));
        ArrayList<String> layanan = new ArrayList<>();
        for (String service : console.readLine("Masukkan services (delimiter ,): ").split(",")) {
            layanan.add(service);
        }
        newAccount.setServices(layanan);
        newAccount.setRecoveryAccount(console.readLine("Masukkan account recovery: "));
        newAccount.setPassword(console.readLine("Masukkan password: "));
        newAccount.setNumberPhone(console.readLine("Masukkan numberphone: "));
        newAccount.setBorn(console.readLine("Masukkan tanggal/bulan/tahun lahir: "));
        
        if (accounts.containsKey(newAccount.getEmail())) {
                System.out.println("\nMaaf akun yang Anda masukkan sudah ada.");
                return;
        }
        
        clearScreen();
        System.out.println("\nData yang Anda masukkan adalah sebagai berikut:");
        System.out.println("-".repeat(50));
        newAccount.print();

        if (!getYesOrNo(console, "\nApakah Anda ingin menyimpan data tersebut [y/n]? "))
            return;

        accounts.put(newAccount.getEmail(), newAccount);
        try {
            JSONArray.writeJSONString(new ArrayList<>(accounts.values()), new FileWriter(DATABASE), true);
            System.out.println("Berhasil menambah akun!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateAccount() {
        Accounts accounts = new Accounts(DATABASE);
        Accounts filterAccounts = (Accounts) accounts.clone();
        if (accounts.isEmpty()) {
            System.out.println("Database kosong!");
            return;
        }

        String[] keywords = console.readLine("Masukkan keywords yang akan dicari (delimiter spasi): ").split("\\s");
        // Filter objek Accounts.
        filterAccounts.filter(keywords);
        if (accounts.isEmpty()) {
            System.out.println("Data tidak ditemukan!");
            return;
        }

        table = new Table(accounts);
        System.out.printf("\nData yang ditemukan dengan keywords %s ada %d, sebagai berikut:\n", Arrays.toString(keywords), accounts.size());
        table.printTable();

        String email = console.readLine("\nMasukkan email yang akan diperbaharui: ");
        clearScreen();
        filterAccounts.forEach((K, V) -> {
            if (V.getEmail().equals(email)) {
                System.out.println("\nData ditemukan!");
                System.out.println("-".repeat(100));
                V.print();

                if (getYesOrNo(console, "\nApakah Anda ingin merubah data tersebut [y/n]? ")) {                    
                    //  Update perkomponen.
                    V.forEach((keyAccount, valueAccount) -> {
                        if (getYesOrNo(console, format("\nApakah Anda ingin merubah %s [y/n]? ", keyAccount)))
                            V.set(keyAccount, console.readLine("Masukkan %s baru: ", keyAccount));
                    });

                    if (getYesOrNo(console, "\nApakah Anda ingin menyimpan pembaharuan tersebut [y/n]? ")) {
                        try {
                            // Menyimpan pada databse.
                            accounts.replace(K, V);
                            JSONArray.writeJSONString(new ArrayList<>(accounts.values()), new FileWriter(DATABASE),
                                    true);
                            System.out.println("Berhasil update data!");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return;
                    }
                }
            }
        });
    }

    @Override
    public void deleteAccount() {
        Accounts accounts = new Accounts(DATABASE);
        Accounts filterAccounts = (Accounts) accounts.clone();
        if (accounts.isEmpty()) {
            System.out.println("Database kosong!");
            return;
        }

        String[] keywords = console.readLine("Masukkan keywords yang akan dicari (delimiter spasi): ").split("\\s");
        // Filter objek Accounts.
        accounts.filter(keywords);
        if (accounts.isEmpty()) {
            System.out.println("Data tidak ditemukan!");
            return;
        }

        table = new Table(accounts);
        System.out.printf("\nData yang ditemukan dengan keywords %s ada %d, sebagai berikut:\n",
                Arrays.toString(keywords), accounts.size());
        table.printTable();

        String email = console.readLine("\nMasukkan email yang akan dihapus: ");
        clearScreen();
        filterAccounts.forEach((K, V) -> {
            if (V.getEmail().equals(email)) {
                System.out.println("\nData ditemukan!");
                System.out.println("-".repeat(100));
                V.print();

                if (getYesOrNo(console, "\nApakah Anda ingin menghapus data tersebut [y/n]? ")) {
                    try {
                        // Menyimpan pada databse.
                        accounts.remove(K);
                        JSONArray.writeJSONString(new ArrayList<>(accounts.values()), new FileWriter(DATABASE),
                                true);
                        System.out.println("Berhasil hapus data!");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

   
    
}