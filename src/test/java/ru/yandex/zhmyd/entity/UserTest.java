package ru.yandex.zhmyd.entity;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    private User user;

   @Before
    public void init(){
        user = new User("name", "family", "zzz@zzz.xx");
    }


    @Test
    public void setEmailTest(){
        user.setEmail("ewrfrewgtswgh");
    }

    @Test
    public void getEmailTest(){
        assertEquals("zzz@zzz.xx", user.getEmail());
    }

    @Test
    public void getNameTest(){
        assertEquals("name", user.getFirstName());
    }

    @Test
    public void getEmailTest1(){
        assertEquals("zzz@zzz.xx", user.getEmail());
    }
}
