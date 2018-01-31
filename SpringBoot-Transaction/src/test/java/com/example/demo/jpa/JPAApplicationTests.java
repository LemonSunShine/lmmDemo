package com.example.demo.jpa;

import com.example.demo.jpa.domain.p.User;
import com.example.demo.jpa.domain.p.UserRepository;
import com.example.demo.jpa.domain.s.Message;
import com.example.demo.jpa.domain.s.MessageRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2017/12/11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class JPAApplicationTests {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MessageRepository messageRepository;

    @Before
    public void setUp() {
    }

    @Test
    @Transactional(value="transactionManagerPrimary")
    public void testUser() throws Exception {
        userRepository.deleteAll();
        userRepository.save(new User("aaa", 10));
        userRepository.save(new User("bbb", 20));
        userRepository.save(new User("ccc", 30));
        userRepository.save(new User("ddd", 40));
        userRepository.save(new User("eeee3333", 50));

        Assert.assertEquals(5, userRepository.findAll().size());

        User user = userRepository.findByName("ccc");
        Assert.assertEquals("ccc", user.getName());

    }
    @Test
    @Transactional(value="transactionManagerSecondary")
    public void testMsg() throws Exception {
        messageRepository.save(new Message("o1", "aaaaaaaaaa"));
        messageRepository.save(new Message("o2", "bbbbbbbbbb"));
        messageRepository.save(new Message("o3333333333333", "cccccccccc"));

        Assert.assertEquals(3, messageRepository.findAll().size());

    }

    @Test
    public void testJta() throws Exception {
        //分布式事务请自行实现

    }




}
