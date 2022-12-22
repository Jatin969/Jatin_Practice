package Annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface TestAnnotations {
    String Developer();
    String Expiry_Date() default "2029/12/31";
}
