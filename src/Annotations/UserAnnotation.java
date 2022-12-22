package Annotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.FIELD)
@interface UserAnnotation {
    String Name();
    String position() default "client";
    Class<? > type();
}
