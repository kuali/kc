package org.kuali.kra.s2s.generator;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public @interface FormGenerator {

    /**
     * See {@link org.springframework.stereotype.Component#value()} for details.
     */
    String value() default "";
}
