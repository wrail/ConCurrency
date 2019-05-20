package com.wrial.main.example.immutable;

import com.google.common.collect.Maps;
import com.wrial.main.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * fianl回顾
 * 声明为fianl只是这个引用不能指向其他实例了，但是内部还是可变的
 * 因此不算是一个Immutable
 * 因此final在基本数据类型上使用的话就也相当于不变了
 * */


@Slf4j
@NotThreadSafe
public class Immutable1 {
    private final static Integer a = 1;
    private final static String b = "2";
    private final static Map<Integer, Integer> map = Maps.newHashMap();

    static {
        map.put(1, 1);
        map.put(2, 2);
        map.put(3, 3);

    }
    //如果传入的也是final，也不能进行修改
    public void test(final int a){
//        a= 0;  a不能被修改
    }

    public static void main(String[] args) {

//        a=2;  不可以赋值
//      map = Maps.newHashMap(); 这个引用不能指向其他对象
        map.put(1, 3);
        log.info("{}", map.get(1));

    }



}
