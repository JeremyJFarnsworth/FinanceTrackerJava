package edu.neumont.mgt101.model;

import edu.neumont.mgt101.model.money.Atm;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {
    private String username;
    private String password;
    private Atm atm;
    private static final String DATABASE_FILE = "database.txt";

    public User(String username, String password) {
        this.username = username;
        this.password = hashPassword(password);
        atm = new Atm();
    }

    public boolean registerUser() {
        if (isUsernameTaken(username)) return false; // Prevent duplicate usernames

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATABASE_FILE, true))) {
            writer.write(username + "," + password);
            writer.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean authenticateUser() {
        try (BufferedReader reader = new BufferedReader(new FileReader(DATABASE_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                if (userData.length == 2 && userData[0].equals(username)) {
                    return userData[1].equals(password); // Compare stored hash with input hash
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean isUsernameTaken(String username) {
        File file = new File(DATABASE_FILE);
        if (!file.exists()) return false; // If file doesn't exist, no users exist yet

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(username + ",")) return true; // Username already exists
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashedBytes) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString(); // Convert byte array to hex string
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public Atm getATM() {
        return atm;
    }

    public void withdraw(int amount) {
        atm.withdraw(amount);
    }

    public void deposit(int amount) {
        atm.deposit(amount);
    }
}
