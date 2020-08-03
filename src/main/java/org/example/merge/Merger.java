package org.example.merge;

import java.util.*;

public class Merger {

    private HashMap<String, HashSet<String>> mailIndex;
    private HashMap<String, User> usersIndex;

    public Merger(){
        mailIndex = new HashMap<String, HashSet<String>>();
        usersIndex = new HashMap<String, User>();
    }

    public void add(User user) {
        usersIndex.put(user.getName(), user);
        for (String mail : user.getMails()) {
            HashSet<String> usersName = mailIndex.get(mail);
            if (usersName != null) {
                usersName.add(user.getName());
                mailIndex.put(mail, usersName);
            } else {
                usersName = new HashSet<String>();
                usersName.add(user.getName());
                mailIndex.put(mail, usersName);
            }
        }
    }

    public List<User> getResult() {
        List<User> mergedUsers = new ArrayList<User>();
        for (Map.Entry<String, User> entry : usersIndex.entrySet()) {
            if (!entry.getValue().isProcessed()) {
                User mergedUser = new User(entry.getKey(), findAllMailsByOneUser(entry.getValue()));
                mergedUsers.add(mergedUser);
            }
        }
        return mergedUsers;
    }

    private HashSet<String> findAllMailsByOneUser(User user) {
        user.setProcessed(true);
        HashSet<String> mergedMailsByOneUser = new HashSet<String>();
        Set<String> mails = user.getMails();
        for (String mail : mails) {
            HashSet<String> usersName = mailIndex.get(mail);
            for (String userName : usersName) {
                User userInner = usersIndex.get(userName);
                if (!userInner.isProcessed()) {
                    mergedMailsByOneUser.addAll(findAllMailsByOneUser(userInner));
                } else {
                    mergedMailsByOneUser.add(mail);
                }
            }
        }
        return mergedMailsByOneUser;
    }
}
