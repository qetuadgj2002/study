package com.example.redis.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.util.List;

public class RedisUtil {

    private static final long STRING_MAX_LEN = 44;
    private static final long HASH_MAX_LEN = 500;




    // TODO: 2022/7/29 采集集群BigKey
    public static void searchBigKey(String host,int port){
        try (Jedis jedis = new Jedis(host, port)) {
            String cursor = "0";
            do {
                List<String> list = jedis.scan(cursor).getResult();
                if (list == null || list.isEmpty()) {
                    break;
                }
                long len = HASH_MAX_LEN, maxLen = HASH_MAX_LEN;
                for (String key : list) {
                    String type = jedis.type(key);
                    switch (type) {
                        case "String":
                            len = jedis.strlen(key);
                            maxLen = STRING_MAX_LEN;
                            break;
                        case "list":
                            len = jedis.llen(key);
                            maxLen = HASH_MAX_LEN;
                            break;
                        case "set":
                            len = jedis.scard(key);
                            maxLen = HASH_MAX_LEN;
                            break;
                        case "hash":
                            len = jedis.hlen(key);
                            maxLen = HASH_MAX_LEN;
                            break;
                        case "zset":
                            len = jedis.zcard(key);
                            maxLen = HASH_MAX_LEN;
                        default:
                            break;
                    }
                    if (len >= maxLen) {
                        // TODO: 2022/7/29 记载日志
                        System.out.println(type + "---" + key + "---" + len);
                    }
                }
            } while (!cursor.equals("0"));
        }
    }

    public static void insertMultipleData(String host,int port){
        try (Jedis jedis = new Jedis(host,port)) {
            String[] strings = new String[2000];
            for (int i = 1,j; i < 100000; i++) {
                j = i % 1000 * 2;
                strings[j] = "key:" + i;
                strings[j + 1] = "" + i;
                if (j == 0) {
                    jedis.mset(strings);
                }
            }
        }
    }

    public static void insertMultipleDataByPipeline(String host,int port) {
        try (Jedis jedis = new Jedis(host, port)) {
            Pipeline pipeline = jedis.pipelined();
            for (int i = 1; i < 100000; i++) {
                pipeline.set("key" + i, "" + i);
                if (i % 1000 == 0) {
                    pipeline.sync();
                }
            }
        }
    }

}
