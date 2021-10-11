package com.gaoap.learning.multipleds.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CurdataSource {
    String name();
}
