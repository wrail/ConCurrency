package com.wrial.main.annotations;

import sun.misc.Contended;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)//不被编译进字节码
public @interface ThreadSafe {

    String value() default "";
}
