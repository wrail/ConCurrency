package com.wrial.main.example.publish;

import com.sun.org.apache.bcel.internal.classfile.InnerClass;
import com.wrial.main.annotations.NotRecommend;
import com.wrial.main.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

/***
 * 对象还没有构造完成就对其他线程可见
 *
 * */


@Slf4j
@NotThreadSafe
@NotRecommend
public class Escape {

    private int thisCanBeEscape = 0;

    public Escape() {
        new InnerClass();
    }

    class InnerClass {
        InnerClass() {
            log.info("{}", Escape.this.thisCanBeEscape);
        }
    }

    public static void main(String[] args) {
        new Escape();
    }
}
