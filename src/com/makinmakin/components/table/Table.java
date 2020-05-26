package com.makinmakin.components.table;

import java.util.concurrent.atomic.AtomicInteger;

import com.makinmakin.components.account.Account;
import com.makinmakin.components.account.Accounts;

import static java.lang.String.format;

/**
 *  Sebuah kelas yang mendefinisikan tabel untuk fungsi printData().
 * Komponen-komponen yang ditampilkan adalah:
 * 1. email (40 chars),
 * 2. password (40 chars),
 * 3. date (20 chars),
 * 4. nick-description (50 chars).
 */
public class Table extends Rectangle {

    /**
     *  Instance objek Table kosong.
     */
    public Table() {}

    public Table(Accounts accounts) {
        this.accounts = accounts;
        super.setHeight(accounts.size());
        super.setWidth(getWillPrint(0, new Account()).length());
    }

    Accounts accounts;

    /**
     *  Kelas yang didefinisikan untuk menyimpan data-data setiap komponen.
     */
    public static enum Components {
        NO_LENGTH(4),
        EMAIL_LENGTH(40),
        PASSWORD_LENGTH(40),
        DESCRIPTION_LENGTH(80);
        
        private int space;
        Components(int space) {
            this.space = space;
        }

        public int getSpace() {
            return this.space;
        }
    }

    private String getWillPrint(int no, Account account) {
        int pointer = 0;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.delete(0, stringBuilder.length());
        for (Components components : Components.values()) {
            if (pointer == 0)
                stringBuilder.append(format("| %-" + components.getSpace() + "d", no));
            if (pointer == 1)
                stringBuilder.append(format("| %-" + components.getSpace() + "s", account.getEmail()));
            if (pointer == 2)
                stringBuilder.append(format("| %-" + components.getSpace() + "s", account.getPassword()));
            if (pointer == 3)
                stringBuilder.append(format("| %-" + components.getSpace() + "s", account.getDescription()));
            pointer++;
        }
        stringBuilder.append("|");
        return stringBuilder.toString();
    }

    public void printTable() {
        
        //  Lable.
        int pointer = 0;
        StringBuilder stringBuilder = new StringBuilder();
        System.out.println("-".repeat(super.getWidth()));
        for (Components components : Components.values()) {
            if (pointer == 0)
                stringBuilder.append(format("| %-" + components.getSpace() + "s", "NO"));
            if (pointer == 1)
                stringBuilder.append(format("| %-" + components.getSpace() + "s", "Email"));
            if (pointer == 2)
                stringBuilder.append(format("| %-" + components.getSpace() + "s", "Password"));
            if (pointer == 3)
                stringBuilder.append(format("| %-" + components.getSpace() + "s", "Description"));
            pointer++;
        }
        stringBuilder.append("|");
        System.out.println(stringBuilder);

        AtomicInteger no = new AtomicInteger(0);
        System.out.println("-".repeat(super.getWidth()));
        accounts.forEach((K, V) -> {
            System.out.println(getWillPrint(no.incrementAndGet(), V));
        });
        System.out.println("-".repeat(super.getWidth()));
    }


}