package com.wrial.main.example.publish;


import com.wrial.main.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;


/***
 *
 * 对象的发布
 */
@NotThreadSafe
@Slf4j
public class UnsafePublish {

    private String[] states = {"a", "b", "c"};

    //返回对象引用
    public String[] GetStates() {
        return states;
    }

    public static void main(String[] args) {
        UnsafePublish publish  = new UnsafePublish();
        String[] states = publish.GetStates();
        log.info("begin->{}", Arrays.toString(states));
        //修改states的值
        states[0] = "i";
        log.info("after->{}",Arrays.toString(states));

    }


}
