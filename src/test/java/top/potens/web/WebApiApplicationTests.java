package top.potens.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RBucket;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebApiApplicationTests {
    @Autowired
    private RedissonClient redisson;

    @Test
    public void contextLoads() {
    }
    @Test
    public void set() {
        // 设置字符串
        RBucket<String> bucket = redisson.getBucket("anyObject");
        bucket.set("111");
        String obj = bucket.get();
        System.out.println(obj);
    }


}
