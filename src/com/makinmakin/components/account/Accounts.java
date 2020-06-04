package com.makinmakin.components.account;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicReference;

import com.json.JSONArray;
import com.json.JSONObject;
import com.json.parser.JSONParser;
import com.json.parser.ParseException;
import com.makinmakin.utils.AccountComponentsInvalidException;

/**
 * Kumpulan akun-akun yang dibaca dari database. Yang diurutkan berdasarkan
 * email akun tersebut.
 */
public class Accounts extends TreeMap<String, Account> {

    public Accounts() {
    }

    public Accounts(File file) {
        try {
            JSONArray<JSONObject> jsonArray = (JSONArray) new JSONParser().parse(new FileReader(file));
            AtomicReference<Account> account = new AtomicReference<>();
            jsonArray.forEach(V -> {
                try {
                    account.set(new Account(V));
                } catch (AccountComponentsInvalidException e) {
                    System.err.println(e);
                }
                super.put(account.get().getEmail(), account.get());
            });
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException ignored) {}
    }

    /**
     * Menyimpan akun-akun hanya yang apabila akun tersebut memuat keywords.
     */
    public Accounts filter(String[] keywords) {
        Accounts accounts = new Accounts();
        super.forEach((K1, V1) -> {
            //  Cek perkomponen.
            V1.forEach((K2, V2) -> {
                if (V2 != null) {
                    for (String keyword : keywords) {
                        if (V2.toString().toLowerCase().contains(keyword.toLowerCase())) {
                            accounts.put(K1, V1);
                            break;
                        }
                    }
                }
            });
        });

        super.clear();
        if (accounts.size() == 0)
            return null;
        accounts.forEach((K, V) -> super.put(K, V));
        return this;
    }
}