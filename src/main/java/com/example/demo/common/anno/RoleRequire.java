package com.example.demo.common.anno;

import com.example.demo.common.enums.RoleType;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RoleRequire {
    RoleType[] role() default {};
}
