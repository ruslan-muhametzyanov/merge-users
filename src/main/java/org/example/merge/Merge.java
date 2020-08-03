package org.example.merge;

import java.io.IOException;
import java.util.List;

public class Merge {

    public static void main(String[] args) throws IOException {
        UserReader userReader = new UserReader(System.in);
        Merger merger = new Merger();
        UserWriter userWriter = new UserWriter(System.out);
        while (userReader.hasNext()) {
            merger.add(userReader.next());
        }
        List<User> users = merger.getResult();
        for (User user : users) {
            userWriter.write(user);
        }
        userWriter.flush();
    }
}