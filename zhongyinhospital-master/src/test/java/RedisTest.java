import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@ComponentScan
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml"})
public class RedisTest {
    @Autowired
    RedisTemplate<String,String> redisTemplate;

    /**
     * 操作String类型
     */
    @Test
    public void testString(){
        redisTemplate.opsForValue().set("nb","22");
        System.out.println(redisTemplate.opsForValue().get("nb"));
        //设置10s过期
        redisTemplate.opsForValue().set("code","2023",10l,TimeUnit.SECONDS);
        //是否存在 不存在则创建
        Boolean code = redisTemplate.opsForValue().setIfAbsent("code", "2021");
        System.out.println(code);
    }
    /**
     * 操作哈希类型
     */
    @Test
    public void testHash(){
        HashOperations hashOperations = redisTemplate.opsForHash();
        //存值
        hashOperations.put("User","name","zhangsan");
        hashOperations.put("User","age","20");
        hashOperations.put("User","addres","beijing");
        //取值
        String o = (String) hashOperations.get("User", "name");
        System.out.println(o);
        //获取所有hashkey
        Set user = hashOperations.keys("User");
        for (Object o1 : user) {
            System.out.println(o1);
        }
        //获取所有值
        List user1 = hashOperations.values("User");
        for (Object o1 : user1) {
            System.out.println(o1);
        }
    }

    /**
     * 操作list类型
     */
    @Test
    public void testList(){
        ListOperations stringStringListOperations = redisTemplate.opsForList();
        //存值
        stringStringListOperations.leftPush("ettt","name");
        stringStringListOperations.leftPushAll("ettt","age","addres");
        //取值
        List<String> user = stringStringListOperations.range("ettt", 0, -1);
        for (String o : user) {
            System.out.println(o);
        }
        //获取list长度
        Long ettt = stringStringListOperations.size("ettt");
        int i = ettt.intValue();
        for (int i1 = 0; i1 < i; i1++) {
            //取出队列
            String o = (String) stringStringListOperations.rightPop("ettt");
            System.out.println(o);
        }
    }

    /**
     * 操作set类型
     */
    @Test
    public void testSet(){
        SetOperations setOperations = redisTemplate.opsForSet();
        //存值
        setOperations.add("myset","a","b","c","a");
        //取值
        Set myset = setOperations.members("myset");
        for (Object o : myset) {
            System.out.print(o);
        }
        //删除指定
        setOperations.remove("myset", "a");

         myset = setOperations.members("myset");
        for (Object o : myset) {
            System.out.println(o);
        }
    }

    /**
     *操作ZSet类型
     */
    @Test
    public void testZset(){
        ZSetOperations<String, String> stringStringZSetOperations = redisTemplate.opsForZSet();
        //存值
        stringStringZSetOperations.add("myzset","a",10.0);
        stringStringZSetOperations.add("myzset","b",11.0);
        stringStringZSetOperations.add("myzset","c",12.0);
        stringStringZSetOperations.add("myzset","a",1.0);
        //取值
        Set<String> myzset = stringStringZSetOperations.range("myzset", 0, -1);
//        for (String s : myzset) {
//            System.out.println(s);
//        }
        //修改分值
        stringStringZSetOperations.incrementScore("myzset","a",14.0);
//        myzset = stringStringZSetOperations.range("myzset", 0, -1);
//        for (String s : myzset) {
//            System.out.println(s);
//        }
        stringStringZSetOperations.remove("myzset","b","c");
        myzset = stringStringZSetOperations.range("myzset", 0, -1);
        for (String s : myzset) {
            System.out.println(s);
        }
    }
    /**
     * 通用操作 所有类型的通用操作
     */
    @Test
    public void testCommon(){
        //获取所有key
        Set<String> keys = redisTemplate.keys("*");
        for (String key : keys) {
            System.out.println(key);
        }
        //判断key是否存在
        Boolean name = redisTemplate.hasKey("name");
        System.out.println(name);
        //删除key
        redisTemplate.delete("nb");
        //获取key对应value的数据类型
        DataType myhash = redisTemplate.type("myhash");
        System.out.println(myhash.name());
    }




}
