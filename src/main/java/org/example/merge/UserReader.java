package org.example.merge;

import java.io.InputStream;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class UserReader {

    private Scanner scanner;
    private User nextValue;

    public UserReader(InputStream inputStream) {
        this.scanner = new Scanner(inputStream);
        this.nextValue = parseNext();
    }

    public User next() {
        if (!hasNext()) {
            return null;
        }
        User currentValue = nextValue;
        nextValue = null;
        return currentValue;
    }

    public Boolean hasNext() {
        if (nextValue != null) {
            return true;
        }
        nextValue = parseNext();
        return nextValue != null;
    }

    private User parseNext() {
        String[] splittedLine = getNextLine().split(":");
        if (splittedLine.length == 2) {
            String username = splittedLine[0].trim();
            String[] mails = splittedLine[1].trim().split(",");
            HashSet<String> trimmedMails = new HashSet<String>();
            for (String mail : mails){
                trimmedMails.add(mail.trim());
            }
            if (!trimmedMails.isEmpty()) {
                return  new User(username, trimmedMails);
            }
        }
        return null;
    }

    private String getNextLine(){
        try {
            return scanner.nextLine();
        } catch (NoSuchElementException e) {
            return "";
        }
    }
}
