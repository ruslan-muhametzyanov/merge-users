package org.example.merge;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

public class MergerTest {

    private Merger merger;

    @BeforeEach
    public void setUp(){
        merger = new Merger();
    }

    @Test
    public void testEmptyInput(){
        assert(merger.getResult().isEmpty());
    }

    @Test
    public void testWithoutMerge(){
        HashSet<String> mails1 = new HashSet<String>();
        mails1.add("mail1");
        mails1.add("mail2");
        merger.add(new User("user1", mails1));
        HashSet<String> mails2 = new HashSet<String>();
        mails2.add("mail3");
        mails2.add("mail4");
        merger.add(new User("user2", mails2));

        User controlUser1 = new User("user1", mails1);
        User controlUser2 = new User("user2", mails2);
        List<User> controlUsers = new ArrayList<User>();
        controlUsers.add(controlUser1);
        controlUsers.add(controlUser2);
        assert(resultEquals(merger.getResult(), controlUsers));
    }

    @Test
    public void testWithMerge(){
        HashSet<String> mails1 = new HashSet<String>();
        mails1.add("mail1");
        mails1.add("mail2");
        merger.add(new User("user1", mails1));
        HashSet<String> mails2 = new HashSet<String>();
        mails2.add("mail2");
        mails2.add("mail4");
        merger.add(new User("user2", mails2));

        HashSet<String> mails3 = new HashSet<String>();
        mails3.add("mail1");
        mails3.add("mail2");
        mails3.add("mail4");
        User controlUser = new User("user1", mails3);
        List<User> controlUsers = new ArrayList<User>();
        controlUsers.add(controlUser);
        assert(resultEquals(merger.getResult(), controlUsers));
    }

    private boolean resultEquals(List<User> checkUserList, List<User> controlUserList){
        Map<String, Boolean> checked = new HashMap<>();
        for (User checkUser : checkUserList){
            checked.put(checkUser.getName(), false);
            for (User controlUser: controlUserList){
                if (checkUser.getName().equals(controlUser.getName())){
                    if (checkUser.getMails().equals(controlUser.getMails())){
                        checked.put(checkUser.getName(), true);
                        break;
                    }
                }
            }
        }
        for (Map.Entry<String, Boolean> entry : checked.entrySet()){
            if (entry.getValue().equals(Boolean.FALSE)){
                return false;
            }
        }
        return true;
    }
}
