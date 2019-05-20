package com.wrial.main.example.immutable;

import com.google.common.collect.Maps;
import com.wrial.main.annotations.NotThreadSafe;
import com.wrial.main.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Map;

/**
 * 使用Collections.unmodifiable()方法来实现不可变的集合
 * 如果用put加就会报错
 *
 * */

@Slf4j
@ThreadSafe
public class Immutable2 {
    private final static Integer a = 1;
    private final static String b = "2";
    private static Map<Integer, Integer> map = null;

    static {
        map.put(1, 1);
        map.put(2, 2);
        map.put(3, 3);
        //使用Collections.unmodifiableMap()生成不可变对象
        map = Collections.unmodifiableMap(map);

    }

    public static void main(String[] args) {

        //如果强制进行put就会报错
        map.put(1, 3);
        log.info("{}", map.get(1));

    }


}
