package com.makinmakin.components.account;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicReference;

import com.json.JSONArray;
import com.json.JSONObject;
import com.json.parser.JSONParser;
import com.json.parser.ParseException;

/**
 * Kumpulan akun-akun yang dibaca dari database. Yang diurutkan berdasarkan
 * email akun tersebut.
 */
public class Accounts extends TreeMap<String, Account> {

    private Accounts() {
    }

    public Accounts(File file) {
        try {
            JSONArray<JSONObject> jsonArray = (JSONArray) new JSONParser().parse(new FileReader(file));
            AtomicReference<Account> account = new AtomicReference<>();
            jsonArray.forEach(V -> {
                account.set(new Account(V));
                super.put(account.get().getEmail(), account.get());
            });
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException ignored) {}
    }

    /**
     * Menyimpan akun-akun hanya yang akun tersebut memuat @param keywords.
     */
    public Accounts filter(String[] keywords) {
        Accounts accounts = new Accounts();
        super.forEach((K1, V1) -> {
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