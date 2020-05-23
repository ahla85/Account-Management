package com.makinmakin;

import java.io.Console;
import java.io.File;
import java.io.IOException;

import com.makinmakin.main.Main;

/**
 * Kelas yang berisikan semua fungsi umum yang dibutuhkan aplikasi Library ini.
 */
public class Utils {

    public static final File DATABASE = new File(Main.PATH + "//sources//database.json");

    public static void initAccountManagement(File path) {
        path.mkdirs();

        try {
            File file;

            file = new File(path + "//" + DATABASE);
            file.getParentFile().mkdirs();
            file.createNewFile();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *  Fungsi untuk mendapatkan persetujuan dari user.
     * 
     * @param console
     * @param message
     * @return
     */
    public static boolean getYesOrNo(Console console, String message) {
        String input = console.readLine(message);
        while (!input.equalsIgnoreCase("Y") && !input.equalsIgnoreCase("N")) {
            System.out.printf("Input '%s' tidak valid!\n", input);
            input = console.readLine(message);
        }
        return input.equalsIgnoreCase("Y");
    }

    /**
     *  Menghapus buffered console.
     */
    public static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                System.out.print("\033\143");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}