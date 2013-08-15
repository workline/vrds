package vrds.meta;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface Coupling {
    CouplingTag[] tags() default {};

    String value() default "";
}
