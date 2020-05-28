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
        NO_LENGTH(4, "NO"),
        EMAIL_LENGTH(40, Account.Key.EMAIL_KEYWORD.getKey()),
        PASSWORD_LENGTH(40, Account.Key.PASSWORD_KEYWORD.getKey()),
        DESCRIPTION_LENGTH(80, Account.Key.DESCRIPTION_KEYWORD.getKey());
        
        private int space;
        private String lable;
        Components(int space, String lable) {
            this.space = space;
            this.lable = lable;
        }

        public int getSpace() {
            return this.space;
        }

        public String getLable() {
            return this.lable;
        }
    }

    private String getWillPrint(int no, Account account) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Components components : Components.values()) {
            if (components.getLable().equalsIgnoreCase("NO")) {
                stringBuilder.append(format("| %-" + components.getSpace() + "d", no));
                continue;
            }

            stringBuilder.append(format("| %-" + components.getSpace() + "s", 
                account.get(components.getLable())));
        }
        stringBuilder.append("|");
        return stringBuilder.toString();
    }

    public void printTable() {
        
        //  Lable.
        StringBuilder stringBuilder = new StringBuilder();
        System.out.println("-".repeat(super.getWidth()));
        for (Components components : Components.values()) {
            stringBuilder.append(format("| %-" + components.getSpace() + "s", components.getLable()));
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